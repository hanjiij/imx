package com.mx.web.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;

import com.mx.web.data.Greeting;
import com.mx.web.data.HelloMessage;

@Controller
public class MessageController {

	private SessionRepository repository;
	
	@MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
}
