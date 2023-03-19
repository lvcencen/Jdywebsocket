package com.jdywebsocket.websocket;

import com.alibaba.fastjson.JSONObject;
import com.jdywebsocket.model.LinkNotice;
import com.jdywebsocket.model.NoticeWebsocketResp;
import com.jdywebsocket.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author lvChen
 * @date 2023/1/5 22:00
 */
@ServerEndpoint("/ws/{userId}")
@Component
@Slf4j
public class WebSocketServer {

    //记录连接的客户端
    public static Map<String, Session> clients = new ConcurrentHashMap<>();

    public static Map<String, Set<String>> conns = new ConcurrentHashMap<>();

    private String userId;



    /**
     * 连接成功后调用的方法
     * @param session
     * @param userId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) throws IOException {

        this.userId = userId;
        clients.put(userId, session);

        Set<String> clientSet = conns.get(userId);
        if (clientSet==null){
            clientSet = new HashSet<>();
            conns.put(userId,clientSet);
        }
        clientSet.add(userId);
        log.info(userId + "连接开启！");
//        LinkNotice linkNotice = new LinkNotice();
//        linkNotice.setToken(userId);
//        linkNotice.setStatus(true);
//        session.getBasicRemote().sendText(JSONObject.toJSONString(linkNotice));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        log.info(this.userId + "连接断开！");
        clients.remove(this.userId);
    }

    /**
     * 判断是否连接的方法
     * @return
     */
    public static boolean isServerClose() {
        if (WebSocketServer.clients.values().size() == 0) {
            log.info("已断开");
            return true;
        }else {
            log.info("已连接");
            return false;
        }
    }

    /**
     * 发送给所有用户
     * @param noticeInfo
     */
    public static void sendMessage(List<Object> noticeInfo){
        NoticeWebsocketResp noticeWebsocketResp = new NoticeWebsocketResp();
        noticeWebsocketResp.setNoticeInfo(noticeInfo);
        sendMessage(noticeWebsocketResp);
    }


    /**
     * 发送给所有用户
     * @param noticeWebsocketResp
     */
    public static void sendMessage(NoticeWebsocketResp noticeWebsocketResp){
        String message = JSONObject.toJSONString(noticeWebsocketResp);
        for (Session session1 : WebSocketServer.clients.values()) {
            try {
                session1.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("群发异常：{}",e.getMessage(),e);
            }
        }
    }

    /**
     * 根据用户id发送给某一个用户
     * **/
    public static void sendMessageByUserId(UserDto userDto) {
        if (!StringUtils.isEmpty(userDto.getUserId())) {
            String message = JSONObject.toJSONString(userDto.getNoticeWebsocketResp());
            Set<String> clientSet = conns.get(userDto.getUserId());
            if (clientSet != null) {
                Iterator<String> iterator = clientSet.iterator();
                while (iterator.hasNext()) {
                    String sid = iterator.next();
                    Session session = clients.get(sid);
                    if (session != null) {
                        try {
                            session.getBasicRemote().sendText(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口"+this.userId+"的信息:"+message);
        UserDto userDto = new UserDto();
        userDto.setUserId(this.userId);
        NoticeWebsocketResp noticeWebsocketResp = new NoticeWebsocketResp();
        noticeWebsocketResp.setNoticeType("健康存活");
        userDto.setNoticeWebsocketResp(noticeWebsocketResp);
        sendMessageByUserId(userDto);

    }

    /**
     * 发生错误时的回调函数
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        log.info("错误");
        error.printStackTrace();
    }
}
