# Message structure

## Introduction

The messages for SIM0MQ are aimed at executing **distributed simulations**. This means that multiple models, or so-called **federates** (a logging component in a federation is not a model, but still gets messsages), have to  exchange information. The idea behind the SIM0MQ messages is that we build in some fault-tolerance, e.g., by clearly defining the sender and receiver of the message,and the type of each field in the message. In this way, we can check the validity of the message in the given circumstances. Furthermore, it is considered to be useful to know the message type, to avoid mistakes for parsing the wrong message. Although it adds a bit to the message overhead, the benefits of not parsing and interpreting a wrong message are clearly outweighing the transmission of a few bytes. In cases where many short messages of a certain type are sent, untyped messages could be preferred over typed simulation messages. Finally, when multiple simulations are running in parallel, it is important to know for which running simulation the message is intended. In case it gets delivered to the wrong simulation, it can be discarded and potentially, the mistake can be logged.

All fields are serialized with the standards as defined in the [djunits-serialization](https://djutils.org/manual/djutils-serialization) manual, which describes the encoding/decoding (aka serializing/deserializing, marshalling/unmarshalling) of different types of fields, such as primitives, vectors, matrices, strings, and scalars, vectors and matrices with units. In addition, [endianness](https://en.wikipedia.org/wiki/Endianness) is taken into account for the (de)coding of the fields in the messages.


## Structure

The message structure of a typical typed Sim0MQ simulation message looks as follows:

**Frame 0. Magic number** = |9|0|0|0|5|S|I|M|#|#| where ## stands for the version number, e.g., 01. The magic number is always coded as a UTF-8 String, so it always starts with a byte equal to 9.

**Frame 1. Endianness** = boolean _with the 1-byte prefix_ that indicates whether this message using little endian or big endian encoding. Big endian is encoded as **true**, and little endian as **false**.

**Frame 2. Federation id**. Federation ids can be provided in different types. Examples are a 64-bit long, or a String with a UUID number, a String with meaningful identification, or a short or an int with a simulation run number. In order to check whether the right information has been received, the id can be translated to a String and compared with an internal string representation of the required simulation run id. Typically we use a String to provide maximum freedom. In that case, the run id can be coded as UTF-8 or UTF-16.

**Frame 3. Sender id**. Sender ids can be provided in different types. Examples are a 64-bit long, or a String with a UUID number, a String with meaningful identification, or a short or an int with a sender id number. The sender id can be used to send back a message to the sender at some later time. Typically we use a String to provide maximum freedom. In that case, the sender id can be coded as UTF-8 or UTF-16.

**Frame 4. Receiver id**. Receiver ids can be provided in different types. Examples are a 64-bit long, or a String with a UUID number, a String with meaningful identification, or a short or an int with a receiver id number. The receiver id can be used to check whether the message is meant for us, or should be discarded (or an error can be sent if we receive a message not meant for us). Typically we use a String to provide maximum freedom. In that case, the receiver id can be coded as UTF-8 or UTF-16.

**Frame 5. Message type id**. Message type ids can be defined per type of simulation, and can be provided in different types. Examples are a String with a meaningful identification, or a short or an int with a message type number. For interoperability between different types of simulation, a String id with dot-notation (e.g., DSOL.1 for a simulator start message from DSOL or OTS.14 for a statistics message from OpenTrafficSim) would be preferred. In that case, the run id can be coded as UTF-8 or UTF-16.

**Frame 6. Message id**. An id that identifies the message, which can be used to send a reply to a message. Typically, message ids are unique per sender, but could also be unique globally, or reused once the earlier usage has expired. Often, message ids will be encoded as an int or long, but a UUID is also possible.

**Frame 7. Number of fields**. The number of fields in the payload is indicated to be able to check the payload and to avoid reading past the end. The number of fields can be encoded using byte, short, or int. A 16-bit positive short (including zero) is the standard encoding. It can also be an int or long, allowing for messages with a vast number of fields.

**Frame 8-n. Payload**, where each field has a 1-byte prefix denoting the type of field.

!!! Warning
    Note that the message structure of Sim0MQ version 2 differs from the message structure of Sim0MQ version 1 in the fact that the message type (originally field 6) has been removed. General messages don't indicate whether they denote a NEW, CHANGE or DELETE operation type, as this only applies to updating status of variables or entities in a model, e.g., for animation. Most messages do not concern NEW, CHANGE or DELETE operations and therefore do not need the special field. This makes version 1 messages **incompatible** with version 2 messages.

!!! Warning
    Note that as of version 2 of Sim0MQ the endianness holds for the entire message rather than per field. 


## Example coding standard

In the rest of the manual (and in the example implementation), the federation id, sender id, receiver id, and message id are coded as UTF-8 Strings using dot notation. Any other coding is also valid.

* The **federation id** is coded as a UTF-8 String with a short abbreviation for the type of simulation that is running, folllowed by a dot and the experiment id, followed by a dot and the replication id. An example for federation 'IDVV', experiment 4, replication 7 is "IDVV.4.7".
* The **sender id** is coded as a UTF-8 String with an abbreviation for the federate name, followed by a dot and number in case multiple federates (can) exist. The (single) Model Controller can therefore be coded as "MC.1".
* The **receiver id** is coded as a UTF-8 String with an abbreviation for the federate name, followed by a dot and number in case multiple federates (can) exist. The (first) Logging component can therefore be coded as "LOG.1".
* The **message type id** is coded as a UTF-8 String with an abbreviation for the originating type of federate, followed by a dot and number to distinguish the message types. An example is the message from the Federation Manager (FM) to start a federate on a node in the network, and is sent to the Federate Starter, which has the code FM.1. The Federate Starter answers with a message "FederateStarted" that has code FS.1.
* The **message id** is coded as a long that is unique per sender in the current execution of the federation.


## Message Example

Suppose we have a simulation called IDVV.14.2 in which a message to change the (double) simulation speed to the value 0.2 is sent, of which the message type is DSOL.3. The message is sent by "MC.1" and received by "MM1.4". Suppose the message number is 124. The whole message is sent using big endian encoding. Then the message looks as follows (note that the double representation of 0.2 is 0x3FC999999999999A):

~~~xml
|9|0|0|0|5|S|I|M|0|1|6|1|9|0|0|0|9|I|D|V|V|.|1|4|.|2| 
|9|0|0|0|4|M|C|.|1|9|0|0|0|5|M|M|1|.|4|9|0|0|0|6|D|S|O|L|.|3| 
|3|0|0|0|0|0|0|0|124|1|0|1| 
|5|0x3F|0xC9|0x99|0x99|0x99|0x99|0x99|0x9A|
~~~

or field by field:

~~~xml
0. Magic number:   |9|0|0|0|5|S|I|M|0|1|          (String, 5 chars, SIM01)
1. Endianness:     |6|1|                          (Boolean, true, Big Endian)
2. Federation id:  |9|0|0|0|9|I|D|V|V|.|1|4|.|2|  (String, 9 chars, IDVV14.2)
3. Sender id:      |9|0|0|0|4|M|C|.|1|            (String, 4 chars, MC.1)
4. Receiver id:    |9|0|0|0|5|M|M|1|.|4|          (String, 5 chars, MM1.4)
5. Message type:   |9|0|0|0|6|D|S|O|L|.|3|        (String, 6 chars, DSOL.3)
6. Message id:     |3|0|0|0|0|0|0|0|124|          (Long, 8 bytes, 124)
7. Nr of fields:   |1|0|1|                        (Short, value 1)
8. Payload:        |5|0x3F|0xC9|0x99|0x99|0x99|0x99|0x99|0x9A|  (Double, 0.2)
~~~
