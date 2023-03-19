//package com.jdywebsocket.test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//import javax.annotation.Resource;
//
///**
//* @author yanyj
//* @version 创建时间：2019年1月15日 下午1:29:13
//* @ClassName WebSocketConfig.java
//* @Description 类描述
//*/
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//
//	@Autowired
//	ProcessHttpSessionInterception processHttpSessionInterception;
//
//	@Override
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		registry.addHandler(handler(), "/proc.do").setAllowedOrigins("*").addInterceptors(processHttpSessionInterception);
//	}
//
//	@Bean
//	public ProcessWebSocketHandler handler(){
//		return new ProcessWebSocketHandler();
//	}
//
//
//}
