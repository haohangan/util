package demo.mq;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import com.sun.messaging.ConnectionFactory;

/**
 * @author guihao
 * @date 2017年2月17日下午5:50:19
 * @desc 
 */
public class TopicTest {
    public static void main(String[] args) throws JMSException {
    	String topicName = "TOPIC";
    	read(topicName);
    	send(topicName);
	}
    
    static void send(String topicName) throws JMSException{
    	ConnectionFactory factory = new ConnectionFactory();
		Session session = null;
		Connection conn = null ;
		try {
			factory.setProperty(com.sun.messaging.ConnectionConfiguration.imqAddressList, "localhost:7676");
			conn = factory.createQueueConnection();
			conn.start();
			session = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(topicName);
			MessageProducer msgProducer = session.createProducer(topic);
			msgProducer.send(session.createTextMessage("111111"));
		}catch(JMSException e){
			e.printStackTrace();
		}finally{
			conn.close();
			session.close();
		}
    }
    
    static void read(String topicName) throws JMSException{
    	ConnectionFactory factory = new ConnectionFactory();
		Session session = null;
		Connection conn = null ;
		try {
			factory.setProperty(com.sun.messaging.ConnectionConfiguration.imqAddressList, "localhost:7676");
			conn = factory.createQueueConnection();
			conn.start();
			session = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(topicName);
			MessageConsumer msgConsumer = session.createConsumer(topic);
			msgConsumer.setMessageListener(new MessageListener() {
				
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
		}catch(JMSException e){
			e.printStackTrace();
		}finally{
//			conn.close();
//			session.close();
		}
    }
}
