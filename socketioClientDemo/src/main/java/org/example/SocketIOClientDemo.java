package org.example;

import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.LocationUpdateMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

@Slf4j
public class SocketIOClientDemo {
    // 09bd6d56b59fa6aba99021263ec3b7e9

//    public static final String SCHEMA_TEST = "https://iovws-test.radio.cn";
    public static final String SCHEMA_TEST = "http://iovws-test.radio.cn";
//    public static final String SCHEMA_TEST = "http://localhost:9095";
    public static final String wsUri = SCHEMA_TEST + "/socket.io/?capabilities=NEW_DOMAIN_SUPPORTTED%2CPAY_CONTENT_SUPPORTTED&os=android&lng=114.45123091&openid=kc34992023041210000003&packagename=com.edog.car&sign=da838eb65cd00d0f4561aa5110793943&channel=ceshi&deviceid=09bd6d56b59fa6aba99021263ec3b7e9&version=1.7.0&sdkversion=1.7.0&carType=X7&appid=kc3499&udid=09bd6d56b59fa6aba99021263ec3b7e9&lat=38.03762354";

    public static final String locationUpdateMsg = "{\"deviceId\":\"09bd6d56b59fa6aba99021263ec3b7e9\",\"requestId\":\"f1480d62-6243-496d-bb41-17708df87001\",\"lng\":116.354957,\"lat\":39.910639}";
    public static void main(String[] args) {
        // 服务端socket.io连接通信地址
//        String url = "http://127.0.0.1:8888";
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            options.reconnectionAttempts = 2;
            // 失败重连的时间间隔
            options.reconnectionDelay = 1000;
            // 连接超时时间(ms)
            options.timeout = 500;
            // userId: 唯一标识 传给服务端存储
//            final Socket socket = IO.socket(url + "?userId=1", options);
            final Socket socket = IO.socket(wsUri, options);

            socket.on(Socket.EVENT_CONNECT, args1 -> {
                try {
                    log.info("连接建立成功");
                    socket.send("hello...");
                }catch (Exception e){
                    e.printStackTrace();
                }
            });

            // 自定义事件`connected` -> 接收服务端成功连接消息
            socket.on("connected", objects -> {
                try {
                    log.debug("服务端:" + objects[0].toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            });

            // 自定义事件`push_data_event` -> 接收服务端消息
//            socket.on("push_data_event", objects -> log.debug("服务端:" + objects[0].toString()));

            // 自定义事件`myBroadcast` -> 接收服务端广播消息
            socket.on("myBroadcast", objects -> {
                try {
                    log.debug("服务端myBroadcast：" + objects[0].toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            socket.on("appMessage", objects -> {
                try {
                    log.debug("服务端appMessage：" + objects[0].toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            socket.on("apiEBMessage", objects -> {
                try {
                    log.debug("服务端apiEBMessage：" + objects[0].toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            socket.on("locationUpdateMessageResponse", objects -> {
                try {
                    log.debug("服务端locationUpdateMessageResponse：" + objects[0].toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            });

            socket.connect();

            while (true) {
                Thread.sleep(3000);
                // 自定义事件`push_data_event` -> 向服务端发送消息
//                socket.emit("push_data_event", "发送数据 " + new Date());
                LocationUpdateMessageRequest request = generateLocationRequest("09bd6d56b59fa6aba99021263ec3b7e9");
                socket.emit("locationUpdateMessage", request);
            }
        } catch (Exception e) {
            log.error("err:{}",e);
            e.printStackTrace();
        }
    }

    private static LocationUpdateMessageRequest generateLocationRequest(String deviceId) {
        LocationUpdateMessageRequest request = new LocationUpdateMessageRequest();
        request.setRequestId(UUID.randomUUID().toString());
        request.setDeviceId(deviceId);
        request.setLat(116.354957);
        request.setLng(39.910639);
        return request;
    }

}