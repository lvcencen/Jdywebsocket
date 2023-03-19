package com.jdywebsocket.service.impl;

import com.jdywebsocket.enums.ResultEnum;
import com.jdywebsocket.model.R;
import com.jdywebsocket.service.AppPushService;
import com.jdywebsocket.utils.IosPushUtils;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author lvChen
 * @date 2023/2/23 22:25
 */
@Slf4j
@Service
public class AppPushServiceImpl implements AppPushService {

//    @Resource
//    private SendDataMessage sendDataMessage;

    @Autowired
    private IosPushUtils iosPushUtils;


    @Override
    public R xiaomiPush(String token) throws IOException {
//        Constants.disableOkHttp3();
        Sender sender = new Sender("gBIhfbirumqdD51bPVydOw==");
        String messagePayload = "This is a message";
        String title = "新订单提醒";
        String description = "您有新的订单等待配送";
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayload)
                .restrictedPackageName("uni.UNI0E10694")
                .notifyType(1)     // 使用默认提示音提示
                .build();
        Result result = sender.send(message, "03YDkeByp5y+FfaQ1ZPj1yRhYsaNR30EaLiLSylAW1AsM5eqK+FugA8v0CyXDjOo", 3);
        log.info("Server response: ", "MessageId: " + result.getMessageId()
                + " ErrorCode: " + result.getErrorCode().toString()
                + " Reason: " + result.getReason());
        return R.ok();
    }

    @Override
    public R iosPush(String token,String body,String title) {
        try {
           R r = iosPushUtils.iosPush(token,body,title);
           return r;
        }catch (Exception e){
            log.error("Ios推送失败:{}",e.getMessage(),e);
            return R.instance(ResultEnum.FAST_FAIL);
        }
    }


    public static void main(String[] args) throws IOException {
        Constants.useOfficial();
        Sender sender = new Sender("gBIhfbirumqdD51bPVydOw==");
        String messagePayload = "This is a message";
        String title = "新订单提醒";
        String description = "您有新的订单等待配送";
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayload)
                .restrictedPackageName("uni.UNI0E10694")
                .notifyType(1)     // 使用默认提示音提示
                .build();
        Result result = sender.send(message, "03YDkeByp5y+FfaQ1ZPj1yRhYsaNR30EaLiLSylAW1AsM5eqK+FugA8v0CyXDjOo", 3);
        System.out.println(result);
        log.info("Server response: ", "MessageId: " + result.getMessageId()
                + " ErrorCode: " + result.getErrorCode().toString()
                + " Reason: " + result.getReason());
    }


}