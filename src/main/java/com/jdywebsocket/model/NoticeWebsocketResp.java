package com.jdywebsocket.model;

import lombok.Data;

import java.util.List;

/**
 * @author lvChen
 * @date 2023/1/5 22:10
 */
@Data
public class NoticeWebsocketResp<T> {

    /**
     * 消息类型
     */
    private String noticeType;

    /**
     * 消息体
     */
    private List<T> noticeInfo;
}