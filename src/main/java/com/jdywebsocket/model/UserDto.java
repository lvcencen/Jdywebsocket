package com.jdywebsocket.model;

import lombok.Data;

/**
 * @author lvChen
 * @date 2023/1/7 22:40
 */
@Data
public class UserDto {

    private String userId;

    private NoticeWebsocketResp noticeWebsocketResp;
}