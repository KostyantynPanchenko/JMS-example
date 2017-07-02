# JMS-example
Simple JMS example using both Queue and Topic as Destination.

HOW TO:
This particulat example uses OpenMQ as Message Broker.
You need to add jms.jar and imq.jar to your buildpath. If you have installed instance of Glassfish4 these jars can be found in <YOUR_GLASSFIH_INSTALL_DIR>/mq/lib
Start imqbrokered.exe, then start imqadmin.exe (in bin folder of your local OpenMQ installation) and create new Broker. Then start connection to this broker and create one Queue - StockQueue, and one Topic - ChatTopic.
Compile and run code.
