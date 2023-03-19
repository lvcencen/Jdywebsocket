package com.jdywebsocket.controller;

import com.jdywebsocket.model.R;
import com.jdywebsocket.service.AppPushService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author lvChen
 * @date 2023/2/25 1:01
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/app")
public class PushController {

    @Autowired
    private AppPushService appPushService;



    /**
     * xiaomiPush
     * @param token
     * @return
     */
    @GetMapping("/xiaomiPush")
    public R xiaomiPush(String token){
        if (StringUtils.isEmpty(token)){
            return R.fail("token不能为空");
        }
        try {
            return appPushService.xiaomiPush(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    /**
     * iosPush
     * @param token
     * @return
     */
    @GetMapping("/iosPush")
    public R iosPush(String token,String body,String title){
        if (StringUtils.isEmpty(token)){
            return R.fail("token不能为空");
        }
        return appPushService.iosPush(token,body,title);

    }



}