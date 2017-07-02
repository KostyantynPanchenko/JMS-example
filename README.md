# JMS-example
Simple JMS example using both Queue and Topic as Destination.

HOW TO:
This particulat example uses OpenMQ as Message Broker. 
To compile the cource code You need to add jms.jar and imq.jar to your buildpath. If you have installed instance of Glassfish4 on your machine these jars can be found in <YOUR_GLASSFISH_INSTALL_DIR>/mq/lib
Start imqbrokerd.exe, then start imqadmin.exe (both can be found in bin folder of your local OpenMQ installation) and create new Broker. Then start connection to this broker and create one Queue - StockQueue, and one Topic - ChatTopic.
Compile and run code.
