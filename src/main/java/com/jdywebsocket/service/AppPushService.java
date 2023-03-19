package com.jdywebsocket.service;

import com.jdywebsocket.model.R;

import java.io.IOException;

/**
 * @author lvChen
 * @date 2023/2/23 22:25
 */
public interface AppPushService {





    R xiaomiPush(String token) throws IOException;


    R iosPush(String token,String body,String title);


}
