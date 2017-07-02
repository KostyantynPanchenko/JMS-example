package yougetit.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

public class MessageReceiver implements MessageListener {
	
	public MessageReceiver() {
		Session session = null;
		QueueConnection connection = null;
		
		try {
			ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
			factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://localhost:7676,mq://localhost:7676");
			
			connection = factory.createQueueConnection("admin", "admin");
			connection.start();
			
			session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Queue queue = session.createQueue("StockQueue");
			
			MessageConsumer consumer = session.createConsumer(queue);
			consumer.setMessageListener(this);
			
			System.out.println("Listening for messages...");
		} catch (JMSException e) {
			System.out.println("Something bad happened... We are sorry!");
		} finally {
			try {
				session.close();
				connection.close();
			} catch (JMSException e) {
				System.out.println("Could not close session or connection. " + e.getMessage());
			}
		}
	}
	
	public static void main(String[] args) {
		new MessageReceiver();
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			System.out.println("Something bad happened... We are sorry!");
		}
	}

	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ignored) {
				}
				System.out.println("Received: " + ((TextMessage) message).getText());
			} else {
				System.out.println("Non-text message received.");
			}
		} catch (JMSException e) {
			System.out.println("Something bad happened... We are sorry!");
		}		
	}

}
