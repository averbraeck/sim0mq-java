package org.sim0mq.test.message;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import org.djutils.io.URLResource;
import org.djutils.serialization.SerializationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.sim0mq.Sim0MQException;
import org.sim0mq.federatestarter.FederateStarter;
import org.sim0mq.message.Sim0MQMessage;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * <p>
 * Copyright (c) 2013-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
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
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

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
        InputStream propertiesStream = URLResource.getResourceAsStream("/software.properties");
        try
        {
            softwareProperties.load(propertiesStream);
        }
        catch (IOException | NullPointerException e)
        {
            fail("Could not find or read software properties file /software.properties");
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
        String temp = this.folder.getRoot().getAbsolutePath();
        System.out.println(temp);
        byte[] fm1Message;
        fm1Message = Sim0MQMessage.encodeUTF8(federationName, "FM", "FS", "FM.1", ++this.messageCount, "MM1.1", "java8+",
                "-version", temp, "", temp, "", "out.txt", "err.txt", false, false, false);
        this.fsSocket.connect("tcp://127.0.0.1:" + fsPort);
        this.fsSocket.send(fm1Message);

        byte[] reply = this.fsSocket.recv(0);
        Object[] replyMessage = Sim0MQMessage.decode(reply);
        System.out.println("Received\n" + Sim0MQMessage.print(replyMessage));

        if (replyMessage[4].toString().equals("FS.2") && replyMessage[8].toString().equals("started")
                && replyMessage[7].toString().equals("MM1.1"))
        {
            this.state = 1;
            System.out.println("Model started correctly -- state = " + replyMessage[8] + "\n");
        }
        else
        {
            this.state = -1;
            System.err.println("Model not started correctly -- state = " + replyMessage[8]);
            System.err.println("Started model = " + replyMessage[7] + " on port " + replyMessage[9]);
            System.err.println("Error message = " + replyMessage[10] + "\n");
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
        fm8Message = Sim0MQMessage.encodeUTF8(federationName, "FM", "FS", "FM.8", ++this.messageCount, "MM1.1");
        this.fsSocket.send(fm8Message);

        byte[] reply = this.fsSocket.recv(0);
        Object[] replyMessage = Sim0MQMessage.decode(reply);
        System.out.println("Received\n" + Sim0MQMessage.print(replyMessage));

        if (replyMessage[4].toString().equals("FS.4") && (boolean) replyMessage[8]
                && replyMessage[7].toString().equals("MM1.1"))
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
