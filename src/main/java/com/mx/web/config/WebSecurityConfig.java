//package com.mx.web.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.access.channel.ChannelProcessingFilter;
//
//import com.mx.web.filter.LoginFilter;
//
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	LoginFilter loginFilter;
//
//	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.addFilterBefore(loginFilter, ChannelProcessingFilter.class);
//	}
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		logger.info("进入自定义UserDetailsService-->config");
//		auth.userDetailsService(userSecurity());
//	}
//
//	@Bean
//	protected UserSecurity userSecurity() throws Exception {
//		logger.info("进入自定义UserDetailsService-->userService");
//		return new UserSecurity();
//	}
//
//	@Bean
//	public org.springframework.security.access.event.LoggerListener eventLoggerListener() {
//		logger.info("org.springframework.security.access.event.LoggerListener");
//		org.springframework.security.access.event.LoggerListener eventLoggerListener = new org.springframework.security.access.event.LoggerListener();
//
//		return eventLoggerListener;
//	}
//}