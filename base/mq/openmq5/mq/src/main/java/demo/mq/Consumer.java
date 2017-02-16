package demo.mq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.sun.messaging.BasicQueue;
import com.sun.messaging.ConnectionFactory;

/**
 * @author guihao
 * @date 2017年2月16日下午6:12:35
 * @desc 
 */
public class Consumer {
    public static void main(String[] args) {
    	String queueName = "AA";
    	try {
			consume(queueName);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
    
    public static void consume(String queueName) throws JMSException{
    	ConnectionFactory factory = new ConnectionFactory();
		Session session = null;
		Connection conn = null ;
		try {
			factory.setProperty(com.sun.messaging.ConnectionConfiguration.imqAddressList, "localhost:7676");
			conn = factory.createQueueConnection();
			conn.start();
			session = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
//			Destination dest = session.createQueue(queueName);
			MessageConsumer mc = session.createConsumer(new BasicQueue(queueName));
//			MessageConsumer mc = session.createConsumer(dest);
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
		}finally{
//			conn.close();
//			session.close();
		}
    }
}
