# Introduction

Several components have been created to help in easy setting up of simulations that use the Sim0MQ message bus. Examples are a **Model Controller**, a **Federation Manager** and a **Federate Starter**. 

The **Federation Manager (FM)** is a component that orchestrates the execution of one or more models. This could be an experiment manager that varies parameters over given ranges and collects the results, an optimizer that does the same but tries to set the input parameters such that the models outputs are maximized / minimized, or an [EMA Workbench](https://emaworkbench.readthedocs.io/en/latest/) (Exploratory Modeling and Analysis) that carries out a Monte Carlo analysis of a model with deeply uncertain parameters.

The **Model** is controlled by Sim0MQ messages rather than by an interactive user interface. Therefore, each model needs a **Model Controller (MC)** in addition to the model logic, which is responsibe for receiving and sending nstructions from the Federation Manager to execute its model logic according to specifications.

The **Federate Starter (FS)** is a lightweight executable to start a federate on a local node as a (sub)process. The Federate Starter listens to external messages, e.g. from a Federation Manager. The Federation Manager sends messages to Federate Starters to start model components, loggers, data collectors, etc. It can start a federation as soon as all required model components are on-line. It might kill model components that are finished, or that take too long to finish.

The **Load Balancer (LB)** takes care of distributing multiple models over different nodes or processors in a network. Each node has its own FederateStarter to start models. The LoadBalancer could be linked to e.g., Kubernetes to be able to create new nodes on the network.

The **Web Server (WS)** is an extension of the Federation Manager, that serves multiple web browser sessions with models that are executed on the network. In addition to the remote control of the model that the Federation Manager offers, the WS messages also take care of informing the user about the state of the model, such as parameter settings, animation, and graphs.

The **Time Manager** offers light-weight control for time synchronization for distributed model components, where the model components are executed in a relaxed time synchronization fashion using a lookahead function. 


!!! Note
    The Load Balancer, Web Server and Time Manager are yet been available with a reference implementation.