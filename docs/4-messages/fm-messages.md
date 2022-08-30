# FM messages

The following messages have been defined for version 1 of the Sim0MQ information exchange:

## Messages from Federate Manager (FM)

The Federation Manager (e.g., EMA Workbench, Experiment Manager) sends the following messages:

* <a href="#fm1">FM.1 StartFederate</a> (to FS)
* <a href="#fm2">FM.2 SimRunControl</a> (to MC)
* <a href="#fm3">FM.3 SetParameter</a> (to MC)
* <a href="#fm4">FM.4 SimStart</a> (to MC)
* <a href="#fm5">FM.5 RequestStatus</a> (to MC)
* <a href="#fm6">FM.6 RequestStatistics</a> (to MC)
* <a href="#fm7">FM.7 SimReset</a> (to MC)
* <a href="#fm8">FM.8 KillFederate</a> (to FS)
* <a href="#fm9">FM.9 KillAll</a> (to FS)


## FM.1 StartFederate (to FS) <a id="fm1"></a>

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| instanceId | 8 | Any basic type (1-3,9,10) | Id to identify the callback to know which model instance has been started, e.g. the String "IDVV.14". The model instance will use this as its sender id. |
| softwareCode | 9 | String(9,10) | Code for the software to run, will be looked up in a table on the local computer to determine the path to start the software on that computer. Example: "java". The string cannot be empty. |
| argsBefore | 10 | String(9,10) | Arguments that the software needs, before the model file path and name; e.g. "–Xmx2G -jar" in case of a Java model. This String can be empty (0 characters). |
| modelPath | 11 | String(9,10) | The actual path on the target computer where the model resides, including the model that needs to be run. This String cannot be empty.
| argsAfter | 12 | String(9,10) | Arguments that the software or the model needs, after the model file path and name; e.g. arguments for the model itself to run like a data file or a data location . This String can be empty (0 characters), but usually we would want to send the port number(s) or a location where the model can find it as well as the name under which the model was registered. If the port number needs to be inserted in the args, use %p for the port number. |
| workingDirectory | 13 | String(9,10) | Full path on the target computer that will be used as the working directory. Some files may be temporarily stored there. If the working directory does not exist yet, it will be created. The string cannot be empty. |
| redirectStdin | 14 | String(9,10) | Place to get user input from in case a model asks for it (it shouldn't, by the way). The string can be empty (0 characters). |
| redirectStdout | 15 | String(9,10) | Place to send the output to that the model normally displays on the console. If this is not redirected, the memory buffer for the stdout might get full, and the model might stop as a result. On Linux systems, this often redirected to /dev/null. On Windows systems, this can e.g., be redirected to a file "out.txt" in the current working directory. For now, it has to be a path name (including /dev/null as being acceptable). If no full path is given, the filename is relative to the working directory. The string cannot be empty. |
| redirectStderr | 16 | String(9,10) | Place to send the error messages to that the model normally displays on the console. If this is not redirected, the memory buffer for the stderr might get full, and the model might stop as a result. On Linux systems, this often redirected to /dev/null. On Windows systems, this can e.g., be redirected to a file "err.txt" in the current working directory. For now, it has to be a path name (including /dev/null as being acceptable). If no full path is given, the filename is relative to the working directory. The string cannot be empty. |
| deleteWorkingDirectory | 17 | Boolean(6) | Whether to delete the working directory after the run of the model or not. |
| deleteStdout | 18 | Boolean(6) | Whether to delete the redirected stdout after running or not (in case it is stored in a different place than the working directory) |
| deleteStderr | 19 | Boolean(6) | Whether to delete the redirected stderr after running or not (in case it is stored in a different place than the working directory) |

For field 8, the _softwareCode_, a number of standard types of software to look up have the following codes:

* java for the latest Java version
* java7, java8, java7+, etc. for a specific version of Java
* python for the latest python version
* python2, python3, python2+, etc. for a specific version of Python
* if necessary, special Strings could be created for 32-bit and 64-bit versions of the software. Preferably, "x64" is added at the end of the String to denote a 64-bit version
* if a specific version is needed of software, either extra entries can be created, or the actual path on the computer can be specified instead of the type code.

!!! Note
    The softwareCode can also be used to start your own specific model. There are no formal rules what the softwareCode should represent. The above coding for certain programming languages is just one of the possible implementations. The same FM1. StartFederate message can be used to start completely different software / models as well.

<br>

## FM.2 SimRunControl (to MC) <a id="fm2"></a>

Message sent by the Federation Manager to the Model to initiate a simulation.

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| runTime | 8 | Any numeric type (0-5) or Float or Double with Unit (25, 26) of type Duration (25) | Duration of the run of a single replication, including the warmup time, if present. |
| warmupTime | 9 | Any numeric type (0-5) or Float or Double with Unit (25, 26) of type Duration (25) | Warmup time of the model in time units that the model uses. |
| offsetTime | 10 | Any numeric type (0-5) or Float or Double with Unit (25, 26) of type Time (26) | Offset of the time (e.g., a model time of 0 is the year 2016, or 1-1-2015). |
| speed | 11 | Double(5) | Speed as the number of times real-time the model should run; Double.INFINITY means as fast as possible. |
| nrReplications | 12 | Integer(2) | Number of replications for stochastic uncertainties in the model. |
| nrRandomStreams | 13 | Integer(2) | Number of random streams that follow |
| streamId.1 | 14 | basic type (0-3,9,10) | Identifier of random stream 1 |
| seed.1 | 15 | Long(3) | Seed for random stream 1 |
| ... | | | | 
| streamId.n | | basic type (0-3,9,10) | Identifier of random stream n |
| seed.n | | Long(3) | Seed for random stream n |
<br>

## FM.3 SetParameter (to MC) <a id="fm3"></a>

Message sent by the FederateManager to the Model for setting the parameter values. Parameters are set one by one (but can be a Vector or Matrix).

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| parameterName | 8 | String(9,10) | Name of the parameter as it is in the model. |
| parameterValue | 9 | any type (0-32) | Value of the parameter assigned for a specific simulation. The type depends on the parameter. It could, e.g., be long or double. |

If multiple parameters have to be set, multiple messages are sent. 

<br>

## FM.4 SimStart (to MC) <a id="fm4"></a>

Message sent by the Federation Manager to start the simulation.

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |

This message has no payload. The number of fields is zero.

<br>

## FM.5 RequestStatus (to MC) <a id="fm5"></a>

Message sent by the Federation Manager to enquire the status of the simulation. The answer to this message is MC.1 "Status".
Since the message type id clarifies the function of this message and no information exchange is necessary, the payload field can be empty.

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
<br>

## FM.6 RequestStatistics (to MC) <a id="fm6"></a>

Message sent by the Federation Manager to collect the output.

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| variableName | 8 | String(9,10) | The name of the output variable whose value is requested. That should match with the name in the model. For a tallied variable, several statistics are possible, e.g., average, variance, minimum, maximum, time series, etc. The name should clearly indicate what the Model Controller expects and what the model should produce. |
<br>

## FM.7 SimReset (to MC) <a id="fm7"></a>

Reset the model to its initial state. 

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |

<br>


## FM.8 KillFederate (to FS) <a id="fm8"></a>

Kill the given federate (including termination of the process on the computer / node / processor where the federate is running).

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| instanceId | 8 | Any basic type (1-3,9,10) | Id to identify the federate instance that has to be killed, e.g. the String "IDVV.14". |
<br>


## FM.9 KillAll (to FS) <a id="fm9"></a>

Kill all federates on that particular node that are still running.

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
<br>
