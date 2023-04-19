package org.example;

import com.google.gson.*;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.engineio.client.transports.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.LocationUpdateMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.*;

import static org.apache.commons.collections4.IterableUtils.toList;

public class SocketIOClientDemo {
    // 09bd6d56b59fa6aba99021263ec3b7e9

//    public static final String SCHEMA_TEST = "https://iovws-test.radio.cn";
    public static final String SCHEMA_TEST = "http://iovws-test.radio.cn";
//    public static final String SCHEMA_TEST = "http://localhost:9095";

    public static final String wsUri = SCHEMA_TEST + "/?capabilities=NEW_DOMAIN_SUPPORTTED%2CPAY_CONTENT_SUPPORTTED&os=android&lng=114.45123091&openid=kc34992023041210000003&packagename=com.edog.car&sign=da838eb65cd00d0f4561aa5110793943&deviceid=09bd6d56b59fa6aba99021263ec3b7e9&carType=X7&appid=kc3499&udid=09bd6d56b59fa6aba99021263ec3b7e9&lat=38.03762354";

    public static void main(String[] args) {
        // charls代理
//        System.setProperty("http.proxyHost", "127.0.0.1");
//        System.setProperty("http.proxyPort", "8888");

        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{ WebSocket.NAME};
            options.reconnectionAttempts = 2;
            // 失败重连的时间间隔
            options.reconnectionDelay = 1000;
            // 连接超时时间(ms)
            options.timeout = 500;

            final Socket socket = IO.socket(wsUri, options);
            socket.on(Socket.EVENT_CONNECT, args1 -> {
                try {
                    System.out.println("连接建立成功");
                }catch (Exception e){
                    e.printStackTrace();
                }
            });

            socket.on("appMessage", objects -> {
                try {
                    System.out.println("服务端appMessage：" + objects[0].toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            socket.on("apiEBMessage", objects -> {
                try {
                    System.out.println("服务端apiEBMessage：" + objects[0].toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            socket.on("locationUpdateMessageResponse", objects -> {
                try {
                    System.out.println("服务端locationUpdateMessageResponse：" + objects[0].toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            });

            socket.connect();

            while (true) {
                Thread.sleep(3000);
                // 自定义事件`push_data_event` -> 向服务端发送消息
                Map<String,Object> map = new HashMap<>();
                map.put("deviceId","09bd6d56b59fa6aba99021263ec3b7e9");
                map.put("requestId",UUID.randomUUID().toString());
                map.put("lat",36.1231);
                map.put("lng",108.1234);
                socket.emit("locationUpdateMessage",map);
            }
        } catch (Exception e) {
            System.out.println("err:" + e);
            e.printStackTrace();
        }
    }
}