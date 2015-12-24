package com.mx.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mx.web.redis.Receiver;

@Component
public class LoginFilter implements javax.servlet.Filter {

	private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

	@Value("${Access-Control-Allow-Origin}") // 这里的冒号加与不加感觉没区别
	String[] originProperties; // 这里竟然可以直接以数组接收以逗号分隔的多个属性

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		//在WebMvcConfigurerAdapter子类中设置了Access-Control-Allow-Origin配置:
		
		// 访问: http://127.0.0.1:8080/index.html(同域请求)
		// url1: http://127.0.0.1:8080/index.html 
		// url2: /index.html
		// url3: null (你的跨域请求的Access-Control-Allow-Origin配置,如果不是则为null)
		
		// 访问:http://127.0.0.1:8020/index.html(跨域请求)
		// url1: http://127.0.0.1:8020/index.html 
		// url2: /index.html 
		// url3: http://127.0.0.1:8020
		String url1 = httpRequest.getRequestURL().toString();
		String url2 = httpRequest.getServletPath();
		String url3 = httpRequest.getHeader("Origin");

		logger.info("访问来源是：{}, {}, {}", url1, url2, url3);
		// if (url3 != null) {
		// for (int i = 0; i < originProperties.length; i++) {
		// // System.out.println("允许跨域访问的来源是："+originProperties[i]);
		// if (url3.equals(originProperties[i])) {
		// httpResponse.setHeader("Access-Control-Allow-Origin", url3);
		// }
		// }
		// } else { // 对于无来源的请求(比如在浏览器地址栏直接输入请求的)，那就只允许我们自己的机器可以吧
		// httpResponse.setHeader("Access-Control-Allow-Origin",
		// "http://127.0.0.1");
		// }
		// httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET,
		// OPTIONS, DELETE, PUT,HEAD");
		// 请求来自哪个域，我就允许哪个域的来源，也就是说允许所有域访问服务，这也太不安全了
		// if(httpRequest.getHeader("Origin") != null){
		// httpResponse.setHeader("Access-Control-Allow-Origin", curOrigin);
		// }

		// 这句千万别忘，让Filter按默认方式处理请求和响应，如果没写，那么response里没有body
		chain.doFilter(request, response);

		// if (session.getAttribute("username") != null) {
		// context.log("身份认证通过，进入下一步处理 ");
		// chain.doFilter(request, response);
		// } else {
		// context.log("身份认证失败，直接返回");
		// res.sendRedirect("../failure.jsp");
		// }
	}

	@Override
	public void destroy() {
	}

}