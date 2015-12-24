package com.mx.web;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.mx.web.bean.User;
import com.mx.web.redis.Receiver;
import com.mx.web.service.UserRepository;

@SpringBootApplication//启动自动配置,扫描
public class Application implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private UserRepository repository;
	
	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

		return container;
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	@Bean
	Receiver receiver(CountDownLatch latch) {
		return new Receiver(latch);
	}

	@Bean
	CountDownLatch latch() {
		return new CountDownLatch(1);
	}

	@Bean
	StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}
	
    public static void main(String[] args) throws InterruptedException {
    	
    	ApplicationContext ctx = SpringApplication.run(Application.class, args);
    	
    	StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
		CountDownLatch latch = ctx.getBean(CountDownLatch.class);
		
		logger.info("Sending message...");
		template.convertAndSend("chat", "Hello from Redis!");

		latch.await();

//		System.exit(0);
    }
    
    @Override
	public void run(String... args) throws Exception {

		repository.deleteAll();

		repository.save(new User("imx", "123"));
		repository.save(new User("imxood", "123456"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (User customer : repository.findAll()) {
			System.out.println(customer.getUsername());
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('imx'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByUsername("imx"));

	}
}
