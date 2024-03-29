package org.sim0mq.test.message;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;
import java.util.UUID;

import org.djutils.io.URLResource;
import org.djutils.serialization.SerializationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.sim0mq.Sim0MQException;
import org.sim0mq.federatestarter.FederateStarter;
import org.sim0mq.message.Sim0MQMessage;
import org.sim0mq.message.federatestarter.FS2FederateStartedMessage;
import org.sim0mq.message.federationmanager.FM1StartFederateMessage;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version 13 Jul 2019 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class TestFederateStarter
{
    /** the state of the started model. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    int state;

    /** the ready state of the started model. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    static boolean ready = false;

    /** the federate starter socket. */
    private ZMQ.Socket fsSocket;

    /** the context. */
    private ZContext fmContext;

    /** message count. */
    private long messageCount = 0;

    /** the error to report. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    String error = "";

    /** temporary folder for out / err files. */
    @TempDir
    private Path folder;

    /**
     * Test Federation Starter functions.
     * @throws Sim0MQException on encoding error
     * @throws SerializationException on serialization error
     */
    @SuppressWarnings("checkstyle:needbraces")
    @Test
    public void testFederateStarter() throws Sim0MQException, SerializationException
    {
        Properties softwareProperties = new Properties();
        InputStream propertiesStream = URLResource.getResourceAsStream("/resources/software.properties");
        try
        {
            softwareProperties.load(propertiesStream);
        }
        catch (IOException | NullPointerException e)
        {
            fail("Could not find or read software properties file /resources/software.properties");
        }
        Thread fsThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    new FederateStarter(5500, softwareProperties, 5501, 5599, false);
                }
                catch (Sim0MQException | SerializationException exception)
                {
                    TestFederateStarter.this.state = 2;
                    TestFederateStarter.ready = true;
                    TestFederateStarter.this.error = exception.getMessage();
                }
            }
        });
        fsThread.start();

        this.fmContext = new ZContext(1);
        this.fsSocket = this.fmContext.createSocket(SocketType.REQ);
        this.fsSocket.setIdentity(UUID.randomUUID().toString().getBytes());

        this.state = 0;
        while (!ready)
        {
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException exception)
            {
                //
            }
            switch (this.state)
            {
                case 0:
                    this.state = 100;
                    new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            try
                            {
                                startModel("UNITTEST.1", 5500);
                            }
                            catch (Sim0MQException | SerializationException exception)
                            {
                                TestFederateStarter.this.error = exception.getMessage();
                                TestFederateStarter.this.state = 2;
                            }
                        }
                    }).start();
                    break;

                case 1:
                    this.state = 100;
                    new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            try
                            {
                                killFederate("UNITTEST.1");
                            }
                            catch (Sim0MQException | SerializationException exception)
                            {
                                TestFederateStarter.this.error = exception.getMessage();
                                TestFederateStarter.this.state = 3;
                            }
                        }
                    }).start();
                    break;

                case 2:
                    fsThread.interrupt();
                    ready = true;
                    break;

                case -1:
                    this.state = 2;
                    new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            try
                            {
                                killFederate("UNITTEST.1");
                            }
                            catch (Sim0MQException | SerializationException exception)
                            {
                                TestFederateStarter.this.error = exception.getMessage();
                                TestFederateStarter.this.state = -1;
                            }
                        }
                    }).start();
                    ready = true;
                    fail("FederateStarted returned with an error");
                    break;

                default:
                    break;
            }
        }

        fsThread.interrupt();

        if (this.error.length() > 0)
        {
            fail(this.error);
        }

        this.fsSocket.close();
        this.fmContext.destroy();
        this.fmContext.close();
    }

    /**
     * Send the FM.1 message to the FederateStarter to start a process.
     * @param federationName Name of the federation
     * @param fsPort the Federate Starter port
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    void startModel(final String federationName, final int fsPort) throws Sim0MQException, SerializationException
    {
        String temp = this.folder.toString();
        System.out.println(temp);
        //@formatter:off
        byte[] fm1Message = new FM1StartFederateMessage.Builder()
            .setSimulationRunId(federationName)
            .setSenderId("FM")
            .setReceiverId("FS")
            .setMessageId(++this.messageCount)
            .setInstanceId("MM1.1")
            .setSoftwareCode("java8+")
            .setArgsBefore("-version")
            .setModelPath(temp)
            .setArgsAfter("")
            .setWorkingDirectory(temp)
            .setRedirectStdin("")
            .setRedirectStdout("out.txt")
            .setRedirectStderr("err.txt")
            .setDeleteWorkingDirectory(false)
            .setDeleteStdout(false)
            .setDeleteStderr(false)
            .build()
            .createByteArray();
        //@formatter:on

        this.fsSocket.connect("tcp://127.0.0.1:" + fsPort);
        this.fsSocket.send(fm1Message);

        byte[] reply = this.fsSocket.recv(0);
        Object[] replyArray = Sim0MQMessage.decodeToArray(reply);
        System.out.println("Received\n" + Sim0MQMessage.print(replyArray));
        FS2FederateStartedMessage replyMessage = new FS2FederateStartedMessage(replyArray);
        if (replyMessage.getStatus().equals("started") && replyMessage.getInstanceId().toString().equals("MM1.1"))
        {
            this.state = 1;
            System.out.println("Model started correctly -- state = " + replyMessage.getStatus() + "\n");
        }
        else
        {
            this.state = -1;
            System.err.println("Model not started correctly -- state = " + replyMessage.getStatus());
            System.err.println("Started model = " + replyMessage.getInstanceId() + " on port " + replyMessage.getModelPort());
            System.err.println("Error message = " + replyMessage.getError() + "\n");
        }
    }

    /**
     * Send the FM.8 message to the FederateStarter to kill the MM1 model.
     * @param federationName the name of the federation
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    void killFederate(final String federationName) throws Sim0MQException, SerializationException
    {
        byte[] fm8Message;
        fm8Message = Sim0MQMessage.encodeUTF8(true, federationName, "FM", "FS", "FM.8", ++this.messageCount, "MM1.1");
        this.fsSocket.send(fm8Message);

        byte[] reply = this.fsSocket.recv(0);
        Object[] replyMessage = Sim0MQMessage.decodeToArray(reply);
        System.out.println("Received\n" + Sim0MQMessage.print(replyMessage));

        if (replyMessage[5].toString().equals("FS.4") && (boolean) replyMessage[9]
                && replyMessage[8].toString().equals("MM1.1"))
        {
            this.state = 2;
        }
        else
        {
            this.state = -1;
            System.err.println("Model not killed correctly");
            System.err.println("Tried to kill model = " + replyMessage[8]);
            System.err.println("Error message = " + replyMessage[10]);
        }
    }

}
