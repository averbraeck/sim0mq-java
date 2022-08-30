# Java Source Code

## GIT Location

Source code can be checked out from the public git [https://github.com/averbraeck/sim0mq-java](https://github.com/averbraeck/sim0mq-java). Releases can be found at [https://github.com/averbraeck/sim0mq-java/releases](https://github.com/averbraeck/sim0mq-java/releases).


## Java API Documentation

Java API Documentation can be found at [https://sim0mq.org/docs/latest/apidocs/index.html](https://sim0mq.org/docs/latest/apidocs/index.html). Java API documentation of the underlying message structure can be found at [https://djutils.org/docs/latest/djutils-serialization/apidocs/index.html](https://djutils.org/docs/latest/djutils-serialization/apidocs/index.html). 


## Java Code Documentation

Java code Documentation can be found at [https://sim0mq.org/docs/latest](https://sim0mq.org/docs/latest). Java code documentation of the underlying message structure can be found at [https://djutils.org/docs/latest/djutils-serialization](https://djutils.org/docs/latest/djutils-serialization). 


## Package structure

SIM0MQ is divided into a number of packages:

* **org.sim0mq** with generic classes such as the `Sim0MQException`. 
* **org.sim0mq.federatestarter** provides a reference implementation of a _Federate Starter_.
* **org.sim0mq.federationmanager** provides an abstract implementation of a _Federation Manager_.
* **org.sim0mq.message** containing the basic message structures such as the abstract `Sim0MQMessage` and the abstract `Sim0MQReply`.
* **org.sim0mq.message.types** which defines some types in addition to the types included in [djutils-serialization](https://djutils.org/manual/djutils-serialization).
* **org.sim0mq.message.federatestarter** provides all messages sent by a _Federate Starter_.
* **org.sim0mq.message.federationmanager** provides all messages sent by a _Federation Manager_.
* **org.sim0mq.message.heartbeat** provides messages to establish whether federates are still alive.
* **org.sim0mq.message.modelcontroller** provides all messages sent by a _Model_ (e.g., status and statistics).
