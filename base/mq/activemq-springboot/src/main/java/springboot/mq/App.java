package springboot.mq;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableJms
public class App 
{
	
	
	@Bean
	public Queue queue() {
		return new ActiveMQQueue("demo_queue");
	}
	
    public static void main( String[] args )
    {
    	ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
    	

//        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        // Send a message with a POJO - the template reuse the message converter
//        System.out.println("Sending an email message.");
//        jmsTemplate.convertAndSend("demo_queue", "3141516");
    }
}
