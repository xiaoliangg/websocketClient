package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    private static Logger logger = LoggerFactory.getLogger(App.class);
//    public static final String domain = "wss://iovwsopen.radio.cn";
    public static final String domain = "ws://oms.msglongconnect.com:5051";
//    public static final String wsUri = domain + "/socket.io/?packagename=com.edog.car.ceshi&capabilities=PAY_CONTENT_SUPPORTTED%2CNEW_DOMAIN_SUPPORTTED%2CMEDIA_URL_MUST_BE_HTTPS&sign=18ebf8da68ea26b3068efb28726444d3&appid=kc3499&deviceid=1b4e83e1ce10a4cc6cb6a26d29e7bddc&os=android&openid=ho75492019050910006171&lng=116.354957&lat=39.910639&EIO=3&transport=websocket";
    public static final String wsUri = "https://iovws-test.radio.cn/socket.io/?capabilities=NEW_DOMAIN_SUPPORTTED%2CPAY_CONTENT_SUPPORTTED&os=android&lng=114.45123091&openid=kc34992023041210000003&packagename=com.edog.car&sign=da838eb65cd00d0f4561aa5110793943&channel=ceshi&EIO=3&transport=websocket&deviceid=09bd6d56b59fa6aba99021263ec3b7e9&version=1.7.0&sdkversion=1.7.0&carType=X7&appid=kc3499&udid=09bd6d56b59fa6aba99021263ec3b7e9&lat=38.03762354";


//    public static final String wsUri = "ws://localhost:9095/socket.io/?packagename=com.edog.car.ceshi&capabilities=PAY_CONTENT_SUPPORTTED%2CNEW_DOMAIN_SUPPORTTED%2CMEDIA_URL_MUST_BE_HTTPS&sign=18ebf8da68ea26b3068efb28726444d3&appid=kc3499&deviceid=1b4e83e1ce10a4cc6cb6a26d29e7bddc&os=android&openid=ho75492019050910006171&lng=116.354957&lat=39.910639&EIO=3&transport=websocket";
//    public static final String kradioHomepageRefresh = "[\"KradioHomepageRefresh\",{\"packagename\":\"com.edog.car.ceshi\",\"capabilities\":\"PAY_CONTENT_SUPPORTTED,NEW_DOMAIN_SUPPORTTED,MEDIA_URL_MUST_BE_HTTPS\",\"sign\":\"18ebf8da68ea26b3068efb28726444d3\",\"zone\":\"mainPage\",\"appid\":\"kc3499\",\"deviceid\":\"1b4e83e1ce10a4cc6cb6a26d29e7bddc\",\"parentCode\":\"0\",\"lng\":\"\",\"os\":\"android\",\"openid\":\"ho75492019050910006171\",\"version\":\"1.0.0.0001\",\"app_type\":\"1000460\",\"isRecursive\":\"1\",\"lat\":\"\",\"channel\":\"ceshi\"}]";
    public static final String kradioHomepageRefresh = "42[\"KradioHomepageRefresh\",{\"packagename\":\"com.edog.car.ceshi\",\"capabilities\":\"PAY_CONTENT_SUPPORTTED,NEW_DOMAIN_SUPPORTTED,MEDIA_URL_MUST_BE_HTTPS\",\"sign\":\"18ebf8da68ea26b3068efb28726444d3\",\"zone\":\"mainPage\",\"appid\":\"kc3499\",\"deviceid\":\"1b4e83e1ce10a4cc6cb6a26d29e7bddc\",\"parentCode\":\"0\",\"lng\":\"\",\"os\":\"android\",\"openid\":\"ho75492019050910006171\",\"version\":\"1.0.0.0001\",\"app_type\":\"1000460\",\"isRecursive\":\"1\",\"lat\":\"\",\"channel\":\"ceshi\"}]";

    public static final String locationUpdateMessage = "[\"locationUpdateMessage\",{\"deviceid\":\"1b4e83e1ce10a4cc6cb6a26d29e7bddc\",\"lng\":\"116.354957\",\"lat\":\"39.910639\"}]";

    static javaxClientUtil endpoint = null;
    public static void main( String[] args )
    {
        javaxClientUtil endpoint = null;
        URI uri = URI.create(wsUri);
        new Thread(()->{
            javaxClientUtil endpoint2 = new javaxClientUtil(uri);
            endpoint2.addMessageHandler(new MessageHandlerImpl());
            App.endpoint = endpoint2;
            endpoint2.sendMessage(kradioHomepageRefresh);
        }).start();


        while(true){
            System.out.println("请输入内容：");
            Scanner scanner = new Scanner(System.in);
            String info = scanner.nextLine();
            System.out.println("输入的内容为：" + info);
        }
    }
}
