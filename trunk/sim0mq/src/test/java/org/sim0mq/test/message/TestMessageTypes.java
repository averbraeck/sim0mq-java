package org.sim0mq.test.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.djunits.unit.DurationUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vdouble.scalar.Energy;
import org.djunits.value.vdouble.scalar.Time;
import org.djunits.value.vfloat.scalar.FloatDuration;
import org.djunits.value.vfloat.scalar.FloatTime;
import org.djutils.serialization.SerializationException;
import org.junit.Test;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.MessageStatus;
import org.sim0mq.message.Sim0MQMessage;
import org.sim0mq.message.SimulationMessage;
import org.sim0mq.message.federatestarter.FS1RequestStatusMessage;
import org.sim0mq.message.federatestarter.FS2FederateStartedMessage;
import org.sim0mq.message.federatestarter.FS3KillModelMessage;
import org.sim0mq.message.federatestarter.FS4FederateKilledMessage;
import org.sim0mq.message.federatestarter.FS5FederatesKilledMessage;
import org.sim0mq.message.federationmanager.FM1StartFederateMessage;
import org.sim0mq.message.federationmanager.FM2SimRunControlMessage;
import org.sim0mq.message.federationmanager.FM3SetParameterMessage;
import org.sim0mq.message.federationmanager.FM4SimStartMessage;
import org.sim0mq.message.federationmanager.FM5RequestStatus;
import org.sim0mq.message.federationmanager.FM6RequestStatisticsMessage;
import org.sim0mq.message.federationmanager.FM7SimResetMessage;
import org.sim0mq.message.federationmanager.FM8KillFederateMessage;
import org.sim0mq.message.federationmanager.FM9KillAllMessage;

/**
 * Test the field types of the messages.
 * <p>
 * Copyright (c) 2013-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class TestMessageTypes
{
    /** */
    @SuppressWarnings({"javadoc", "checkstyle:javadocvariable", "checkstyle:visibilitymodifier"})
    class Header
    {
        public String simulationRunId = "TESTFED.12";

        public String senderId;

        public String receiverId;

        public long messageId = 1270L;
    }

    /**
     * Test Federation Manager (FM) message type classes one by one.
     * @throws Sim0MQException on encoding error
     * @throws SerializationException on serialization error
     */
    @SuppressWarnings("checkstyle:needbraces")
    @Test
    public void testMessageTypesFM() throws Sim0MQException, SerializationException
    {
        Header fmfs = new Header();
        fmfs.senderId = "FM.1";
        fmfs.receiverId = "FS.2";
        Header fmmc = new Header();
        fmmc.senderId = "FM.1";
        fmmc.receiverId = "MODEL.12";

        FM1StartFederateMessage fm1 = new FM1StartFederateMessage(fmfs.simulationRunId, fmfs.senderId, fmfs.receiverId,
                fmfs.messageId, "IDVV.14", "java8+", "-Xmx500M -jar", "C:/models/MM1/mm1.jar", "./model.properties",
                "C:/models/MM1", "", "out.txt", "err.txt", false, true, true);
        Object[] fm1o = fm1.createObjectArray();
        FM1StartFederateMessage fm1c = FM1StartFederateMessage.createMessage(fm1o, fmfs.receiverId);
        //@formatter:off
        FM1StartFederateMessage fm1d = new FM1StartFederateMessage.Builder()
                .setSimulationRunId(fmfs.simulationRunId)
                .setSenderId(fmfs.senderId)
                .setReceiverId(fmfs.receiverId)
                .setMessageId(fmfs.messageId)
                .setInstanceId("IDVV.14")
                .setSoftwareCode("java8+")
                .setArgsBefore("-Xmx500M -jar")
                .setModelPath("C:/models/MM1/mm1.jar")
                .setArgsAfter("./model.properties")
                .setWorkingDirectory("C:/models/MM1")
                .setRedirectStdin("")
                .setRedirectStdout("out.txt")
                .setRedirectStderr("err.txt")
                .setDeleteWorkingDirectory(false)
                .setDeleteStdout(true)
                .setDeleteStderr(true)
                .build();
        //@formatter:on
        testMessage(fm1, fm1o, fm1c, fm1d, fmfs, "FM.1");
        assertEquals("IDVV.14", fm1.getInstanceId());
        assertEquals("java8+", fm1.getSoftwareCode());
        assertEquals("-Xmx500M -jar", fm1.getArgsBefore());
        assertEquals("C:/models/MM1/mm1.jar", fm1.getModelPath());
        assertEquals("./model.properties", fm1.getArgsAfter());
        assertEquals("C:/models/MM1", fm1.getWorkingDirectory());
        assertEquals("", fm1.getRedirectStdin());
        assertEquals("out.txt", fm1.getRedirectStdout());
        assertEquals("err.txt", fm1.getRedirectStderr());
        assertEquals(false, fm1.isDeleteWorkingDirectory());
        assertEquals(true, fm1.isDeleteStdout());
        assertEquals(true, fm1.isDeleteStderr());

        Map<Object, Long> streamMap = new LinkedHashMap<>();
        streamMap.put("default", 1L);
        streamMap.put("generator", 2L);
        testFM2(new Duration(1.0, DurationUnit.DAY), new Duration(4.0, DurationUnit.HOUR),
                new Time(0.0, TimeUnit.EPOCH_J2000_1), streamMap);
        testFM2(new FloatDuration(1.0f, DurationUnit.DAY), new FloatDuration(4.0f, DurationUnit.HOUR),
                new FloatTime(0.0f, TimeUnit.EPOCH_J2000_1), streamMap);
        testFM2(24.0f, 4.0d, 48, new LinkedHashMap<>());

        FM3SetParameterMessage fm3 = new FM3SetParameterMessage(fmmc.simulationRunId, fmmc.senderId, fmmc.receiverId,
                fmmc.messageId, "EnergyParam", new Energy(1.67, EnergyUnit.GIGAWATT_HOUR));
        Object[] fm3o = fm3.createObjectArray();
        FM3SetParameterMessage fm3c = FM3SetParameterMessage.createMessage(fm3o, fmmc.receiverId);
        FM3SetParameterMessage fm3d = new FM3SetParameterMessage.Builder().setSimulationRunId(fmmc.simulationRunId)
                .setSenderId(fmmc.senderId).setReceiverId(fmmc.receiverId).setMessageId(fmmc.messageId)
                .setParameterName("EnergyParam").setParameterValue(new Energy(1.67, EnergyUnit.GIGAWATT_HOUR)).build();
        testMessage(fm3, fm3o, fm3c, fm3d, fmmc, "FM.3");
        assertEquals("EnergyParam", fm3.getParameterName());
        assertEquals(new Energy(1.67, EnergyUnit.GIGAWATT_HOUR), fm3.getParameterValue());

        FM4SimStartMessage fm4 = new FM4SimStartMessage(fmmc.simulationRunId, fmmc.senderId, fmmc.receiverId, fmmc.messageId);
        Object[] fm4o = fm4.createObjectArray();
        FM4SimStartMessage fm4c = FM4SimStartMessage.createMessage(fm4o, fmmc.receiverId);
        FM4SimStartMessage fm4d = new FM4SimStartMessage.Builder().setSimulationRunId(fmmc.simulationRunId)
                .setSenderId(fmmc.senderId).setReceiverId(fmmc.receiverId).setMessageId(fmmc.messageId).build();
        testMessage(fm4, fm4o, fm4c, fm4d, fmmc, "FM.4");

        FM5RequestStatus fm5 = new FM5RequestStatus(fmmc.simulationRunId, fmmc.senderId, fmmc.receiverId, fmmc.messageId);
        Object[] fm5o = fm5.createObjectArray();
        FM5RequestStatus fm5c = FM5RequestStatus.createMessage(fm5o, fmmc.receiverId);
        FM5RequestStatus fm5d = new FM5RequestStatus.Builder().setSimulationRunId(fmmc.simulationRunId)
                .setSenderId(fmmc.senderId).setReceiverId(fmmc.receiverId).setMessageId(fmmc.messageId).build();
        testMessage(fm5, fm5o, fm5c, fm5d, fmmc, "FM.5");

        FM6RequestStatisticsMessage fm6 = new FM6RequestStatisticsMessage(fmmc.simulationRunId, fmmc.senderId, fmmc.receiverId,
                fmmc.messageId, "WaitingTimeAvg");
        Object[] fm6o = fm6.createObjectArray();
        FM6RequestStatisticsMessage fm6c = FM6RequestStatisticsMessage.createMessage(fm6o, fmmc.receiverId);
        FM6RequestStatisticsMessage fm6d =
                new FM6RequestStatisticsMessage.Builder().setSimulationRunId(fmmc.simulationRunId).setSenderId(fmmc.senderId)
                        .setReceiverId(fmmc.receiverId).setMessageId(fmmc.messageId).setVariableName("WaitingTimeAvg").build();
        testMessage(fm6, fm6o, fm6c, fm6d, fmmc, "FM.6");
        assertEquals("WaitingTimeAvg", fm6.getVariableName());

        FM7SimResetMessage fm7 = new FM7SimResetMessage(fmmc.simulationRunId, fmmc.senderId, fmmc.receiverId, fmmc.messageId);
        Object[] fm7o = fm7.createObjectArray();
        FM7SimResetMessage fm7c = FM7SimResetMessage.createMessage(fm7o, fmmc.receiverId);
        FM7SimResetMessage fm7d = new FM7SimResetMessage.Builder().setSimulationRunId(fmmc.simulationRunId)
                .setSenderId(fmmc.senderId).setReceiverId(fmmc.receiverId).setMessageId(fmmc.messageId).build();
        testMessage(fm7, fm7o, fm7c, fm7d, fmmc, "FM.7");

        FM8KillFederateMessage fm8 =
                new FM8KillFederateMessage(fmfs.simulationRunId, fmfs.senderId, fmfs.receiverId, fmfs.messageId, "IDVV.28");
        Object[] fm8o = fm8.createObjectArray();
        FM8KillFederateMessage fm8c = FM8KillFederateMessage.createMessage(fm8o, fmfs.receiverId);
        FM8KillFederateMessage fm8d =
                new FM8KillFederateMessage.Builder().setSimulationRunId(fmfs.simulationRunId).setSenderId(fmfs.senderId)
                        .setReceiverId(fmfs.receiverId).setMessageId(fmfs.messageId).setInstanceId("IDVV.28").build();
        testMessage(fm8, fm8o, fm8c, fm8d, fmfs, "FM.8");
        assertEquals("IDVV.28", fm8.getInstanceId());

        FM9KillAllMessage fm9 = new FM9KillAllMessage(fmfs.simulationRunId, fmfs.senderId, fmfs.receiverId, fmfs.messageId);
        Object[] fm9o = fm9.createObjectArray();
        FM9KillAllMessage fm9c = FM9KillAllMessage.createMessage(fm9o, fmfs.receiverId);
        FM9KillAllMessage fm9d = new FM9KillAllMessage.Builder().setSimulationRunId(fmfs.simulationRunId)
                .setSenderId(fmfs.senderId).setReceiverId(fmfs.receiverId).setMessageId(fmfs.messageId).build();
        testMessage(fm9, fm9o, fm9c, fm9d, fmfs, "FM.9");
    }

    /**
     * Test Federation Starter (FS) message type classes one by one.
     * @throws Sim0MQException on encoding error
     * @throws SerializationException on serialization error
     */
    @SuppressWarnings("checkstyle:needbraces")
    @Test
    public void testMessageTypesFS() throws Sim0MQException, SerializationException
    {
        Header fsfm = new Header();
        fsfm.senderId = "FS.2";
        fsfm.receiverId = "FM.1";
        Header fsmc = new Header();
        fsmc.senderId = "FS.2";
        fsmc.receiverId = "MODEL.12";

        FS1RequestStatusMessage fs1 =
                new FS1RequestStatusMessage(fsmc.simulationRunId, fsmc.senderId, fsmc.receiverId, fsmc.messageId);
        Object[] fs1o = fs1.createObjectArray();
        FS1RequestStatusMessage fs1c = FS1RequestStatusMessage.createMessage(fs1o, fsmc.receiverId);
        FS1RequestStatusMessage fs1d = new FS1RequestStatusMessage.Builder().setSimulationRunId(fsmc.simulationRunId)
                .setSenderId(fsmc.senderId).setReceiverId(fsmc.receiverId).setMessageId(fsmc.messageId).build();
        testMessage(fs1, fs1o, fs1c, fs1d, fsmc, "FS.1");

        FS2FederateStartedMessage fs2 = new FS2FederateStartedMessage(fsfm.simulationRunId, fsfm.senderId, fsfm.receiverId,
                fsfm.messageId, "IDVV.12", "started", (short) 5012, "");
        Object[] fs2o = fs2.createObjectArray();
        FS2FederateStartedMessage fs2c = FS2FederateStartedMessage.createMessage(fs2o, fsfm.receiverId);
        FS2FederateStartedMessage fs2d = new FS2FederateStartedMessage.Builder().setSimulationRunId(fsfm.simulationRunId)
                .setSenderId(fsfm.senderId).setReceiverId(fsfm.receiverId).setMessageId(fsfm.messageId).setInstanceId("IDVV.12")
                .setStatus("started").setModelPort(5012).setError("").build();
        testMessage(fs2, fs2o, fs2c, fs2d, fsfm, "FS.2");
        assertEquals("IDVV.12", fs2.getInstanceId());
        assertEquals("started", fs2.getStatus());
        assertEquals((short) 5012, fs2.getModelPort());
        assertEquals("", fs2.getError());

        FS3KillModelMessage fs3 = new FS3KillModelMessage(fsmc.simulationRunId, fsmc.senderId, fsmc.receiverId, fsmc.messageId);
        Object[] fs3o = fs3.createObjectArray();
        FS3KillModelMessage fs3c = FS3KillModelMessage.createMessage(fs3o, fsmc.receiverId);
        FS3KillModelMessage fs3d = new FS3KillModelMessage.Builder().setSimulationRunId(fsmc.simulationRunId)
                .setSenderId(fsmc.senderId).setReceiverId(fsmc.receiverId).setMessageId(fsmc.messageId).build();
        testMessage(fs3, fs3o, fs3c, fs3d, fsmc, "FS.3");

        FS4FederateKilledMessage fs4 = new FS4FederateKilledMessage(fsfm.simulationRunId, fsfm.senderId, fsfm.receiverId,
                fsfm.messageId, "IDVV.12", true, "Model not found");
        Object[] fs4o = fs4.createObjectArray();
        FS4FederateKilledMessage fs4c = FS4FederateKilledMessage.createMessage(fs4o, fsfm.receiverId);
        FS4FederateKilledMessage fs4d = new FS4FederateKilledMessage.Builder().setSimulationRunId(fsfm.simulationRunId)
                .setSenderId(fsfm.senderId).setReceiverId(fsfm.receiverId).setMessageId(fsfm.messageId).setInstanceId("IDVV.12")
                .setStatus(true).setError("Model not found").build();
        testMessage(fs4, fs4o, fs4c, fs4d, fsfm, "FS.4");
        assertEquals("IDVV.12", fs4.getInstanceId());
        assertEquals(true, fs4.isStatus());
        assertEquals("Model not found", fs4.getError());

        FS5FederatesKilledMessage fs5 =
                new FS5FederatesKilledMessage(fsfm.simulationRunId, fsfm.senderId, fsfm.receiverId, fsfm.messageId, false, "");
        Object[] fs5o = fs5.createObjectArray();
        FS5FederatesKilledMessage fs5c = FS5FederatesKilledMessage.createMessage(fs5o, fsfm.receiverId);
        FS5FederatesKilledMessage fs5d =
                new FS5FederatesKilledMessage.Builder().setSimulationRunId(fsfm.simulationRunId).setSenderId(fsfm.senderId)
                        .setReceiverId(fsfm.receiverId).setMessageId(fsfm.messageId).setStatus(false).setError("").build();
        testMessage(fs5, fs5o, fs5c, fs5d, fsfm, "FS.5");
        assertEquals(false, fs5.isStatus());
        assertEquals("", fs5.getError());
    }

    /**
     * test the FM.2 message with different parameters.
     * @param runDuration run duration
     * @param warmupDuration warmup duration
     * @param offsetTime start time
     * @param streamMap streams
     * @throws NullPointerException on error
     * @throws Sim0MQException on error
     * @throws SerializationException on error
     */
    @SuppressWarnings("checkstyle:needbraces")
    private void testFM2(final Object runDuration, final Object warmupDuration, final Object offsetTime,
            final Map<Object, Long> streamMap) throws NullPointerException, Sim0MQException, SerializationException
    {
        Header fmmc = new Header();
        fmmc.senderId = "FM.1";
        fmmc.receiverId = "MODEL.12";
        FM2SimRunControlMessage fm2 = new FM2SimRunControlMessage(fmmc.simulationRunId, fmmc.senderId, fmmc.receiverId,
                fmmc.messageId, runDuration, warmupDuration, offsetTime, 0.5d, 10, streamMap.size(), streamMap);
        Object[] fm2o = fm2.createObjectArray();
        FM2SimRunControlMessage fm2c = FM2SimRunControlMessage.createMessage(fm2o, fmmc.receiverId);
        //@formatter:off
        FM2SimRunControlMessage.Builder builder = new FM2SimRunControlMessage.Builder()
            .setSimulationRunId(fmmc.simulationRunId)
            .setSenderId(fmmc.senderId)
            .setReceiverId(fmmc.receiverId)
            .setMessageId(fmmc.messageId)
            .setSpeed(0.5d)
            .setNumberReplications(10)
            .setStreamMap(streamMap);
        //@formatter:on
        if (runDuration instanceof Duration)
            builder.setRunDuration((Duration) runDuration);
        else if (runDuration instanceof FloatDuration)
            builder.setRunDurationFloat((FloatDuration) runDuration);
        else
            builder.setRunDurationNumber((Number) runDuration);
        if (warmupDuration instanceof Duration)
            builder.setWarmupDuration((Duration) warmupDuration);
        else if (warmupDuration instanceof FloatDuration)
            builder.setWarmupDurationFloat((FloatDuration) warmupDuration);
        else
            builder.setWarmupDurationNumber((Number) warmupDuration);
        if (offsetTime instanceof Time)
            builder.setOffsetTime((Time) offsetTime);
        else if (offsetTime instanceof FloatTime)
            builder.setOffsetTimeFloat((FloatTime) offsetTime);
        else
            builder.setOffsetTimeNumber((Number) offsetTime);

        FM2SimRunControlMessage fm2d = builder.build();
        testMessage(fm2, fm2o, fm2c, fm2d, fmmc, "FM.2");

        assertEquals(runDuration, fm2.getRunDuration().getObject());
        assertEquals(warmupDuration, fm2.getWarmupDuration().getObject());
        assertEquals(offsetTime, fm2.getOffsetTime().getObject());
        assertEquals(0.5d, fm2.getSpeed(), 0.0001);
        assertEquals(10, fm2.getNumberReplications());
        assertEquals(streamMap.size(), fm2.getStreamMap().size());
        assertEquals(streamMap.size(), fm2.getNumberRandomStreams());
        assertEquals(streamMap.keySet(), fm2.getStreamMap().keySet());
        assertEquals(new ArrayList<Long>(streamMap.values()), new ArrayList<Long>(fm2.getStreamMap().values()));
    }

    /**
     * @param message original message
     * @param objectArray object array from the message
     * @param messageCoded recoded message
     * @param messageBuild built message
     * @param headers expected headers
     * @param messageType message type
     * @throws Sim0MQException on error
     * @throws SerializationException on error
     */
    private void testMessage(final Sim0MQMessage message, final Object[] objectArray, final Sim0MQMessage messageCoded,
            final Sim0MQMessage messageBuild, final Header headers, final String messageType)
            throws Sim0MQException, SerializationException
    {
        testStandardFields(message, headers, messageType);
        byte[] bytes = message.createByteArray();
        assertEquals(8 + message.getNumberOfPayloadFields(), objectArray.length);
        testStandardFields(messageCoded, headers, messageType);
        Object[] codedObjects = messageCoded.createObjectArray();
        compareFields(objectArray, codedObjects);
        testStandardFields(messageBuild, headers, messageType);
        Object[] buildObjects = messageBuild.createObjectArray();
        compareFields(objectArray, buildObjects);
        Object[] decodedObjects = SimulationMessage.decode(bytes);
        compareFields(objectArray, decodedObjects);

        assertEquals("SIM01", message.getMagicNumber());
        assertEquals(headers.senderId, message.getSenderId());
        assertEquals(headers.messageId, message.getMessageId());
        assertEquals(headers.receiverId, message.getReceiverId());
        assertEquals(headers.simulationRunId, message.getSimulationRunId());
        assertEquals(messageType, message.getMessageTypeId());

        // test if the class has a (static) method getMessageType() that returns the right value
        try
        {
            Method staticMtId = message.getClass().getMethod("getMessageType", new Class<?>[] {});
            assertEquals(messageType, staticMtId.invoke(message, new Object[] {}));
        }
        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e)
        {
            throw new Sim0MQException(e);
        }
    }

    /**
     * test the standard fields of an FM message.
     * @param message the message to check
     * @param fm the fields.
     * @param messageType the expected message type
     */
    private void testStandardFields(final Sim0MQMessage message, final Header fm, final String messageType)
    {
        assertEquals(fm.simulationRunId, message.getSimulationRunId());
        assertEquals(fm.senderId, message.getSenderId());
        assertEquals(fm.receiverId, message.getReceiverId());
        assertEquals(fm.messageId, message.getMessageId());
        assertEquals(messageType, message.getMessageTypeId());
    }

    /**
     * @param o1 object array 1
     * @param o2 object array 2
     */
    private void compareFields(final Object[] o1, final Object[] o2)
    {
        assertEquals(o1.length, o2.length);
        for (int i = 0; i < o1.length; i++)
        {
            if (o1[i].getClass().equals(MessageStatus.class))
            {
                if (!o2[i].getClass().equals(MessageStatus.class) && !o2[i].getClass().equals(byte.class)
                        && !o2[i].getClass().equals(Byte.class))
                {
                    fail("field " + i + ", expected class: " + o1[i].getClass() + ", actual class: " + o2[i].getClass());
                }
            }
            else if (o2[i].getClass().equals(MessageStatus.class))
            {
                if (!o1[i].getClass().equals(MessageStatus.class) && !o1[i].getClass().equals(byte.class)
                        && !o1[i].getClass().equals(Byte.class))
                {
                    fail("field " + i + ", expected class: " + o1[i].getClass() + ", actual class: " + o2[i].getClass());
                }
            }
            else
            {
                assertEquals("field " + i + ", expected class: " + o1[i].getClass() + ", actual class: " + o2[i].getClass(),
                        o1[i].getClass(), o2[i].getClass());
                assertEquals("field " + i + ", expected value: " + o1[i].toString() + ", actual value: " + o2[i].toString(),
                        o1[i], o2[i]);
            }
        }
    }
}
