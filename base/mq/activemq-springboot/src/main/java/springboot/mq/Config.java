package springboot.mq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class Config {
	
	@Value("${activemq.broker-url}")
	  private String brokerUrl;

	  @Bean
	  public ActiveMQConnectionFactory activeMQConnectionFactory() {
	    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
	    activeMQConnectionFactory.setBrokerURL(brokerUrl);

	    return activeMQConnectionFactory;
	  }

//	  @Bean
	  public CachingConnectionFactory cachingConnectionFactory() {
	    return new CachingConnectionFactory(activeMQConnectionFactory());
	  }

	  @Bean
	  public JmsTemplate jmsTemplate() {
	    return new JmsTemplate(cachingConnectionFactory());
	  }
	  
	  @Bean
	  public JmsMessagingTemplate jmsMessagingTemplate() {
		  return new JmsMessagingTemplate(jmsTemplate());
	  }

	
//	@Bean
//    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
//                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        // This provides all boot's default to this factory, including the message converter
//        configurer.configure(factory, connectionFactory);
//        // You could still override some of Boot's default if necessary.
//        return factory;
//    }
//
//    @Bean // Serialize message content to json using TextMessage
//    public MessageConverter jacksonJmsMessageConverter() {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("_type");
//        return converter;
//    }	
	
}
