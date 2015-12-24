/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mx.web.config;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import com.mx.web.bean.User;
import com.mx.web.service.UserRepository;

public class WebSocketConnectHandler<S> implements ApplicationListener<SessionConnectEvent> {
	private UserRepository repository;
	private SimpMessageSendingOperations messagingTemplate;

	public WebSocketConnectHandler(SimpMessageSendingOperations messagingTemplate, UserRepository repository) {
		super();
		this.messagingTemplate = messagingTemplate;
		this.repository = repository;
	}

	public void onApplicationEvent(SessionConnectEvent event) {
		MessageHeaders headers = event.getMessage().getHeaders();
		Principal user = SimpMessageHeaderAccessor.getUser(headers);
		if(user == null) {
			return;
		}
		String id = SimpMessageHeaderAccessor.getSessionId(headers);
		repository.save(new User(id, user.getName(), new Date()));
		messagingTemplate.convertAndSend("/topic/friends/signin", Arrays.asList(user.getName()));
	}
}