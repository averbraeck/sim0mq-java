# FS: Federate Starter

A Federate Starter is a small program or daemon that listens on a certain port and that can start federates such as models, loggers, data providers and other federation components on a local machine. A Federation Manager (e.g., a model controller or a workbench) sends a `FederateStart` message to the Federate Starter, which starts the federate and provides it with enough information so it can report back to the federate starter that the start has succeeded. After that, the Federate Starter reports back to the Federation Manager that the federate is on-line with a `FederateStarted` message. After that, the Federate Starter resumes listening on the port for new messages to start a federate. 

Starting a program as a subprocess is sometimes done by forking. The disadvantage of forking is that the newly started program 'inherits' the state of the federate starter. In this case, we want to start a program fresh, that is independent of the parent process. In other words, if the parent process (the Federate Starter) dies, the federates and models that have been started by it should keep working. In Java 1.7 or higher, this can be achieved by a `ProcessBuilder` class that takes care of setting the working directory, setting the environment variables, redirecting the standard i/o (stdin, stdout, stderr) of the program to be started, and starts the program. It only takes a few lines of coding.

One of the important things to take into account is that all arguments to the program need to be split as separate arguments, so `java -jar test.jar` should be split into 3 arguments. Another point of attention is the redirection of input and output. When it is not redirected, it might stay in a buffer within the program that consumes memory until it is full or until memory runs out. Therefore, adequate handling of `stdout` and `stderr` is needed.


## Reference implementation

A reference implementation of the FederateStarter has been made in Java and in Python. The Java FederateStarter can be found at [https://sim0mq.org/docs/latest/xref/org/sim0mq/federatestarter/FederateStarter.html](https://sim0mq.org/docs/latest/xref/org/sim0mq/federatestarter/FederateStarter.html). The Python FederateStarter can be found at [https://github.com/quaquel/bangladesh_roadtransport/blob/master/simulation_model/federatestarter.py](https://github.com/quaquel/bangladesh_roadtransport/blob/master/simulation_model/federatestarter.py).

The Java version takes care of not starting arbitrary programs (such as viruses, formatting the hard drive, etc.) by using a properties file that indicates the valid programs to use, and the path towards the executable of that program. An example of such a properties file is shown below:

~~~~
java=c:/app/jdk1.8.0_201/bin/java.exe
java7=c:/app/jdk1.8.0_201/bin/java.exe
java8=c:/app/jdk1.8.0_201/bin/java.exe
java7+=c:/app/jdk1.8.0_201/bin/java.exe
java8+=c:/app/jdk1.8.0_201/bin/java.exe
javax86=c:/app/jdk1.8.0_201/bin/java.exe
java7x86=c:/app/jdk1.8.0_201/bin/java.exe
java8x86=c:/app/jdk1.8.0_201/bin/java.exe
java7x86+=c:/app/jdk1.8.0_201/bin/java.exe
java8x86+=c:/app/jdk1.8.0_201/bin/java.exe
javax64=c:/app/jdk1.8.0_201/bin/java.exe
java7x64=c:/app/jdk1.8.0_201/bin/java.exe
java8x64=c:/app/jdk1.8.0_201/bin/java.exe
java7x64+=c:/app/jdk1.8.0_201/bin/java.exe
java8x64+=c:/app/jdk1.8.0_201/bin/java.exe
python=c:/app/Python36x64/python.exe
python2=c:/app/Python27x64/python.exe
python3=c:/app/Python36x64/python.exe
python2+=c:/app/Python27x64/python.exe
python3+=c:/app/Python36x64/python.exe
pythonx86=c:/app/Python36-32/python.exe
python2x86=c:/app/Python27/python.exe
python3x86=c:/app/Python36-32/python.exe
python2x86+=c:/app/Python27/python.exe
python3x86+=c:/app/Python36-32/python.exe
pythonx64=c:/app/Python36x64/python.exe
python2x64=c:/app/Python27x64/python.exe
python3x64=c:/app/Python36x64/python.exe
python2x64+=c:/app/Python27x64/python.exe
python3x64+=c:/app/Python36x64/python.exe
~~~~

This indicates the paths to use to start different versions of Java and Python. The "+" indicates the mentioned version and above. The use of a label to indicate the program to start has two advantages: One, it prevents arbitrary code from being executed (although any Java or Python program that is started could potentially do harm as well), and two, it can indicate the paths to the executables on the particular machine, as this can differ from machine to machine.

