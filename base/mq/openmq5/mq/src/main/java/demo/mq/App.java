package demo.mq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		String queueName = "asda";
		// produce();

//		consume();
		produce();
		asyncConsume(queueName);
	}

	private static void produce() {
		com.sun.messaging.ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
		try {
			factory.setProperty(com.sun.messaging.ConnectionConfiguration.imqAddressList, "localhost:7676");
			Connection conn = factory.createConnection();
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination dest = session.createQueue("asda");
			MessageProducer mp = session.createProducer(dest);
			TextMessage tm = session.createTextMessage();
			tm.setText("A test message");
			mp.send(tm);
			System.out.println("Message sent:");
			session.close();
			conn.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private static void consume() {
		com.sun.messaging.ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
		try {
			factory.setProperty(com.sun.messaging.ConnectionConfiguration.imqAddressList, "localhost:7676");
			Connection conn = factory.createConnection();
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination dest = session.createQueue("asda");
			MessageConsumer mc = session.createConsumer(dest);
			conn.start();
			TextMessage msg = (TextMessage) mc.receive();
			System.out.println("Received message: " + msg.getText());
			session.close();
			conn.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	private static void asyncConsume(String queueName) {
		com.sun.messaging.ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
		try {
			factory.setProperty(com.sun.messaging.ConnectionConfiguration.imqAddressList, "localhost:7676");
			Connection conn = factory.createConnection();
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination dest = session.createQueue(queueName);
			MessageConsumer mc = session.createConsumer(dest);
			conn.start();
//			TextMessage msg = (TextMessage) mc.receive();
//			System.out.println("Received message: " + msg.getText());
//			session.close();
//			conn.close();
			mc.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					TextMessage msg = (TextMessage) message;
					try {
						System.out.println("Received message: " + msg.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
