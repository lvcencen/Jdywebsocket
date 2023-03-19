package com.jdywebsocket.controller;

import com.jdywebsocket.model.NoticeWebsocketResp;
import com.jdywebsocket.model.R;
import com.jdywebsocket.model.UserDto;
import com.jdywebsocket.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lvChen
 * @date 2022/11/18 0:05
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {
//    @Autowired
//    private WebSocketServer webSocketServer;

    @GetMapping("/get")
    public R get(){
        return R.ok();
    }

    @GetMapping("/send")
    public R send(){
        UserDto userDto = new UserDto();
        WebSocketServer.sendMessage(Collections.singletonList("123123"));
        NoticeWebsocketResp noticeWebsocketResp = new NoticeWebsocketResp();
//        noticeWebsocketResp.setNoticeInfo(Collections.singletonList("啊哈哈哈哈哈哈"));
        userDto.setNoticeWebsocketResp(noticeWebsocketResp);
        WebSocketServer.sendMessageByUserId(userDto);
        NoticeWebsocketResp noticeWebsocketResp1 = new NoticeWebsocketResp();
        noticeWebsocketResp1.setNoticeInfo(Collections.singletonList("呵呵呵呵呵呵"));
        userDto.setNoticeWebsocketResp(noticeWebsocketResp1);
        WebSocketServer.sendMessageByUserId(userDto);
        return R.ok();
    }

    @GetMapping("/sendTest")
    public R sendTest(){
        System.out.println("调用");
        UserDto userDto = new UserDto();
//
//        List<String> s = new ArrayList<>();
//        s.add("列表新增1");

        NoticeWebsocketResp noticeWebsocketResp = new NoticeWebsocketResp();
//        noticeWebsocketResp.setNoticeInfo(s);
        noticeWebsocketResp.setNoticeType("1");
//        WebSocketServer.sendMessage(noticeWebsocketResp);

        userDto.setNoticeWebsocketResp(noticeWebsocketResp);
        userDto.setUserId("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMzA3MTA1MTE4MSJ9.NS8WPabPLH_kt24cKiO5GyPhO8kZN5p4cPf6si4W2G8");
        WebSocketServer.sendMessageByUserId(userDto);

        return R.ok();
    }
}