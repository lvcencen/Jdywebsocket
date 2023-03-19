package com.jdywebsocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Context;
import org.apache.tomcat.websocket.server.WsSci;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@Slf4j
@EnableWebSocket
@SpringBootApplication
public class JdyWebsocketApplication {

    public static void main(String[] args) {
        System.out.println("JdyWebsocket===================start");
        SpringApplication.run(JdyWebsocketApplication.class, args);
        System.out.println("JdyWebsocket===================end");
    }


}

