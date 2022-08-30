# Heartbeat (v2)

Heartbeat is not a special component, but rather a set of messages that each REQ component can send to its REP-component(s) or ROUTER component(s) to guarantee two things:
1. Check that the connection is still alive, and the REP/ROUTER client is still listening;
2. Keep the TCP connection alive so it does not time out between to succesive states, which might take a long time.

The heartbeat is different from the FM.5 RequestStatus message, as it is not interested in the state of the _Model_, but rather in the state of the _connection to the Model Controller_. The heartbeat can also be used to check if a FederateStarter or a LoadBalancer is still up-and-running.

There are two heartbeat messages:

* HB.1 Heartbeat
* HB.2 Alive

The messages are quite simple, and do not have a payload. See [Heartbeat Messages](/sim0mq-messages/heartbeat-messages-v2) for more information about the messages. The Heartbeat message can be sent as often as required, e.g. once a minute or once every 10 seconds, depending on the speed by which a time-out or frozen / crashed component should be detected.


## Reference implementation in Java

TODO
