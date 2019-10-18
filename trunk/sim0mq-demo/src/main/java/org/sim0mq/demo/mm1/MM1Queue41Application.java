package org.sim0mq.demo.mm1;

import java.rmi.RemoteException;

import javax.naming.NamingException;

import org.djunits.unit.DurationUnit;
import org.djunits.value.vdouble.scalar.Duration;
import org.djutils.serialization.SerializationException;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.MessageUtil;
import org.sim0mq.message.Sim0MQMessage;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import nl.tudelft.simulation.dsol.SimRuntimeException;
import nl.tudelft.simulation.dsol.experiment.Replication;
import nl.tudelft.simulation.dsol.experiment.ReplicationMode;
import nl.tudelft.simulation.dsol.simulators.DEVSSimulator;
import nl.tudelft.simulation.dsol.simulators.DEVSSimulatorInterface;

/**
 * <p>
 * Copyright (c) 2015-2019 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
 * <p>
 * See for project information <a href="http://www.simulation.tudelft.nl/"> www.simulation.tudelft.nl</a>.
 * <p>
 * The DSOL project is distributed under a BSD-style license.<br>
 * @version Aug 15, 2014 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class MM1Queue41Application
{
    /** */
    private DEVSSimulator.TimeDouble simulator;

    /** */
    private MM1Queue41Model model;

    /** the socket. */
    private ZMQ.Socket fsSocket;

    /** the context. */
    private ZContext fsContext;

    /** federation run id. */
    private Object federationRunId;

    /** modelId unique Id of the model that is used as the sender/receiver when communicating. */
    private String modelId;

    /** runtime. */
    private Duration runTime;

    /** warmup. */
    private Duration warmupTime;

    /** message count. */
    private long messageCount = 0;

    /**
     * Construct a console application.
     * @param modelId unique Id of the model that is used as the sender/receiver when communicating
     * @param port the sim0mq port number on which the model listens
     * @throws SimRuntimeException on error
     * @throws RemoteException on error
     * @throws NamingException on error
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    protected MM1Queue41Application(final String modelId, final int port)
            throws SimRuntimeException, RemoteException, NamingException, Sim0MQException, SerializationException
    {
        this.simulator = new DEVSSimulator.TimeDouble();
        this.modelId = modelId.trim();
        this.model = new MM1Queue41Model(this.simulator);
        startListener(port);
    }

    /**
     * Start listening on a port.
     * @param port the sim0mq port number on which the model listens
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    protected void startListener(final int port) throws Sim0MQException, SerializationException
    {
        this.fsContext = new ZContext(1);

        this.fsSocket = this.fsContext.createSocket(SocketType.ROUTER);
        this.fsSocket.bind("tcp://*:" + port);

        System.out.println("Model started. Listening at port: " + port);
        System.out.flush();

        while (!Thread.currentThread().isInterrupted())
        {
            // Wait for next request from the client -- first the identity (String) and the delimiter (#0)
            String identity = this.fsSocket.recvStr();
            this.fsSocket.recvStr();

            byte[] request = this.fsSocket.recv(0);
            System.out.println(MessageUtil.printBytes(request));
            Object[] fields = Sim0MQMessage.decode(request);

            System.out.println("Received " + Sim0MQMessage.print(fields));
            System.out.flush();

            this.federationRunId = fields[1];
            String senderId = fields[2].toString();
            String receiverId = fields[3].toString();
            String messageId = fields[4].toString();
            long uniqueId = ((Long) fields[5]).longValue();

            if (receiverId.equals(this.modelId))
            {
                System.err.println("Received: " + messageId + ", payload = " + Sim0MQMessage.listPayload(fields));
                switch (messageId)
                {
                    case "FS.1":
                    case "FM.5":
                        processRequestStatus(identity, senderId, uniqueId);
                        break;

                    case "FM.2":
                        processSimRunControl(identity, senderId, uniqueId, fields);
                        break;

                    case "FM.3":
                        processSetParameter(identity, senderId, uniqueId, fields);
                        break;

                    case "FM.4":
                        processSimStart(identity, senderId, uniqueId);
                        break;

                    case "FM.6":
                        processRequestStatistics(identity, senderId, uniqueId, fields);
                        break;

                    case "FS.3":
                        processKillFederate();
                        break;

                    default:
                        // wrong message
                        System.err.println("Received unknown message -- not processed: " + messageId);
                }
            }
            else
            {
                // wrong receiver
                System.err.println(
                        "Received message not intended for " + this.modelId + " but for " + receiverId + " -- not processed: ");
            }
        }
    }

    /**
     * Process FS.1 message and send MC.1 message back.
     * @param identity reply id for REQ-ROUTER pattern
     * @param receiverId the receiver of the response
     * @param replyToMessageId the message to which this is the reply
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    private void processRequestStatus(final String identity, final String receiverId, final long replyToMessageId)
            throws Sim0MQException, SerializationException
    {
        String status = "started";
        if (this.simulator.isRunning())
        {
            status = "running";
        }
        else if (this.simulator.getSimulatorTime() != null && this.simulator.getReplication() != null
                && this.simulator.getReplication().getTreatment() != null)
        {
            if (this.simulator.getSimulatorTime() >= this.simulator.getReplication().getTreatment().getEndTime())
            {
                status = "ended";
            }
            else
            {
                status = "error";
            }
        }
        this.fsSocket.sendMore(identity);
        this.fsSocket.sendMore("");
        byte[] mc1Message = Sim0MQMessage.encodeUTF8(this.federationRunId, this.modelId, receiverId, "MC.1",
                ++this.messageCount, replyToMessageId, status, "");
        this.fsSocket.send(mc1Message, 0);

        System.out.println("Sent MC.1");
        System.out.flush();
    }

    /**
     * Process FM.2 message and send MC.2 message back.
     * @param identity reply id for REQ-ROUTER pattern
     * @param receiverId the receiver of the response
     * @param replyToMessageId the message to which this is the reply
     * @param fields the message
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    private void processSimRunControl(final String identity, final String receiverId, final long replyToMessageId,
            final Object[] fields) throws Sim0MQException, SerializationException
    {
        boolean status = true;
        String error = "";
        try
        {
            Object runTimeField = fields[7];
            if (runTimeField instanceof Number)
            {
                this.runTime = new Duration(((Number) fields[7]).doubleValue(), DurationUnit.SI);
            }
            else if (runTimeField instanceof Duration)
            {
                this.runTime = (Duration) runTimeField;
            }
            else
            {
                throw new Sim0MQException("runTimeField " + runTimeField + " neither Number nor Duration");
            }

            Object warmupField = fields[8];
            if (warmupField instanceof Number)
            {
                this.warmupTime = new Duration(((Number) fields[8]).doubleValue(), DurationUnit.SI);
            }
            else if (warmupField instanceof Duration)
            {
                this.warmupTime = (Duration) warmupField;
            }
            else
            {
                throw new Sim0MQException("warmupField " + warmupField + " neither Number nor Duration");
            }
        }
        catch (Exception e)
        {
            status = false;
            error = e.getMessage();
        }
        byte[] mc2Message = Sim0MQMessage.encodeUTF8(this.federationRunId, this.modelId, receiverId, "MC.2",
                ++this.messageCount, replyToMessageId, status, error);
        this.fsSocket.sendMore(identity);
        this.fsSocket.sendMore("");
        this.fsSocket.send(mc2Message, 0);

        System.out.println("Sent MC.2");
        System.out.flush();
    }

    /**
     * Process FM.3 message and send MC.2 message back.
     * @param identity reply id for REQ-ROUTER pattern
     * @param receiverId the receiver of the response
     * @param replyToMessageId the message to which this is the reply
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    private void processSimStart(final String identity, final String receiverId, final long replyToMessageId)
            throws Sim0MQException, SerializationException
    {
        boolean status = true;
        String error = "";
        try
        {
            Replication.TimeDouble<DEVSSimulatorInterface.TimeDouble> replication =
                    Replication.TimeDouble.create("rep1", 0.0, this.warmupTime.si, this.runTime.si, this.model);
            this.simulator.initialize(replication, ReplicationMode.TERMINATING);
            this.simulator.scheduleEventAbs(100.0, this, this, "terminate", null);

            this.simulator.start();
        }
        catch (Exception e)
        {
            status = false;
            error = e.getMessage();
        }

        byte[] mc2Message = Sim0MQMessage.encodeUTF8(this.federationRunId, this.modelId, receiverId, "MC.2",
                ++this.messageCount, replyToMessageId, status, error);
        this.fsSocket.sendMore(identity);
        this.fsSocket.sendMore("");
        this.fsSocket.send(mc2Message, 0);

        System.out.println("Sent MC.2");
        System.out.flush();
    }

    /**
     * Process FM.4 message and send MC.2 message back.
     * @param identity reply id for REQ-ROUTER pattern
     * @param receiverId the receiver of the response
     * @param replyToMessageId the message to which this is the reply
     * @param fields the message
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    private void processSetParameter(final String identity, final String receiverId, final long replyToMessageId,
            final Object[] fields) throws Sim0MQException, SerializationException
    {
        boolean status = true;
        String error = "";
        try
        {
            String parameterName = fields[7].toString();
            Object parameterValueField = fields[8];

            switch (parameterName)
            {
                case "seed":
                    this.model.seed = ((Number) parameterValueField).longValue();
                    break;

                case "iat":
                    this.model.iat = ((Number) parameterValueField).doubleValue();
                    break;

                case "servicetime":
                    this.model.serviceTime = ((Number) parameterValueField).doubleValue();
                    break;

                default:
                    status = false;
                    error = "Parameter " + parameterName + " unknown";
                    break;
            }
        }
        catch (Exception e)
        {
            status = false;
            error = e.getMessage();
        }

        byte[] mc2Message = Sim0MQMessage.encodeUTF8(this.federationRunId, this.modelId, receiverId, "MC.2",
                ++this.messageCount, replyToMessageId, status, error);
        this.fsSocket.sendMore(identity);
        this.fsSocket.sendMore("");
        this.fsSocket.send(mc2Message, 0);

        System.out.println("Sent MC.2");
        System.out.flush();
    }

    /**
     * Process FM.5 message and send MC.3 or MC.4 message back.
     * @param identity reply id for REQ-ROUTER pattern
     * @param receiverId the receiver of the response
     * @param replyToMessageId the message to which this is the reply
     * @param fields the message
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    private void processRequestStatistics(final String identity, final String receiverId, final long replyToMessageId,
            final Object[] fields) throws Sim0MQException, SerializationException
    {
        boolean ok = true;
        String error = "";
        String variableName = fields[7].toString();
        double variableValue = Double.NaN;
        try
        {
            switch (variableName)
            {
                case "dN.average":
                    variableValue = this.model.dN.getSampleMean();
                    break;

                case "uN.average":
                    variableValue = this.model.uN.getSampleMean();
                    break;

                case "qN.max":
                    variableValue = this.model.qN.getMax();
                    break;

                default:
                    ok = false;
                    error = "Parameter " + variableName + " unknown";
                    break;
            }
        }
        catch (Exception e)
        {
            ok = false;
            error = e.getMessage();
        }

        if (Double.isNaN(variableValue))
        {
            ok = false;
            error = "Parameter " + variableName + " not set to a value";
        }

        if (ok)
        {
            byte[] mc3Message = Sim0MQMessage.encodeUTF8(this.federationRunId, this.modelId, receiverId, "MC.3",
                    ++this.messageCount, replyToMessageId, variableName, variableValue);
            this.fsSocket.sendMore(identity);
            this.fsSocket.sendMore("");
            this.fsSocket.send(mc3Message, 0);

            System.out.println("Sent MC.3");
            System.out.flush();
        }
        else
        {
            byte[] mc4Message = Sim0MQMessage.encodeUTF8(this.federationRunId, this.modelId, receiverId, "MC.4",
                    ++this.messageCount, replyToMessageId, ok, error);
            this.fsSocket.sendMore(identity);
            this.fsSocket.sendMore("");
            this.fsSocket.send(mc4Message, 0);

            System.out.println("Sent MC.4");
            System.out.flush();
        }
    }

    /**
     * Process FS.3 message.
     */
    private void processKillFederate()
    {
        this.fsSocket.close();
        this.fsContext.destroy();
        this.fsContext.close();
        System.exit(0);
    }

    /** stop the simulation. */
    protected final void terminate()
    {
        System.out.println("average queue length = " + this.model.qN.getSampleMean());
        System.out.println("average queue wait   = " + this.model.dN.getSampleMean());
        System.out.println("average utilization  = " + this.model.uN.getSampleMean());
    }

    /**
     * @param args can be left empty
     * @throws SimRuntimeException on error
     * @throws RemoteException on error
     * @throws NamingException on error
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    public static void main(final String[] args)
            throws SimRuntimeException, RemoteException, NamingException, Sim0MQException, SerializationException
    {
        if (args.length < 2)
        {
            System.err.println("Use as MM1Queue41Application modelId sim0mqPortNumber");
            System.exit(-1);
        }

        String modelId = args[0];

        String sPort = args[1];
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

        new MM1Queue41Application(modelId, port);
    }

}
