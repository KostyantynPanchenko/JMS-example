package yougetit.queue;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

public class MessageSender {

	public static void main(String[] args) {
		Session session = null;
		QueueConnection connection = null;
		
		try {
			ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
			factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://localhost:7676,mq://localhost:7676");
			
			connection = factory.createQueueConnection("admin", "admin");
			connection.start();
			
			session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Queue queue = session.createQueue("StockQueue");
			TextMessage message = session.createTextMessage("Buy 200 shares of IBM");
			
			MessageProducer producer = session.createProducer(queue);
			producer.send(message);
			System.out.println("Message was sent to Broker.");
			producer.close();
		} catch (JMSException e) {
			System.out.println("Something bad happened... We are sorry!");
		} finally {
			try {
				session.close();
				connection.close();
			} catch (JMSException e) {
				System.out.println("Could not close session or connection." + e.getMessage());
			}
		}
	}
}
