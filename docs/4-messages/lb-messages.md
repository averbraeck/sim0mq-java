# LB messages (v2)

## Messages between Load Balancer (LB) and Federation Manager (FM)

The Load Balancer exchanges the following messages with the Federation Manager:

* <a href="#lb1">LB.1 AllocateNode</a> (from FM to LB)
* <a href="#lb2">LB.2 Allocated</a> (from LB to FM, in response to LB.1)
* <a href="#lb3">LB.3 AllocationError</a> (from LB to FM, in response to LB.1)
* <a href="#lb4">LB.4 DeallocateNode</a> (from FM to LB)
* <a href="#lb5">LB.5 Deallocated</a> (from LB to FM, in response to LB.3)
* <a href="#lb6">LB.6 DeallocationError</a> (from LB to FM, in response to LB.3)


## LB.1 AllocateNode (from FM to LB) <a id="lb1"></a>

The Model sends this message as a response to RequestStatus messages sent by the Federate Starter or the Federation Manager.

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| modelId | 8 | String(9,10) | A unique indicator of the model for which allocation is asked |
| processors | 9 | Integer(2) | The number of processors to use for that process (often 1). |
| memory | 10 | String(9,10) | The amount of memory to reserve for the process, a number, possibly followed by kB, MB, GB, TB. |
<br>


## LB.2 Allocated (from LB to FM, in response to LB.1) <a id="lb2"></a>

Message sent by the Model to indicate which Federate Starter can be allocated for the request (or none).

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| modelId | 8 | String(9,10) | The model id for which allocation is granted. |
| nameIP | 9 | String(9,10) | The name or IP address of the node that has been alocated/reserved for the model process. |
| fsPort | 10 | Integer(2) | The port number of the FederateStarter on the node that has been alocated/reserved for the model process. |
<br>


## LB.3 AllocationError (from LB to FM, in response to LB.1) <a id="lb3"></a>

Message sent by the Model to indicate which Federate Starter can be allocated for the request (or none).

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| modelId | 8 | String(9,10) | The model id for which allocation is granted (or not). |
| error | 9 | String(9,10) | The reason the allocation could not take place, e.g.: <br/>- no node table.<br/>- no node with sufficient number of processors.<br/>- no node with sufficient available memory. |
<br>


## LB.4 DeallocateNode (from FM to LB) <a id="lb4"></a>

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| modelId | 8 | String(9,10) | The model id for which deallocation can take place. |
<br>


## LB.5 Deallocated (from LB to FM, in response to LB.4) <a id="lb5"></a>

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| modelId | 8 | String(9,10) | The model id for which deallocation has taken place. |
<br>


## LB.6 DeallocationError (from LB to FM, in response to LB.4) <a id="lb6"></a>

| Variable | Fld | Type | Comments |
| ----------- | ----- | ------ | -------------- |
| modelId | 8 | String(9,10) | The model id for which deallocation could not be executed. |
| error | 9 | String(9,10) | The error message belonging to the unsuccessful deallocation, e.g.:<br/>- memory would go below zero.<br/>- number of processes would go below zero.<br/>- modelId cannot be found (e.g., because deallocaton took place multiple times). |
<br>
