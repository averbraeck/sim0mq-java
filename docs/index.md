# DSOL - Distributed Simulation Object Library

## Introduction

Sim0MQ makes use of the [ØMQ](http://zeromq.org/) (or 0MQ or ZMQ) message bus, and contains a layer of simulation-specific components and messages to aid in creating distributed simulation execution. The Sim0MQ message bus is a fast messaging protocol to create loosely coupled simulations.

* Sim0MQ uses the [0MQ](http://zeromq.org/) protocol as its basis, enabling up to 2 million messages per second.
* Sim0MQ provides low-level binary messages for fast message exchange; details are described in [djutils-serialization](https://djutils.org/manual/djutils-serialization/index.html).
* Sim0MQ provides structured binary messages for more controlled message exchange.
* Sim0MQ can be used with any language that 0MQ supports: Java, C, C++, C#, Python, and a few dozen more. Reference implementations in pure Java and Python exist.
* Sim0MQ works in a brokerless fashion due to the nature of the underlying 0MQ protocol.
* Sim0MQ uses <a href="https://djunits.org/" target="_blank">DJUNITS</a>  for a strongly typed unit system for values. A length scalar cannot be added to a time scalar; if length is divided by time, a speed variable results. This is all checked at compile time rather than at run time.
* Sim0MQ can be linked to <a href="https://simulation.tudelft.nl/dsol" target="_blank">DSOL</a> as the underlying, powerful simulation platform. DSOL takes care of time advance mechanisms, discrete-event and continuous simulation, random streams, probability distribution functions, experiment management, etc.


## Origin

SIM0MQ was developed at [Delft University of Technology](https://www.tudelft.nl/) as part of the [Open Traffic Simulator](https://opentrafficsim.org/) project (started in 2014).

In November 2016 it became obvious that the simulation message bus developed for the Open Traffic Simulator were sufficiently mature to be used in other projects.

The main authors/contributors of the SIM0MQ project are Alexander Verbraeck, Peter Knoppers, Wouter Schakel, Jan Kwakkel, Ehab Al-Khannaq, and Sibel Eker.


## Documentation

Documentation can be found at [http://sim0mq.org/docs/latest](http://sim0mq.org/docs/latest). The standards for (de)serialization that Sim0MQ uses can be found at [https://djutils.org/manual/djutils-serialization/index.html](https://djutils.org/manual/djutils-serialization/index.html).


## More information about 0MQ / ZeroMQ

More information about ZeroMQ can be found on the [0MQ Website](http://zeromq.org/).
