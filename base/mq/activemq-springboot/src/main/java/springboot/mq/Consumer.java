package springboot.mq;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@JmsListener(destination = "demo_queue")
	public void receiveQueue(String text) {
		System.out.println(text);
	}

}
