# MC messages

## Messages from Model (MC)

The Model's control loop (MC) sends the following messages:
* <a href="#mc1">MC.1 Status</a> (to FS, in response to FS.1; to FM in response to FM.5)
* <a href="#mc2">MC.2 AckNak</a> (to FM, in response to FM.2, FM.3, FM.4, FM.7)
* <a href="#mc3">MC.3 Statistics</a> (to FM, in response to FM.6)
* <a href="#mc4">MC.4 StatisticsError</a> (to FM, in response to FM.6)


## MC.1 Status (to FS, in response to FS.1; to FM in response to FM.5) <a id="mc1"></a>

The Model sends this message as a response to RequestStatus messages sent by the Federate Starter or the Federation Manager.

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| uniqueId | 8 | any type (0-3,9,10) | The unique message id (Frame 5) of the sender for which this is the reply. |
| status | 9 | String(9,10) | A string that refers to the model status. Four options: <br/> “started”, “running”, “ended”, “error”. |
| error | 10 | String(9,10) | If ‘status’ is "error", an error message that indicates what went wrong and why. Otherwise, an empty string. |
<br>


## MC.2 AckNak (to FM, in response to FM.2, FM.3, FM.4, FM.7) <a id="mc2"></a>

Message sent by the Model to acknowledge the reception and implementation of a message sent by the Federation Manager.

This type of message is sent in response to many messages of the FM. That could create confusion if there were multiple model instances, and one sending an acknowledgement e.g.for SimRunControl, the other for SetParameter. However, since a different port number will be assigned to each model and these acknowledgment messages will be sent only after a command, and include the uniqueId of the request, such a confusion is not expected.

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| uniqueId | 8 | any type (0-3,9,10) | The unique message id (Frame 5) of the sender for which this is the reply. |
| status | 9 | Boolean(6) | A boolean that indicates whether the command sent by the FM has been successfully implemented, e.g. whether the run control parameters are set successfully. |
| error | 10 | String(9,10) | If ‘status’ is False, an error message that indicates which parameter could not be set and why. Otherwise, an empty string. |
<br>


## MC.3 Statistics (to FM, in response to FM.6) <a id="mc3"></a>

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| variableName | 8 | String(9,10) | The name of the output variable whose value is requested. That should match with the name in the model. |
| variableValue | 9 | Any type (0-32) | If variableType is scalar, the data type is e.g., an integer, float etc. and the value generated in the model.<br/> If variableType is timeseries, the data type is an ‘array’ (type 11-16 or 27/28) or a time series (type 31/32). |
<br>


## MC.4 StatisticsError (to FM, in response to FM.6) <a id="mc4"></a>

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| variableName | 8 | String(9,10) | The name of the output variable whose value is requested. That should match with the name in the model. |
| error | 9 | String(9,10) | Three types of error can occur: <br/> - If the variableName does not exist in the model, error = “name”  <br/> - If the simulation did not generate a value for this variable, e.g. NaN or division by zero, error= “novalue” |
<br>
