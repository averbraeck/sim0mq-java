# HB messages (v2)

## Heartbeat Messages

The following messages are Heartbeat ones:

* <a href="#hb1">HB.1 Heartbeat</a> (From FM to FS, LB and MC, or from FS to MC)
* <a href="#hb2">HB.2 Alive</a> (to FM or FS, in response to HB.1)


## HB.1 Heartbeat (From FM to FS, LB and MC, or from FS to MC) <a id="hb1"></a>

The HB.1 message does not have any extra field. Just the first 8 fields with 0 fields for the payload.


## HB.2 Alive (to FM or FS, in response to HB.1) <a id="hb2"></a>

The Alive reply message has one field:

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| uniqueId | 8 | basic type (0-3,9,10) | The unique message id (Frame 5) of the sender for which this is the reply. |

