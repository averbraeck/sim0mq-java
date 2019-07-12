package org.sim0mq.federatestarter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.djutils.io.URLResource;
import org.djutils.serialization.SerializationException;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.MessageStatus;
import org.sim0mq.message.SimulationMessage;
import org.sim0mq.message.federatestarter.FederateStartedMessage;
import org.sim0mq.message.federationmanager.FM1StartFederateMessage;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * The FederateStarter start listening on the given port for messages to start components. Report back via the call-back port on
 * the status of the started components. If necessary, the FederateStarter can also forcefully stop a started (sub)process.
 * <p>
 * Copyright (c) 2016-2017 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version Mar 1, 2017 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class FederateStarter
{
    /** the port number to listen on. */
    private final int fsPort;

    /** the first port to be used for the models, inclusive. */
    private final int startPort;

    /** the last port to be used for the models, inclusive. */
    private final int endPort;

    /** the running programs this FederateStarter started. The String identifies the process (e.g., a UUID or a model id). */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected Map<String, Process> runningProcessMap = Collections.synchronizedMap(new HashMap<>());

    /** the ports where the models listen. The String identifies the process (e.g., a UUID or a model id). */
    private Map<String, Integer> modelPortMap = Collections.synchronizedMap(new HashMap<>());

    /** the StartFederate messages. */
    private Map<String, FM1StartFederateMessage> startFederateMessages = Collections.synchronizedMap(new HashMap<>());

    /** the software properties. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    final Properties softwareProperties;

    /** the 0mq socket. */
    private ZMQ.Socket fsSocket;

    /** the 0mq context. */
    private ZContext fsContext;

    /** message count. */
    private long messageCount = 0;

    /**
     * @param fsPort the port number to listen on
     * @param softwareProperties the software properties to use
     * @param startPort first port to be used for the models, inclusive
     * @param endPort last port to be used for the models, inclusive
     * @throws Sim0MQException on error
     * @throws SerializationException on error
     */
    public FederateStarter(final int fsPort, final Properties softwareProperties, final int startPort, final int endPort)
            throws Sim0MQException, SerializationException
    {
        super();
        this.softwareProperties = softwareProperties;
        this.fsPort = fsPort;
        this.startPort = startPort;
        this.endPort = endPort;

        this.fsContext = new ZContext(1);

        this.fsSocket = this.fsContext.createSocket(SocketType.ROUTER);
        this.fsSocket.bind("tcp://*:" + this.fsPort);

        while (!Thread.currentThread().isInterrupted())
        {
            // Wait for next request from the client -- first the identity (String) and the delimiter (#0)
            String identity = this.fsSocket.recvStr();
            this.fsSocket.recvStr();

            byte[] request = this.fsSocket.recv(0);
            Object[] fields = SimulationMessage.decode(request);
            String messageTypeId = fields[4].toString();
            String receiverId = fields[3].toString();

            System.out.println("Received " + SimulationMessage.print(fields));

            if (receiverId.equals("FS"))
            {
                switch (messageTypeId)
                {
                    case "FM.1":
                        processStartFederate(identity, fields);
                        break;

                    case "FM.8":
                        processKillFederate(identity, fields);
                        break;

                    case "FM.9":
                        // processKillAllFederates(senderId, uniqueId);
                        break;

                    default:
                        // wrong message
                        System.err.println("Received unknown message -- not processed: " + messageTypeId);
                }
            }
            else
            {
                // wrong receiver
                System.err.println("Received message not intended for FS but for " + receiverId + " -- not processed: ");
            }
        }
        this.fsSocket.close();
        this.fsContext.destroy();
    }

    /**
     * Process FM.2 message and send MC.2 message back.
     * @param identity reply id for REQ-ROUTER pattern
     * @param fields the message
     * @throws Sim0MQException on error
     * @throws SerializationException on error
     */
    private void processStartFederate(final String identity, final Object[] fields)
            throws Sim0MQException, SerializationException
    {
        FM1StartFederateMessage startFederateMessage = FM1StartFederateMessage.createMessage(fields, "FS");
        String error = "";

        int modelPort = findFreePortNumber();

        if (modelPort == -1)
        {
            error = "No free port number";
        }

        else

        {
            try
            {
                ProcessBuilder pb = new ProcessBuilder();

                Path workingPath = Files.createDirectories(Paths.get(startFederateMessage.getWorkingDirectory()));
                pb.directory(workingPath.toFile());

                String softwareCode = "";
                if (!this.softwareProperties.containsKey(startFederateMessage.getSoftwareCode()))
                {
                    System.err.println("Could not find software alias " + startFederateMessage.getSoftwareCode()
                            + " in software properties file");
                }
                else
                {
                    softwareCode = this.softwareProperties.getProperty(startFederateMessage.getSoftwareCode());

                    List<String> pbArgs = new ArrayList<>();
                    pbArgs.add(softwareCode);
                    pbArgs.add(startFederateMessage.getArgsBefore());
                    pbArgs.add(startFederateMessage.getModelPath());
                    pbArgs.addAll(Arrays.asList(
                            startFederateMessage.getArgsAfter().replaceAll("%PORT%", String.valueOf(modelPort)).split(" ")));
                    pb.command(pbArgs);

                    String stdIn = startFederateMessage.getRedirectStdin();
                    String stdOut = startFederateMessage.getRedirectStdout();
                    String stdErr = startFederateMessage.getRedirectStderr();

                    if (stdIn.length() > 0)
                    {
                        // TODO working dir path if not absolute?
                        File stdInFile = new File(stdIn);
                        pb.redirectInput(stdInFile);
                    }

                    if (stdOut.length() > 0)
                    {
                        // TODO working dir path if not absolute?
                        File stdOutFile = new File(stdOut);
                        pb.redirectOutput(stdOutFile);
                    }

                    if (stdErr.length() > 0)
                    {
                        // TODO working dir path if not absolute?
                        File stdErrFile = new File(stdErr);
                        pb.redirectError(stdErrFile);
                    }

                    new Thread()
                    {
                        /** {@inheritDoc} */
                        @Override
                        public void run()
                        {
                            try
                            {
                                Process process = pb.start();
                                FederateStarter.this.runningProcessMap.put(startFederateMessage.getInstanceId(), process);
                                System.err.println("Process started:" + process.isAlive());
                            }
                            catch (IOException exception)
                            {
                                exception.printStackTrace();
                            }
                        }
                    }.start();

                    this.modelPortMap.put(startFederateMessage.getInstanceId(), modelPort);
                    this.startFederateMessages.put(startFederateMessage.getInstanceId(), startFederateMessage);

                    // Thread.sleep(1000);

                    // wait till the model is ready...
                    error = waitForModelStarted(startFederateMessage.getSimulationRunId(), startFederateMessage.getInstanceId(),
                            modelPort);
                }
            }
            catch (IOException exception)
            {
                exception.printStackTrace();
                error = exception.getMessage();
            }
        }

        System.out.println("SEND MESSAGE FS.2 ABOUT MODEL " + startFederateMessage.getInstanceId() + " @ port " + modelPort);

        // Send reply back to client
        this.fsSocket.sendMore(identity);
        this.fsSocket.sendMore("");
        //@formatter:off
        byte[] fs2Message = new FederateStartedMessage.Builder()
                .setSimulationRunId(startFederateMessage.getSimulationRunId())
                .setInstanceId(startFederateMessage.getInstanceId())
                .setSenderId("FS")
                .setReceiverId(startFederateMessage.getSenderId())
                .setMessageId(++this.messageCount)
                .setStatus(error.isEmpty() ? "started" : "error")
                .setError(error)
                .setModelPort(modelPort)
                .build()
                .createByteArray();
        this.fsSocket.send(fs2Message, 0);
        //@formatter:on
    }

    /**
     * Find a free port for the model.
     * @return the first free fort number in the range startPort - endPort, inclusive
     */
    private int findFreePortNumber()
    {
        for (int port = this.startPort; port <= this.endPort; port++)
        {
            if (!this.modelPortMap.containsValue(port))
            {
                // try if the port is really free
                ZMQ.Socket testSocket = null;
                try
                {
                    testSocket = this.fsContext.createSocket(SocketType.REP);
                    testSocket.bind("tcp://127.0.0.1:" + port);
                    testSocket.unbind("tcp://127.0.0.1:" + port);
                    testSocket.close();
                    return port;
                }
                catch (Exception exception)
                {
                    // port was not free
                    if (testSocket != null)
                    {
                        try
                        {
                            testSocket.close();
                        }
                        catch (Exception e)
                        {
                            // ignore.
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Wait for simulation to end using status polling with message FM.5.
     * @param federationRunId the name of the federation
     * @param modelId the String id of the model
     * @param modelPort port on which the model is listening
     * @return empty String for no error, filled String for error
     * @throws Sim0MQException on error
     * @throws SerializationException on error
     */
    private String waitForModelStarted(final Object federationRunId, final String modelId, final int modelPort)
            throws Sim0MQException, SerializationException
    {
        boolean ok = true;
        String error = "";
        ZMQ.Socket modelSocket = null;
        try
        {
            modelSocket = this.fsContext.createSocket(SocketType.REQ);
            modelSocket.setIdentity(UUID.randomUUID().toString().getBytes());
            modelSocket.connect("tcp://127.0.0.1:" + modelPort);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            ok = false;
            error = exception.getMessage();
        }

        boolean started = false;
        while (ok && !started)
        {
            byte[] fs1Message = SimulationMessage.encodeUTF8(federationRunId, "FS", modelId, "FS.1", ++this.messageCount,
                    MessageStatus.NEW);
            modelSocket.send(fs1Message, 0);

            byte[] reply = modelSocket.recv(0);
            Object[] replyMessage = SimulationMessage.decode(reply);
            System.out.println("Received\n" + SimulationMessage.print(replyMessage));

            if (replyMessage[4].toString().equals("MC.1") && !replyMessage[9].toString().equals("error")
                    && !replyMessage[9].toString().equals("ended") && ((Long) replyMessage[8]).longValue() == this.messageCount)
            {
                if (replyMessage[9].toString().equals("started"))
                {
                    started = true;
                }
                else
                {
                    // wait a second
                    try
                    {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException ie)
                    {
                        // ignore
                    }
                }
            }
            else
            {
                ok = false;
                error = replyMessage[10].toString();
                System.err.println("Simulation start error -- status = " + replyMessage[9]);
                System.err.println("Error message = " + replyMessage[10]);
            }
        }

        if (modelSocket != null)
        {
            modelSocket.close();
        }

        return error;
    }

    /**
     * Process FM.8 message and send FS.4 message back.
     * @param identity reply id for REQ-ROUTER pattern
     * @param fields the message
     * @throws Sim0MQException on error
     * @throws SerializationException on error
     */
    private void processKillFederate(final String identity, final Object[] fields)
            throws Sim0MQException, SerializationException
    {
        boolean status = true;
        String error = "";

        Object federationRunId = fields[1];
        String senderId = fields[2].toString();

        String modelId = fields[8].toString();
        if (!this.modelPortMap.containsKey(modelId))
        {
            status = false;
            error = "model " + modelId + " unknown -- this model is unknown to the FederateStarter";
        }
        else
        {
            int modelPort = this.modelPortMap.remove(modelId);
            Process process = this.runningProcessMap.remove(modelId);

            try
            {
                try
                {
                    ZMQ.Socket modelSocket = this.fsContext.createSocket(SocketType.REQ);
                    modelSocket.setIdentity(UUID.randomUUID().toString().getBytes());
                    modelSocket.connect("tcp://127.0.0.1:" + modelPort);

                    byte[] fs3Message = SimulationMessage.encodeUTF8(federationRunId, "FS", modelId, "FS.3",
                            ++this.messageCount, MessageStatus.NEW);
                    modelSocket.send(fs3Message, 0);

                    modelSocket.close();
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                    status = true;
                    error = exception.getMessage();
                }

                try
                {
                    Thread.sleep(100);
                }
                catch (InterruptedException ie)
                {
                    // ignore
                }

                if (process != null && process.isAlive())
                {
                    process.destroyForcibly();
                }

                FM1StartFederateMessage sfm = this.startFederateMessages.get(modelId);
                if (sfm.isDeleteStdout())
                {
                    if (sfm.getRedirectStdout().length() > 0)
                    {
                        File stdOutFile = new File(sfm.getRedirectStdout());
                        stdOutFile.delete();
                    }
                }

                if (sfm.isDeleteStderr())
                {
                    if (sfm.getRedirectStderr().length() > 0)
                    {
                        File stdErrFile = new File(sfm.getRedirectStderr());
                        stdErrFile.delete();
                    }
                }

                if (sfm.isDeleteWorkingDirectory())
                {
                    File workingDir = new File(sfm.getWorkingDirectory());
                    workingDir.delete();
                }
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
                status = false;
                error = exception.getMessage();
            }

            byte[] fs4Message = SimulationMessage.encodeUTF8(federationRunId, "FS", senderId, "FS.4", ++this.messageCount,
                    MessageStatus.NEW, modelId, status, error);
            this.fsSocket.sendMore(identity);
            this.fsSocket.sendMore("");
            this.fsSocket.send(fs4Message, 0);
        }
    }

    /**
     * Start listening on the given port for messages to start components. Report back via the call-back port on the status of
     * the started components. If necessary, the FederateStarter can also forcefully stop a started (sub)process.
     * @param args the federation name and port on which the FederateStarter is listening
     * @throws Sim0MQException on error
     * @throws SerializationException on error
     */
    public static void main(final String[] args) throws Sim0MQException, SerializationException
    {
        if (args.length < 4)
        {
            System.err.println("Use as FederateStarter portNumber software_properties_file startPort endPort");
            System.exit(-1);
        }

        String sPort = args[0];
        int port = 0;
        try
        {
            port = Integer.parseInt(sPort);
        }
        catch (NumberFormatException nfe)
        {
            System.err.println("Use as FederateStarter portNumber, where portNumber is a number");
            System.exit(-1);
        }
        if (port == 0 || port > 65535)
        {
            System.err.println("PortNumber should be between 1 and 65535");
            System.exit(-1);
        }

        String propertiesFile = args[1];
        Properties softwareProperties = new Properties();
        InputStream propertiesStream = URLResource.getResourceAsStream(propertiesFile);
        try
        {
            softwareProperties.load(propertiesStream);
        }
        catch (IOException | NullPointerException e)
        {
            System.err.println("Could not find or read software properties file " + propertiesFile);
            System.exit(-1);
        }

        String sStartPort = args[2];
        int startPort = 0;
        try
        {
            startPort = Integer.parseInt(sStartPort);
        }
        catch (NumberFormatException nfe)
        {
            System.err.println("Use as FederateStarter pn file startPort endPort, where startPort is a number");
            System.exit(-1);
        }
        if (startPort == 0 || startPort > 65535)
        {
            System.err.println("startPort should be between 1 and 65535");
            System.exit(-1);
        }

        String sEndPort = args[3];
        int endPort = 0;
        try
        {
            endPort = Integer.parseInt(sEndPort);
        }
        catch (NumberFormatException nfe)
        {
            System.err.println("Use as FederateStarter pn file startPort endPort, where endPort is a number");
            System.exit(-1);
        }
        if (endPort == 0 || endPort > 65535)
        {
            System.err.println("endPort should be between 1 and 65535");
            System.exit(-1);
        }

        new FederateStarter(port, softwareProperties, startPort, endPort);
    }

}
