# FS messages

## Messages from Federate Starter (FS)

The Federate Starter that is responsible to start en stop processes on a local node sends the following messages:
* <a href="#fs1">FS.1 RequestStatus</a> (to MC)
* <a href="#fs2">FS.2 FederateStarted</a> (to FM, in response to FM.1)
* <a href="#fs3">FS.3 KillModel</a> (to MC)
* <a href="#fs4">FS.4 FederateKilled</a> (to FM, in response to FM.8)
* <a href="#fs5">FS.5 FederatesKilled</a> (to FM, in response to FM.9)


## FS.1 RequestStatus (to MC) <a id="fs1"></a>

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |

This message is sent by the Federate Starter to the Model Controller until a “started” response is received from the Model. Since the message type id clarifies the function of this message and no information exchange is necessary, the payload field can be empty (number of fields = 0).

<br>

## FS.2 FederateStarted (to FM, in response to FM.1) <a id="fs2"></a>

Message sent by the Federate Starter to the Federation Manager in response to message FM.1.

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| instanceId | 8 | basic type (0-3,9,10) | The sender id of the model that was started or had an error while starting. This is exactly the same as the instanceId sent by the Federation Manager in the Start Federate message. |
| status | 9 | String(9,10) | A string that refers to the model status. Four options: “started”, “running”, “ended”, “error”. |
| modelPortNumber | 10 | int(2) | Port number of the model, so the FederateManager can connect to the model on this port for further simulation messages. Because of the fact that the short is stored as a 2's complement (-32768-32767), an int is used to store the port number (valid range 0-65535). |
| error | 11 | String(9,10) | If there is an error, this field contains the error message. Otherwise this field is an empty string. |
<br>


## FS.3 KillModel (to MC) <a id="fs3"></a>

The message is sent by the federate starter to a Model Controller. The number of extra fields is zero.

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
<br>


## FS.4 FederateKilled (to FM, in response to FM.8) <a id="fs4"></a>

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| instanceId | 8 | basic type (0-3,9,10) | Id to identify the model instance that was killed, e.g. the String "IDVV.14". |
| status | 9 | Boolean(6) | A boolean that indicates whether the federate has been successfully terminated. |
| error | 10 | String(9,10) | If ‘status’ is False, an error message that specifies the problem. Otherwise, an empty string. |
<br>


## FS.5 FederatesKilled (to FM, in response to FM.9) <a id="fs5"></a>

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| status | 8 | Boolean(6) | A boolean that indicates whether all federates have been successfully terminated. |
| error | 9 | String(9,10) | If ‘status’ is False, an error message that specifies the problem. Otherwise, an empty string. |
<br>

