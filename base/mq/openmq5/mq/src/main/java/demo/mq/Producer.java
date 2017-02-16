package demo.mq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.sun.messaging.BasicQueue;
import com.sun.messaging.ConnectionFactory;

/**
 * @author guihao
 * @date 2017年2月16日下午5:59:06
 * @desc
 */
public class Producer {
	public static void main(String[] args) {
		String queueName = "AA";
		try {
			produce(queueName);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public static void produce(String queueName) throws JMSException {
		ConnectionFactory factory = new ConnectionFactory();
		Session session = null;
		Connection conn = null ;
		try {
			factory.setProperty(com.sun.messaging.ConnectionConfiguration.imqAddressList, "localhost:7676");
			conn = factory.createQueueConnection();
			session = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
			MessageProducer mp = session.createProducer(new BasicQueue(queueName));
//			Destination dest = session.createQueue(queueName);
//			MessageProducer mp = session.createProducer(dest);
			conn.start();
			for(int i  = 0;i<100;i++){
				TextMessage message = session.createTextMessage("text:"+i);
				mp.send(message);
//				try {
//					Thread.sleep(1000*3);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}finally{
			conn.close();
			session.close();
		}
	}
}
