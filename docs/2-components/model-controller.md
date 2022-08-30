# MC: Model Controller

Many models are interactive or controlled from the connand line to execute according to the modeler's specifications. When the model is part of a Sim0MQ federation, the model is started by the Federate Starter rather than from a development environment or via the command line. The control of the model is taken over by the Federation Manager that sets its parameters, starts the model, and reads the results. 

As a result, the model needs to be enhanced with code that is able to receive and send Sim0MQ messages. This is what the Model Controler code is for. 


## Java reference implementation

A reference implementation of Model Controller code exists for Java, where the model code that is controlled is a [DSOL](https://simulation.tudelft.nl/dsol/manual/) model. 

The core of the reference implementation is a thread that waits for messages to come in from the FederateStarter and the FederationManager, and process these messages. An example is:

```java
    protected void startListener(final int port) throws Sim0MQException, SerializationException
    {
        this.fsContext = new ZContext(1);
        this.fsSocket = this.fsContext.createSocket(SocketType.ROUTER);
        this.fsSocket.bind("tcp://*:" + port);

        while (!Thread.currentThread().isInterrupted())
        {
            // Wait for next request from the client -- first the identity (String) and the delimiter (#0)
            String identity = this.fsSocket.recvStr();
            this.fsSocket.recvStr();

            byte[] request = this.fsSocket.recv(0);
            Object[] fields = SimulationMessage.decode(request);

            this.federationRunId = fields[1];
            String senderId = fields[2].toString();
            String receiverId = fields[3].toString();
            String messageId = fields[4].toString();
            long uniqueId = ((Long) fields[5]).longValue();

            if (receiverId.equals(this.modelId))
            {
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
```

Each of the `process` methods handles the message and sends a reply to the sender. As an example, look at the method to process an FM.5 - RequestStatistics message. The method sends back an MC.3 Statistics, or an MC.4 StatisticsError message to the FederationManager. The field `this.model` contains a pointer to the running model, where statistics dN (a Tally of the waiting time for a server), qN (a Tally of the queue length), and uN (the time-weighed utilization of a server) of the model are maintained and calculated.

```java
    private void processRequestStatistics(final String identity, final String receiverId, final long replyToMessageId,
            final Object[] fields) throws Sim0MQException, SerializationException
    {
        boolean ok = true;
        String error = "";
        String variableName = fields[8].toString();
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
            byte[] mc3Message = SimulationMessage.encodeUTF8(this.federationRunId, this.modelId, receiverId, "MC.3",
                    ++this.messageCount, MessageStatus.NEW, replyToMessageId, variableName, variableValue);
            this.fsSocket.sendMore(identity);
            this.fsSocket.sendMore("");
            this.fsSocket.send(mc3Message, 0);
        }
        else
        {
            byte[] mc4Message = SimulationMessage.encodeUTF8(this.federationRunId, this.modelId, receiverId, "MC.4",
                    ++this.messageCount, MessageStatus.NEW, replyToMessageId, ok, error);
            this.fsSocket.sendMore(identity);
            this.fsSocket.sendMore("");
            this.fsSocket.send(mc4Message, 0);
        }
    }
```

Note that these messages follow the REQ-ROUTER pattern, so they explicitly send the identity to ensure that the right receiver will be informed.
