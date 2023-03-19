//package com.jdywebsocket.test;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.AbstractWebSocketHandler;
//import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @author yanyj
// * @version 创建时间：2019年1月15日 下午1:27:30
// * @ClassName ProcessWebSocketHandler.java
// * @Description 类描述
// */
//@Service
//@Component
//public class ProcessWebSocketHandler extends AbstractWebSocketHandler {
//	static Logger logger = LoggerFactory.getLogger(ProcessWebSocketHandler.class);
//	/**
//	 * <sessionId, WebSocketSession>
//	 */
//	private static ConcurrentHashMap<String, WebSocketSession> socketMap = new ConcurrentHashMap<>();
//
//	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//
//	}
//
//	@Override
//	public void afterConnectionEstablished(WebSocketSession session) {
//		logger.info("[{}]建立连接成功", session.getAttributes().get("oporNo"));
//		Map< String, Object> map = session.getAttributes();
//
//	}
//
//	@Override
//	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
//		logger.warn("[{}]已经关闭连接...", session.getAttributes().get("oporNo"));
//	}
//
//	public static ConcurrentHashMap<String, WebSocketSession> getSocketMap() {
//		return socketMap;
//	}
//
//	public static void removeSession(String key){
//		socketMap.remove(key);
//	}
//
//}
