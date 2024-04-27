# Java: Maven Use

## Maven Use for Java

Maven is one of the easiest ways to include Sim0MQ in a Java project. The Maven files for Sim0MQ reside at Maven Central. When a POM-file is created for the project, the following snippet needs to be included to include Sim0MQ:

```xml
<dependencies>
  <dependency>
    <groupId>org.sim0mq</groupId>
    <artifactId>sim0mq</artifactId>
    <version>2.1.2</version>
  </dependency>
</dependencies>
```

Of course, the version number (2.1.2 in the above example) needs to be replaced with the version that one wants to include in the project.


## Java version

Note that as of sim0mq v2.1.2, Java-17 is needed. Up to version 2.1.1, Java-11 was sufficient, but premier support for Java-11 has already stopped in 2023. Therefore, we upgraded to Java-17.


## Dependencies

SIM0MQ is directly dependent on four packages, which have a limited set of further dependencies:

* [jeromq](https://github.com/zeromq/jeromq) for a native Java implementation of ZeroMQ. jeromq is dependent on jnacl for several cryptographic primitives.
* [dunits](https://djunits.org/manual) for the use of units. djunits is dependent on jakarta-annotations-api.
* [djutils](https://djutils.org/manual/) for the use of several helper classes. djutils is dependent on tinylog.
* [djutils-serialization](https://djutils.org/manual/djutils-serialization) for the (de)serialization classes.

If the Sim0MQ library is used as a part of a Maven or Gradle project, all dependencies will be automatically resolved, and the programmer / user does not have to worry about finding the other libraries.
