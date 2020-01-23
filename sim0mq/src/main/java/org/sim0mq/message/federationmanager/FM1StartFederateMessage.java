package org.sim0mq.message.federationmanager;

import org.djutils.exceptions.Throw;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.Sim0MQMessage;

/**
 * StartFederateMessage, FM.1. When it receives a StartFederate message, the Federate starter creates a process to run the model
 * with the specifications given in the message, such as the working directory, model file, output and error files etc. Creating
 * a model instance in this way also requires a port number, to which the model instance should bind as a ROUTER. This port
 * number is assigned by the Federate Starter. Federate Starter picks an available port from a range of ports on the machine it
 * is running (which must be open to outside connection) and gives this to the model as an argument. If the binding is not
 * successful, the Federate Starter creates generates a new port number.
 * <p>
 * Copyright (c) 2016-2020 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class FM1StartFederateMessage extends Sim0MQMessage
{
    /**
     * Id to identify the callback to know which model instance has been started, e.g. "IDVV.14". The model instance will use
     * this as its sender id.
     */
    private final Object instanceId;

    /**
     * Code for the software to run, will be looked up in a table on the local computer to determine the path to start the
     * software on that computer. Example: "java". If the softwarePath is defined, softwareCode can be an empty String (0
     * characters).
     */
    private final String softwareCode;

    /**
     * Arguments that the software needs, before the model file path and name; e.g. "–Xmx2G -jar" in case of a Java model. This
     * String can be empty (0 characters).
     */
    private final String argsBefore;

    /**
     * The actual path on the target computer where the model resides, including the model that needs to be run. This String
     * cannot be empty.
     */
    private final String modelPath;

    /**
     * Arguments that the software or the model needs, after the model file path and name; e.g. arguments for the model itself
     * to run like a data file or a data location . This String can be empty (0 characters), but usually we would want to send
     * the port number(s) or a location where the model can find it as well as the name under which the model was registered.
     */
    private final String argsAfter;

    /**
     * Full path on the target computer that will be used as the working directory. Some files may be temporarily stored there.
     * If the working directory does not exist yet, it will be created.
     */
    private final String workingDirectory;

    /** Place to get user input from in case a model asks for it (it shouldn't, by the way). */
    private final String redirectStdin;

    /**
     * Place to send the output to that the model normally displays on the console. If this is not redirected, the memory buffer
     * for the stdout might get full, and the model might stop as a result. On Linux systems, this often redirected to
     * /dev/null. On Windows systems, this can e.g., be redirected to a file "out.txt" in the current working directory. For
     * now, it has to be a path name (including /dev/null as being acceptable). If no full path is given, the filename is
     * relative to the working directory.
     */
    private final String redirectStdout;

    /**
     * Place to send the error messages to that the model normally displays on the console. If this is not redirected, the
     * memory buffer for the stderr might get full, and the model might stop as a result. On Linux systems, this often
     * redirected to /dev/null. On Windows systems, this can e.g., be redirected to a file "err.txt" in the current working
     * directory. For now, it has to be a path name (including /dev/null as being acceptable). If no full path is given, the
     * filename is relative to the working directory.
     */
    private final String redirectStderr;

    /** Whether to delete the working directory after the run of the model or not. */
    private final boolean deleteWorkingDirectory;

    /**
     * Whether to delete the redirected stdout after running or not (in case it is stored in a different place than the working
     * directory).
     */
    private final boolean deleteStdout;

    /**
     * Whether to delete the redirected stderr after running or not (in case it is stored in a different place than the working
     * directory).
     */
    private final boolean deleteStderr;

    /** the unique message id. */
    private static final String MESSAGETYPE = "FM.1";

    /** */
    private static final long serialVersionUID = 20170422L;

    /**
     * @param federationId the federation id can be coded using different types. Examples are two 64-bit longs indicating a
     *            UUID, or a String with a UUID number, a String with meaningful identification, or a short or an int with a
     *            simulation run number.
     * @param senderId The sender id can be used to send back a message to the sender at some later time.
     * @param receiverId The receiver id can be used to check whether the message is meant for us, or should be discarded (or an
     *            error can be sent if we receive a message not meant for us).
     * @param messageId The unique message number is meant to confirm with a callback that the message has been received
     *            correctly. The number is unique for the sender, so not globally within the federation.
     * @param instanceId Id to identify the callback to know which model instance has been started, e.g. "IDVV.14". The model
     *            instance will use this as its sender id.
     * @param softwareCode Code for the software to run, will be looked up in a table on the local computer to determine the
     *            path to start the software on that computer. Example: "java". If the softwarePath is defined, softwareCode can
     *            be an empty String (0 characters).
     * @param argsBefore Arguments that the software needs, before the model file path and name; e.g. "–Xmx2G -jar" in case of a
     *            Java model. This String can be empty (0 characters).
     * @param modelPath The actual path on the target computer where the model resides, including the model that needs to be
     *            run. This String cannot be empty.
     * @param argsAfter Arguments that the software or the model needs, after the model file path and name; e.g. arguments for
     *            the model itself to run like a data file or a data location . This String can be empty (0 characters), but
     *            usually we would want to send the port number(s) or a location where the model can find it as well as the name
     *            under which the model was registered.
     * @param workingDirectory Full path on the target computer that will be used as the working directory. Some files may be
     *            temporarily stored there. If the working directory does not exist yet, it will be created.
     * @param redirectStdin Place to get user input from in case a model asks for it (it shouldn't, by the way).
     * @param redirectStdout Place to send the output to that the model normally displays on the console. If this is not
     *            redirected, the memory buffer for the stdout might get full, and the model might stop as a result. On Linux
     *            systems, this often redirected to /dev/null. On Windows systems, this can e.g., be redirected to a file
     *            "out.txt" in the current working directory. For now, it has to be a path name (including /dev/null as being
     *            acceptable). If no full path is given, the filename is relative to the working directory.
     * @param redirectStderr Place to send the error messages to that the model normally displays on the console. If this is not
     *            redirected, the memory buffer for the stderr might get full, and the model might stop as a result. On Linux
     *            systems, this often redirected to /dev/null. On Windows systems, this can e.g., be redirected to a file
     *            "err.txt" in the current working directory. For now, it has to be a path name (including /dev/null as being
     *            acceptable). If no full path is given, the filename is relative to the working directory.
     * @param deleteWorkingDirectory Whether to delete the working directory after the run of the model or not.
     * @param deleteStdout Whether to delete the redirected stdout after running or not (in case it is stored in a different
     *            place than the working directory)
     * @param deleteStderr Whether to delete the redirected stderr after running or not (in case it is stored in a different
     *            place than the working directory)
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public FM1StartFederateMessage(final Object federationId, final Object senderId, final Object receiverId,
            final Object messageId, final String instanceId, final String softwareCode, final String argsBefore,
            final String modelPath, final String argsAfter, final String workingDirectory, final String redirectStdin,
            final String redirectStdout, final String redirectStderr, final boolean deleteWorkingDirectory,
            final boolean deleteStdout, final boolean deleteStderr) throws Sim0MQException, NullPointerException
    {
        this(new Object[] {Sim0MQMessage.VERSION, true, federationId, senderId, receiverId, MESSAGETYPE, messageId, 12,
                instanceId, softwareCode, argsBefore, modelPath, argsAfter, workingDirectory, redirectStdin, redirectStdout,
                redirectStderr, deleteWorkingDirectory, deleteStdout, deleteStderr});
    }

    /**
     * @param objectArray Object[]; the fields that constitute the message
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public FM1StartFederateMessage(final Object[] objectArray) throws Sim0MQException, NullPointerException
    {
        super(objectArray, 12, MESSAGETYPE);
        this.instanceId = objectArray[8];
        Throw.when(!(objectArray[9] instanceof String), Sim0MQException.class, "softwareCode (field 9) should be a String");
        this.softwareCode = objectArray[9].toString();
        Throw.when(!(objectArray[10] instanceof String), Sim0MQException.class, "argsBefore (field 10) should be a String");
        this.argsBefore = objectArray[10].toString();
        Throw.when(!(objectArray[11] instanceof String), Sim0MQException.class, "modelPath (field 11) should be a String");
        this.modelPath = objectArray[11].toString();
        Throw.when(!(objectArray[12] instanceof String), Sim0MQException.class, "argsAfter (field 12) should be a String");
        this.argsAfter = objectArray[12].toString();
        Throw.when(!(objectArray[13] instanceof String), Sim0MQException.class,
                "workingDirectory (field 13) should be a String");
        this.workingDirectory = objectArray[13].toString();
        Throw.when(!(objectArray[14] instanceof String), Sim0MQException.class, "redirectStdin (field 14) should be a String");
        this.redirectStdin = objectArray[14].toString();
        Throw.when(!(objectArray[15] instanceof String), Sim0MQException.class, "redirectStdout (field 15) should be a String");
        this.redirectStdout = objectArray[15].toString();
        Throw.when(!(objectArray[16] instanceof String), Sim0MQException.class, "redirectStdin (field 16) should be a String");
        this.redirectStderr = objectArray[16].toString();
        Throw.when(!(objectArray[17] instanceof Boolean), Sim0MQException.class,
                "redirectStdin (field 17) should be a Boolean");
        this.deleteWorkingDirectory = ((Boolean) objectArray[17]).booleanValue();
        Throw.when(!(objectArray[18] instanceof Boolean), Sim0MQException.class,
                "redirectStdout (field 18) should be a Boolean");
        this.deleteStdout = ((Boolean) objectArray[18]).booleanValue();
        Throw.when(!(objectArray[19] instanceof Boolean), Sim0MQException.class,
                "redirectStderr (field 19) should be a Boolean");
        this.deleteStderr = ((Boolean) objectArray[19]).booleanValue();
    }

    /**
     * @return instanceId
     */
    public final Object getInstanceId()
    {
        return this.instanceId;
    }

    /**
     * @return softwareCode
     */
    public final String getSoftwareCode()
    {
        return this.softwareCode;
    }

    /**
     * @return argsBefore
     */
    public final String getArgsBefore()
    {
        return this.argsBefore;
    }

    /**
     * @return modelPath
     */
    public final String getModelPath()
    {
        return this.modelPath;
    }

    /**
     * @return argsAfter
     */
    public final String getArgsAfter()
    {
        return this.argsAfter;
    }

    /**
     * @return workingDirectory
     */
    public final String getWorkingDirectory()
    {
        return this.workingDirectory;
    }

    /**
     * @return redirectStdin
     */
    public final String getRedirectStdin()
    {
        return this.redirectStdin;
    }

    /**
     * @return redirectStdout
     */
    public final String getRedirectStdout()
    {
        return this.redirectStdout;
    }

    /**
     * @return redirectStderr
     */
    public final String getRedirectStderr()
    {
        return this.redirectStderr;
    }

    /**
     * @return deleteWorkingDirectory
     */
    public final boolean isDeleteWorkingDirectory()
    {
        return this.deleteWorkingDirectory;
    }

    /**
     * @return deleteStdout
     */
    public final boolean isDeleteStdout()
    {
        return this.deleteStdout;
    }

    /**
     * @return deleteStderr
     */
    public final boolean isDeleteStderr()
    {
        return this.deleteStderr;
    }

    /**
     * Builder for the StartFederate Message. Can string setters together, and call build() at the end to build the actual
     * message.
     * <p>
     * Copyright (c) 2016-2020 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
     * </p>
     * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
     */
    public static class Builder extends Sim0MQMessage.Builder<FM1StartFederateMessage.Builder>
    {
        /**
         * Id to identify the callback to know which model instance has been started, e.g. "IDVV.14". The model instance will
         * use this as its sender id.
         */
        private String instanceId;

        /**
         * Code for the software to run, will be looked up in a table on the local computer to determine the path to start the
         * software on that computer. Example: "java". If the softwarePath is defined, softwareCode can be an empty String (0
         * characters).
         */
        private String softwareCode;

        /**
         * Arguments that the software needs, before the model file path and name; e.g. "–Xmx2G -jar" in case of a Java model.
         * This String can be empty (0 characters).
         */
        private String argsBefore;

        /**
         * The actual path on the target computer where the model resides, including the model that needs to be run. This String
         * cannot be empty.
         */
        private String modelPath;

        /**
         * Arguments that the software or the model needs, after the model file path and name; e.g. arguments for the model
         * itself to run like a data file or a data location . This String can be empty (0 characters), but usually we would
         * want to send the port number(s) or a location where the model can find it as well as the name under which the model
         * was registered.
         */
        private String argsAfter;

        /**
         * Full path on the target computer that will be used as the working directory. Some files may be temporarily stored
         * there. If the working directory does not exist yet, it will be created.
         */
        private String workingDirectory;

        /** Place to get user input from in case a model asks for it (it shouldn't, by the way). */
        private String redirectStdin;

        /**
         * Place to send the output to that the model normally displays on the console. If this is not redirected, the memory
         * buffer for the stdout might get full, and the model might stop as a result. On Linux systems, this often redirected
         * to /dev/null. On Windows systems, this can e.g., be redirected to a file "out.txt" in the current working directory.
         * For now, it has to be a path name (including /dev/null as being acceptable). If no full path is given, the filename
         * is relative to the working directory.
         */
        private String redirectStdout;

        /**
         * Place to send the error messages to that the model normally displays on the console. If this is not redirected, the
         * memory buffer for the stderr might get full, and the model might stop as a result. On Linux systems, this often
         * redirected to /dev/null. On Windows systems, this can e.g., be redirected to a file "err.txt" in the current working
         * directory. For now, it has to be a path name (including /dev/null as being acceptable). If no full path is given, the
         * filename is relative to the working directory.
         */
        private String redirectStderr;

        /** Whether to delete the working directory after the run of the model or not. */
        private boolean deleteWorkingDirectory;

        /**
         * Whether to delete the redirected stdout after running or not (in case it is stored in a different place than the
         * working directory).
         */
        private boolean deleteStdout;

        /**
         * Whether to delete the redirected stderr after running or not (in case it is stored in a different place than the
         * working directory).
         */
        private boolean deleteStderr;

        /**
         * Empty constructor.
         */
        public Builder()
        {
            // nothing to do.
        }

        /**
         * @param newInstanceId set instanceId
         * @return the original object for chaining
         */
        public final Builder setInstanceId(final String newInstanceId)
        {
            this.instanceId = newInstanceId;
            return this;
        }

        /**
         * @param newSoftwareCode set softwareCode
         * @return the original object for chaining
         */
        public final Builder setSoftwareCode(final String newSoftwareCode)
        {
            this.softwareCode = newSoftwareCode;
            return this;
        }

        /**
         * @param newArgsBefore set argsBefore
         * @return the original object for chaining
         */
        public final Builder setArgsBefore(final String newArgsBefore)
        {
            this.argsBefore = newArgsBefore;
            return this;
        }

        /**
         * @param newModelPath set modelPath
         * @return the original object for chaining
         */
        public final Builder setModelPath(final String newModelPath)
        {
            this.modelPath = newModelPath;
            return this;
        }

        /**
         * @param newArgsAfter set argsAfter
         * @return the original object for chaining
         */
        public final Builder setArgsAfter(final String newArgsAfter)
        {
            this.argsAfter = newArgsAfter;
            return this;
        }

        /**
         * @param newWorkingDirectory set workingDirectory
         * @return the original object for chaining
         */
        public final Builder setWorkingDirectory(final String newWorkingDirectory)
        {
            this.workingDirectory = newWorkingDirectory;
            return this;
        }

        /**
         * @param newRedirectStdin set redirectStdin
         * @return the original object for chaining
         */
        public final Builder setRedirectStdin(final String newRedirectStdin)
        {
            this.redirectStdin = newRedirectStdin;
            return this;
        }

        /**
         * @param newRedirectStdout set redirectStdout
         * @return the original object for chaining
         */
        public final Builder setRedirectStdout(final String newRedirectStdout)
        {
            this.redirectStdout = newRedirectStdout;
            return this;
        }

        /**
         * @param rewRedirectStderr set redirectStderr
         * @return the original object for chaining
         */
        public final Builder setRedirectStderr(final String rewRedirectStderr)
        {
            this.redirectStderr = rewRedirectStderr;
            return this;
        }

        /**
         * @param newDeleteWorkingDirectory set deleteWorkingDirectory
         * @return the original object for chaining
         */
        public final Builder setDeleteWorkingDirectory(final boolean newDeleteWorkingDirectory)
        {
            this.deleteWorkingDirectory = newDeleteWorkingDirectory;
            return this;
        }

        /**
         * @param newDeleteStdout set deleteStdout
         * @return the original object for chaining
         */
        public final Builder setDeleteStdout(final boolean newDeleteStdout)
        {
            this.deleteStdout = newDeleteStdout;
            return this;
        }

        /**
         * @param newDeleteStderr set deleteStderr
         * @return the original object for chaining
         */
        public final Builder setDeleteStderr(final boolean newDeleteStderr)
        {
            this.deleteStderr = newDeleteStderr;
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public FM1StartFederateMessage build() throws Sim0MQException, NullPointerException
        {
            return new FM1StartFederateMessage(this.federationId, this.senderId, this.receiverId, this.messageId,
                    this.instanceId, this.softwareCode, this.argsBefore, this.modelPath, this.argsAfter, this.workingDirectory,
                    this.redirectStdin, this.redirectStdout, this.redirectStderr, this.deleteWorkingDirectory,
                    this.deleteStdout, this.deleteStderr);
        }

    }
}
