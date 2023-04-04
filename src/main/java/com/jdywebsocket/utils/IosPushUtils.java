package com.jdywebsocket.utils;

import com.alibaba.fastjson.JSONObject;
import com.eatthepath.pushy.apns.ApnsClient;
import com.eatthepath.pushy.apns.ApnsClientBuilder;
import com.eatthepath.pushy.apns.PushNotificationResponse;
import com.eatthepath.pushy.apns.util.ApnsPayloadBuilder;
import com.eatthepath.pushy.apns.util.SimpleApnsPayloadBuilder;
import com.eatthepath.pushy.apns.util.SimpleApnsPushNotification;
import com.eatthepath.pushy.apns.util.TokenUtil;
import com.eatthepath.pushy.apns.util.concurrent.PushNotificationFuture;
import com.jdywebsocket.enums.ResultEnum;
import com.jdywebsocket.model.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


/**
 * @author lvChen
 * @date 2023/3/16 22:32
 */
@Slf4j
@Component
public class IosPushUtils {



    public R iosPush(String token,String body,String title) throws IOException {

        final ApnsClient apnsClient = new ApnsClientBuilder()
                .setApnsServer(ApnsClientBuilder.PRODUCTION_APNS_HOST)
                .setClientCredentials(new File("/www/wwwroot/push.p12"), "ly1997.03.08")
//                .setClientCredentials(new File("/IosPush/push.p12"), "ly1997.03.08")
                .build();


        final SimpleApnsPushNotification pushNotification;

        {
            final ApnsPayloadBuilder payloadBuilder = new SimpleApnsPayloadBuilder();
            payloadBuilder.setAlertBody(body);
            payloadBuilder.setAlertTitle(title);

            payloadBuilder.setSound("default"); // 铃音 默认

            payloadBuilder.setLocalizedAlertTitle("Open App");


            final String payload = payloadBuilder.build();
            final String token1 = TokenUtil.sanitizeTokenString(token);

            pushNotification = new SimpleApnsPushNotification(token1, "uni.UNI0E10694", payload);
        }


        final PushNotificationFuture<SimpleApnsPushNotification, PushNotificationResponse<SimpleApnsPushNotification>>
                sendNotificationFuture = apnsClient.sendNotification(pushNotification);
        log.info("Ios推送入参:{}",JSONObject.toJSON(sendNotificationFuture));


        try {
            final PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse =
                    sendNotificationFuture.get();

            System.out.println(JSONObject.toJSON("==========="+pushNotificationResponse));

            if (pushNotificationResponse.isAccepted()) {
                System.out.println("Push notification accepted by APNs gateway.");
                return R.ok();
            } else {
                System.out.println("Notification rejected by the APNs gateway: " +
                        pushNotificationResponse.getRejectionReason());

                pushNotificationResponse.getTokenInvalidationTimestamp().ifPresent(timestamp -> {
                    System.out.println("\t…and the token is invalid as of ----------------" + timestamp);
                });
                return R.instance(ResultEnum.FAST_FAIL);
            }
        } catch (final ExecutionException e) {
            System.err.println("Failed to send push notification.");

            return R.instance(ResultEnum.FAST_FAIL);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            final CompletableFuture<Void> closeFuture = apnsClient.close();
        } catch (final Exception e) {
            System.err.println("Failed to send push notification.");
            e.printStackTrace();
        }
        return R.instance(ResultEnum.FAST_FAIL);

    }


    public static void main(String[] args) throws Exception {

        final ApnsClient apnsClient = new ApnsClientBuilder()
                .setApnsServer(ApnsClientBuilder.PRODUCTION_APNS_HOST)

                .setClientCredentials(new File("\\ideaProject\\jdy-1919\\src\\main\\resources\\key\\push.p12"), "ly1997.03.08")
//                .setClientCredentials(new File("C:\\Users\\asus\\Desktop\\push.p12"), "ly1997.03.08")

                .build();

        final SimpleApnsPushNotification pushNotification;

        {
            final ApnsPayloadBuilder payloadBuilder = new SimpleApnsPayloadBuilder();
            payloadBuilder.setAlertBody("您有新的订单等待配送");
            payloadBuilder.setAlertTitle("新订单提醒");
            payloadBuilder.setSound("default"); // 铃音 默认
            payloadBuilder.setLocalizedAlertTitle("Open App");


            final String payload = payloadBuilder.build();
            final String token = TokenUtil.sanitizeTokenString("d8e1752071d7890e3ebc2d1dc26d5404e160833bfaf8d992f5ef4deafac7ec35");

            pushNotification = new SimpleApnsPushNotification(token, "uni.UNI0E10694", payload);
        }


        final PushNotificationFuture<SimpleApnsPushNotification, PushNotificationResponse<SimpleApnsPushNotification>>
                sendNotificationFuture = apnsClient.sendNotification(pushNotification);


        try {
            final PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse =
                    sendNotificationFuture.get();

            System.out.println(JSONObject.toJSON("==========="+pushNotificationResponse));

            if (pushNotificationResponse.isAccepted()) {

                System.out.println("Push notification accepted by APNs gateway.");
            } else {
                System.out.println("Notification rejected by the APNs gateway: " +
                        pushNotificationResponse.getRejectionReason());

                pushNotificationResponse.getTokenInvalidationTimestamp().ifPresent(timestamp -> {
                    System.out.println("\t…and the token is invalid as of ----------------" + timestamp);
                });
            }
        } catch (final ExecutionException e) {
            System.err.println("Failed to send push notification.");
            e.printStackTrace();
        }

        try {
            final CompletableFuture<Void> closeFuture = apnsClient.close();
        } catch (final Exception e) {
            System.err.println("Failed to send push notification.");
            e.printStackTrace();
        }

    }




//        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(4);
//
//        File file = new File("E:\\ideaProject\\jdy-1919\\src\\main\\resources\\key\\push.p12");
//        String password = "ly1997.03.08";
//        //setClientCredentials是传入一个InputStrem和证书密码
//        //setApnsServer是来确定是开发环境还是生产环境
//        ApnsClient apnsClient = new ApnsClientBuilder().setApnsServer(ApnsClientBuilder.PRODUCTION_APNS_HOST)
//                .setClientCredentials(file, "ly1997.03.08").setConcurrentConnections(1).setEventLoopGroup(eventLoopGroup).build();
//        ApnsPayloadBuilder payBuilder = new ApnsPayloadBuilder();
//        payBuilder.setAlertBody("pushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Examplepushy Example");
////        payBuilder.setSound("default");
//        String payload = payBuilder.buildWithDefaultMaximumLength();
//        final String token = "709bf206719b78e80fca498fdc4746dc7d55dfd4ae443472672865484a7a662e";
//        SimpleApnsPushNotification notification = new SimpleApnsPushNotification(token, "", payload);
//        Future<PushNotificationResponse<SimpleApnsPushNotification>> responseFuture = apnsClient
//                .sendNotification(notification);
//        responseFuture
//                .addListener(new GenericFutureListener<Future<PushNotificationResponse<SimpleApnsPushNotification>>>() {
//
//                    @Override
//                    public void operationComplete(Future<PushNotificationResponse<SimpleApnsPushNotification>> arg0)
//                            throws Exception {
//                        try {
//                            final PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse = arg0
//                                    .get();
//
//                            if (pushNotificationResponse.isAccepted()) {
//                                System.out.println("Push notification accepted by APNs gateway.");
//                            } else {
//                                System.out.println("Notification rejected by the APNs gateway: "
//                                        + pushNotificationResponse.getRejectionReason());
//
//                                if (pushNotificationResponse.getTokenInvalidationTimestamp() != null) {
//                                    System.out.println("\t…and the token is invalid as of "
//                                            + pushNotificationResponse.getTokenInvalidationTimestamp());
//                                }
//                            }
//                        } catch (final ExecutionException e) {
//                            System.err.println("Failed to send push notification.");
//                            e.printStackTrace();
//
////                            if (e.getCause() instanceof ClientNotConnectedException) {
////                                System.out.println("Waiting for client to reconnect…");
////                                apnsClient.getReconnectionFuture().await();
////                                System.out.println("Reconnected.");
////                            }
//                        }
//                    }
//                });
//        // 结束后关闭连接, 该操作会直到所有notification都发送完毕并回复状态后关闭连接
////        Future<Void> disconnectFuture = apnsClient.disconnect();
////        try {
////            disconnectFuture.await(1 , TimeUnit.HOURS);
////        } catch (Exception e) {
////            if(e instanceof InterruptedException) {
////                System.out.println("Failed to disconnect APNs , timeout");
////            }
////            e.printStackTrace();
////        }
//    }


}