package com.jdywebsocket.controller;

import com.jdywebsocket.model.NoticeWebsocketResp;
import com.jdywebsocket.model.R;
import com.jdywebsocket.model.UserDto;
import com.jdywebsocket.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lvChen
 * @date 2023/1/7 22:29
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/ws/send")
public class WebSocketController {
    @Autowired
    private WebSocketServer webSocketServer;


    /**
     * 群发消息
     * @param noticeWebsocketResp
     * @return
     */
    @PostMapping("/group")
    public R group(@RequestBody NoticeWebsocketResp noticeWebsocketResp){
        WebSocketServer.sendMessage(noticeWebsocketResp);
        return R.ok();
    }


    @PostMapping("/someone")
    public R someone(@RequestBody UserDto userDto){
        WebSocketServer.sendMessageByUserId(userDto);
        return R.ok();
    }
}