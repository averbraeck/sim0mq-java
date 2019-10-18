package org.sim0mq.demo.mm1;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.djutils.serialization.SerializationException;
import org.sim0mq.Sim0MQException;
import org.sim0mq.federationmanager.ModelState;
import org.sim0mq.message.Sim0MQMessage;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * Example implementation of a FederationManager to start the MM1Queue41Application DSOL model.
 * <p>
 * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version April 10, 2017 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class MM1FederationManager
{
    /** the state of the started model. */
    private ModelState state;

    /** the model socket. */
    private ZMQ.Socket modelSocket;

    /** the federate starter socket. */
    private ZMQ.Socket fsSocket;

    /** the context. */
    private ZContext fmContext;

    /** message count. */
    private long messageCount = 0;

    /**
     * Send an FM.1 message to the FederateStarter.
     * @param federationName the name of the federation
     * @param fmPort the port number to listen on
     * @param fsServerNameOrIP name or IP address of the federate starter we are using
     * @param fsPort the port where the federate starter can be reached
     * @param mm1ModelFolder location on the computer of the federate starter where the model can be found
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    public MM1FederationManager(final String federationName, final int fmPort, final String fsServerNameOrIP, final int fsPort,
            final String mm1ModelFolder) throws Sim0MQException, SerializationException
    {
        this.fmContext = new ZContext(1);
        this.modelSocket = this.fmContext.createSocket(SocketType.REQ);
        this.modelSocket.setIdentity(UUID.randomUUID().toString().getBytes());
        this.fsSocket = this.fmContext.createSocket(SocketType.REQ);
        this.fsSocket.setIdentity(UUID.randomUUID().toString().getBytes());

        this.state = ModelState.NOT_STARTED;
        boolean ready = false;
        while (!ready)
        {
            System.out.println(this.state);

            switch (this.state)
            {
                case NOT_STARTED:
                    startModel(federationName, fsServerNameOrIP, fsPort, mm1ModelFolder);
                    break;

                case STARTED:
                    sendSimRunControl(federationName);
                    break;

                case RUNCONTROL:
                    setParameters(federationName);
                    break;

                case PARAMETERS:
                    sendSimStart(federationName);
                    break;

                case SIMULATORSTARTED:
                    waitForSimEnded(federationName);
                    break;

                case SIMULATORENDED:
                    requestStatistics(federationName);
                    break;

                case STATISTICSGATHERED:
                    killFederate(federationName);
                    ready = true;
                    break;

                case ERROR:
                    killFederate(federationName);
                    ready = true;
                    break;

                default:
                    break;
            }
        }

        this.modelSocket.close();
        this.fsSocket.close();
        this.fmContext.destroy();
        this.fmContext.close();
    }

    /**
     * Send the FM.1 message to the FederateStarter to start the MM1 model.
     * @param federationName the name of the federation
     * @param fsServerNameOrIP name or IP address of the federate starter we are using
     * @param fsPort the port where the federate starter can be reached
     * @param mm1ModelFolder location on the computer of the federate starter where the model can be found
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    private void startModel(final String federationName, final String fsServerNameOrIP, final int fsPort,
            final String mm1ModelFolder) throws Sim0MQException, SerializationException
    {
        // Start model mmm1.jar
        byte[] fm1Message = Sim0MQMessage.encodeUTF8(federationName, "FM", "FS", "FM.1", ++this.messageCount, "MM1.1", "java8+",
                "-jar", mm1ModelFolder + "/mm1.jar", "MM1.1 %PORT%", mm1ModelFolder, "", mm1ModelFolder + "/out.txt",
                mm1ModelFolder + "/err.txt", false, true, true);
        this.fsSocket.connect("tcp://" + fsServerNameOrIP + ":" + fsPort);
        this.fsSocket.send(fm1Message);

        byte[] reply = this.fsSocket.recv(0);
        Object[] replyMessage = Sim0MQMessage.decode(reply);
        System.out.println("Received\n" + Sim0MQMessage.print(replyMessage));

        if (replyMessage[4].toString().equals("FS.2") && replyMessage[8].toString().equals("started")
                && replyMessage[7].toString().equals("MM1.1"))
        {
            this.state = ModelState.STARTED;
            this.modelSocket.connect("tcp://127.0.0.1:" + replyMessage[9].toString());
        }
        else
        {
            this.state = ModelState.ERROR;
            System.err.println("Model not started correctly -- state = " + replyMessage[8]);
            System.err.println("Started model = " + replyMessage[7] + " on port " + replyMessage[9]);
            System.err.println("Error message = " + replyMessage[10]);
        }

    }

    /**
     * Send the SimRunControl message FM.2.
     * @param federationName the name of the federation
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    private void sendSimRunControl(final String federationName) throws Sim0MQException, SerializationException
    {
        byte[] fm2Message;
        fm2Message = Sim0MQMessage.encodeUTF8(federationName, "FM", "MM1.1", "FM.2", ++this.messageCount, 100.0, 0.0, 0.0,
                Double.POSITIVE_INFINITY, 1, 0);
        this.modelSocket.send(fm2Message);

        byte[] reply = this.modelSocket.recv(0);
        Object[] replyMessage = Sim0MQMessage.decode(reply);
        System.out.println("Received\n" + Sim0MQMessage.print(replyMessage));

        if (replyMessage[4].toString().equals("MC.2") && (boolean) replyMessage[8]
                && ((Long) replyMessage[7]).longValue() == this.messageCount)
        {
            this.state = ModelState.RUNCONTROL;
        }
        else
        {
            this.state = ModelState.ERROR;
            System.err.println("Model not started correctly -- status = " + replyMessage[8]);
            System.err.println("Error message = " + replyMessage[9]);
        }
    }

    /**
     * Send the Parameters messages FM.3.
     * @param federationName the name of the federation
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    private void setParameters(final String federationName) throws Sim0MQException, SerializationException
    {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("iat", new Double(1.0));
        parameters.put("servicetime", new Double(0.85));

        for (String parameterName : parameters.keySet())
        {
            if (!this.state.isError())
            {
                byte[] fm3Message;
                fm3Message = Sim0MQMessage.encodeUTF8(federationName, "FM", "MM1.1", "FM.3", ++this.messageCount, parameterName,
                        parameters.get(parameterName));
                this.modelSocket.send(fm3Message);

                byte[] reply = this.modelSocket.recv(0);
                Object[] replyMessage = Sim0MQMessage.decode(reply);
                System.out.println("Received\n" + Sim0MQMessage.print(replyMessage));

                if (replyMessage[4].toString().equals("MC.2") && (boolean) replyMessage[8]
                        && ((Long) replyMessage[7]).longValue() == this.messageCount)
                {
                    this.state = ModelState.PARAMETERS;
                }
                else
                {
                    this.state = ModelState.ERROR;
                    System.err.println("Model parameter error -- status = " + replyMessage[8]);
                    System.err.println("Error message = " + replyMessage[9]);
                }
            }
        }
        if (!this.state.isError())
        {
            this.state = ModelState.PARAMETERS;
        }
    }

    /**
     * Send the SimStart message FM.4.
     * @param federationName the name of the federation
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    private void sendSimStart(final String federationName) throws Sim0MQException, SerializationException
    {
        byte[] fm4Message;
        fm4Message = Sim0MQMessage.encodeUTF8(federationName, "FM", "MM1.1", "FM.4", ++this.messageCount);
        this.modelSocket.send(fm4Message);

        byte[] reply = this.modelSocket.recv(0);
        Object[] replyMessage = Sim0MQMessage.decode(reply);
        System.out.println("Received\n" + Sim0MQMessage.print(replyMessage));

        if (replyMessage[4].toString().equals("MC.2") && (boolean) replyMessage[8]
                && ((Long) replyMessage[7]).longValue() == this.messageCount)
        {
            this.state = ModelState.SIMULATORSTARTED;
        }
        else
        {
            this.state = ModelState.ERROR;
            System.err.println("Simulation start error -- status = " + replyMessage[8]);
            System.err.println("Error message = " + replyMessage[9]);
        }
    }

    /**
     * Wait for simulation to end using status polling with message FM.5.
     * @param federationName the name of the federation
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    private void waitForSimEnded(final String federationName) throws Sim0MQException, SerializationException
    {
        while (!this.state.isSimulatorEnded() && !this.state.isError())
        {
            byte[] fm5Message;
            fm5Message = Sim0MQMessage.encodeUTF8(federationName, "FM", "MM1.1", "FM.5", ++this.messageCount);
            this.modelSocket.send(fm5Message);

            byte[] reply = this.modelSocket.recv(0);
            Object[] replyMessage = Sim0MQMessage.decode(reply);
            System.out.println("Received\n" + Sim0MQMessage.print(replyMessage));

            if (replyMessage[4].toString().equals("MC.1") && !replyMessage[8].toString().equals("error")
                    && !replyMessage[8].toString().equals("started")
                    && ((Long) replyMessage[7]).longValue() == this.messageCount)
            {
                if (replyMessage[8].toString().equals("ended"))
                {
                    this.state = ModelState.SIMULATORENDED;
                }
                else
                {
                    // wait a second
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException ie)
                    {
                        // ignore
                    }
                }
            }
            else
            {
                this.state = ModelState.ERROR;
                System.err.println("Simulation start error -- status = " + replyMessage[8]);
                System.err.println("Error message = " + replyMessage[9]);
            }
        }
    }

    /**
     * Request statistics with message FM.6.
     * @param federationName the name of the federation
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    private void requestStatistics(final String federationName) throws Sim0MQException, SerializationException
    {
        List<String> stats = new ArrayList<>();
        stats.add("dN.average");
        stats.add("qN.max");
        stats.add("uN.average");

        for (String statName : stats)
        {
            if (!this.state.isError())
            {
                byte[] fm6Message;
                fm6Message = Sim0MQMessage.encodeUTF8(federationName, "FM", "MM1.1", "FM.6", ++this.messageCount, statName);
                this.modelSocket.send(fm6Message);

                byte[] reply = this.modelSocket.recv(0);
                Object[] replyMessage = Sim0MQMessage.decode(reply);
                System.out.println("Received\n" + Sim0MQMessage.print(replyMessage));

                if (replyMessage[4].toString().equals("MC.3"))
                {
                    if (replyMessage[7].toString().equals(statName))
                    {
                        System.out.println("Received statistic for " + statName + " = " + replyMessage[8].toString());
                    }
                    else
                    {
                        this.state = ModelState.ERROR;
                        System.err.println(
                                "Statistics Error: Stat variable expected = " + statName + ", got: " + replyMessage[7]);
                    }
                }
                else if (replyMessage[4].toString().equals("MC.4"))
                {
                    this.state = ModelState.ERROR;
                    System.err.println("Statistics Error: Stat variable = " + replyMessage[7]);
                    System.err.println("Error message = " + replyMessage[8]);
                }
                else
                {
                    this.state = ModelState.ERROR;
                    System.err.println("Statistics Error: Received unknown message as reply to FM6: " + replyMessage[4]);
                }
            }
        }
        if (!this.state.isError())
        {
            this.state = ModelState.STATISTICSGATHERED;
        }
    }

    /**
     * Send the FM.8 message to the FederateStarter to kill the MM1 model.
     * @param federationName the name of the federation
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    private void killFederate(final String federationName) throws Sim0MQException, SerializationException
    {
        byte[] fm8Message;
        fm8Message = Sim0MQMessage.encodeUTF8(federationName, "FM", "FS", "FM.8", ++this.messageCount, "MM1.1");
        this.fsSocket.send(fm8Message);

        byte[] reply = this.fsSocket.recv(0);
        Object[] replyMessage = Sim0MQMessage.decode(reply);
        System.out.println("Received\n" + Sim0MQMessage.print(replyMessage));

        if (replyMessage[4].toString().equals("FS.4") && (boolean) replyMessage[8]
                && replyMessage[7].toString().equals("MM1.1"))
        {
            this.state = ModelState.TERMINATED;
        }
        else
        {
            this.state = ModelState.ERROR;
            System.err.println("Model not killed correctly");
            System.err.println("Tried to kill model = " + replyMessage[7]);
            System.err.println("Error message = " + replyMessage[9]);
        }
    }

    /**
     * @param args parameters for main
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    public static void main(final String[] args) throws Sim0MQException, SerializationException
    {
        if (args.length < 5)
        {
            System.err.println("Use as FederationManager federationName federationManagerPortNumber "
                    + "federateStarterIPorName federateStarterPortNumber modelFolder");
            System.exit(-1);
        }
        String federationName = args[0];

        String fmsPort = args[1];
        int fmPort = 0;
        try
        {
            fmPort = Integer.parseInt(fmsPort);
        }
        catch (NumberFormatException nfe)
        {
            System.err.println("Use as FederationManager fedName fmPort fsIP fsPort modelFolder, where fmPort is a number");
            System.exit(-1);
        }
        if (fmPort == 0 || fmPort > 65535)
        {
            System.err.println("fmPort should be between 1 and 65535");
            System.exit(-1);
        }

        String fsServerNameOrIP = args[2];

        String fsPortString = args[3];
        int fsPort = 0;
        try
        {
            fsPort = Integer.parseInt(fsPortString);
        }
        catch (NumberFormatException nfe)
        {
            System.err.println("Use as FederationManager fedName fmPort fsIP fsPort modelFolder, where fmPort is a number");
            System.exit(-1);
        }
        if (fsPort == 0 || fsPort > 65535)
        {
            System.err.println("fsPort should be between 1 and 65535");
            System.exit(-1);
        }

        String mm1ModelFolder = args[4];

        new MM1FederationManager(federationName, fmPort, fsServerNameOrIP, fsPort, mm1ModelFolder);
    }

}
