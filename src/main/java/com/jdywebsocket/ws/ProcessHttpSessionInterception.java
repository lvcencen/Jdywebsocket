//package com.jdywebsocket.test;
//
//import com.jdywebsocket.enums.AttributeConstant;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.stereotype.Service;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.Enumeration;
//import java.util.Map;
//
///**
// * @author yanyj
// * @version 创建时间：2019年1月15日 下午2:47:40
// * @ClassName ProcessHttpSessionInterception.java
// * @Description 握手前先验证是否已经登录
// */
//@Service
//public class ProcessHttpSessionInterception extends HttpSessionHandshakeInterceptor implements AttributeConstant {
//	@Autowired
//	HttpServletRequest httpServletRequest;
//
//
//
//	@Override
//	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
//			Map<String, Object> attributes) throws Exception {
//
//
//
//
//		return true;
//	}
//
//
//	public HttpSession getSession(ServerHttpRequest request) {
//		if (request instanceof ServletServerHttpRequest) {
//			ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
//			return serverRequest.getServletRequest().getSession(isCreateSession());
//		}
//		return null;
//	}
//
//	@Override
//	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
//			Exception ex) {
//	}
//}
