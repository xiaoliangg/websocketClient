package org.example;

import io.socket.client.IO;
import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.engineio.client.EngineIOException;
import io.socket.engineio.client.transports.WebSocket;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SocketIOClientDemo {
//    public static final String SCHEMA_TEST = "https://iovws-test.radio.cn";
    public static final String SCHEMA_TEST = "http://iovws-dev.radio.cn/open";
//    public static final String SCHEMA_TEST = "http://iovws-test.radio.cn";
//    public static final String SCHEMA_TEST = "http://localhost:9095";

    public static final String deviceId = "09bd6d56b59fa6aba99021263ec3b7e9";
    public static final String appId = "kc3499";
    public static final String packagename = "com.edog.car";
    public static final String openid = "kc34992023041210000003";
    public static final String sign = "da838eb65cd00d0f4561aa5110793943";
    public static final String lat = "38.03762354";
    public static final String lng = "114.45123091";

//    public static String deviceId = "a89b0935f1aaa35984dd6924d0c416f3";
//    public static final String appId = "in2193";
//    public static final String packagename = "com.kaolafm.sdk.demo";
//    public static final String openid = "in21932023041910000001";
//    public static final String sign = "cea844ed5989bc95dbf46e3dfbcdd13c";
//    public static final String lat = "38.03762354";
//    public static final String lng = "114.45123091";

    public static final String WS_URI = "http://iovws-test.radio.cn?capabilities=NEW_DOMAIN_SUPPORTTED%2CPAY_CONTENT_SUPPORTTED&os=android&lng=" + lng
            +"&openid=" + openid + "&packagename=" + packagename + "&sign="+ sign +"&deviceid=" + deviceId
            +"&carType=X7&appid="+ appId + "&lat=" + lat;


    public static void main(String[] args) {
        // charls代理
//        System.setProperty("http.proxyHost", "127.0.0.1");
//        System.setProperty("http.proxyPort", "8888");

        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{ WebSocket.NAME};
//            options.reconnectionAttempts = 2;
//            // 失败重连的时间间隔
//            options.reconnectionDelay = 1000;
//            // 连接超时时间(ms)
//            options.timeout = 500;

            System.out.println("WS_URI:" + WS_URI);
            Manager manager = new Manager(URI.create(WS_URI),options);
            final Socket socket = manager.socket("/open");

            socket.on(Socket.EVENT_CONNECT, args1 -> {
                try {
                    System.out.println("连接建立成功");
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            socket.on(Socket.EVENT_DISCONNECT, args1 -> {
                try {
                    System.out.println("连接断开");
                }catch (Exception e){
                    e.printStackTrace();
                }
            });

            socket.on(Socket.EVENT_ERROR, args1 -> {
                try {
                    System.out.println("连接异常");
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            socket.on(Socket.EVENT_CONNECT_TIMEOUT, args1 -> {
                try {
                    System.out.println("connect_timeout");
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            socket.on(Socket.EVENT_CONNECT_ERROR, args1 -> {
                try {
                    System.out.println("connect_error:" + args1[0].toString());
                    if(args1[0] instanceof EngineIOException){
                        EngineIOException engineIOException = (EngineIOException)args1[0];
                        System.out.println("connect_error:" + engineIOException.getCause()
                                + "|" + engineIOException.getMessage()
                                + "|" + engineIOException.getLocalizedMessage()
                        );
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            });

            socket.on("EB_PUSH", objects -> {
                try {
                    System.out.println("EB_PUSH：" + objects[0].toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            });

            socket.on("LOC_RESP", objects -> {
                try {
                    System.out.println("LOC_RESP：" + objects[0].toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
//            socket.io();socket.open();
            socket.connect();

            // 100s后断开连接
//            new Thread(() -> {
//                try {
//                    Thread.sleep(100*1000);
//                    socket.disconnect();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();

            while (true) {
                Thread.sleep(3000);
                if(socket.connected()){
                    // 自定义事件`push_data_event` -> 向服务端发送消息
                    String uuid = UUID.randomUUID().toString();
                    System.out.println("uuid:" + uuid);
                    Map<String,Object> map = new HashMap<>();
                    map.put("deviceId",deviceId);
                    map.put("requestId",uuid);
                    map.put("lat",Double.parseDouble(lat));
                    map.put("lng",Double.parseDouble(lng));
                    socket.emit("LOC_RPT",map);
                }else{
                    System.out.println("未连接状态");
                }

            }

        } catch (Exception e) {
            System.out.println("err:" + e);
            e.printStackTrace();
        }
    }
}