package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * Hello world!
 *
 */
public class App
{
    private static Logger logger = LoggerFactory.getLogger(App.class);
    public static final String wsUri = "wss://iovwsopen-test.radio.cn/socket.io/?packagename=com.edog.car.ceshi&capabilities=PAY_CONTENT_SUPPORTTED%2CNEW_DOMAIN_SUPPORTTED%2CMEDIA_URL_MUST_BE_HTTPS&sign=18ebf8da68ea26b3068efb28726444d3&appid=kc3499&deviceid=1b4e83e1ce10a4cc6cb6a26d29e7bddc&os=android&openid=ho75492019050910006171&lng=116.354957&lat=39.910639";
//    public static final String kradioHomepageRefresh = "[\"KradioHomepageRefresh\",{\"packagename\":\"com.edog.car.ceshi\",\"capabilities\":\"PAY_CONTENT_SUPPORTTED,NEW_DOMAIN_SUPPORTTED,MEDIA_URL_MUST_BE_HTTPS\",\"sign\":\"18ebf8da68ea26b3068efb28726444d3\",\"zone\":\"mainPage\",\"appid\":\"kc3499\",\"deviceid\":\"1b4e83e1ce10a4cc6cb6a26d29e7bddc\",\"parentCode\":\"0\",\"lng\":\"\",\"os\":\"android\",\"openid\":\"ho75492019050910006171\",\"version\":\"1.0.0.0001\",\"app_type\":\"1000460\",\"isRecursive\":\"1\",\"lat\":\"\",\"channel\":\"ceshi\"}]";
    public static final String kradioHomepageRefresh = "42[\"KradioHomepageRefresh\",{\"packagename\":\"com.edog.car.ceshi\",\"capabilities\":\"PAY_CONTENT_SUPPORTTED,NEW_DOMAIN_SUPPORTTED,MEDIA_URL_MUST_BE_HTTPS\",\"sign\":\"18ebf8da68ea26b3068efb28726444d3\",\"zone\":\"mainPage\",\"appid\":\"kc3499\",\"deviceid\":\"1b4e83e1ce10a4cc6cb6a26d29e7bddc\",\"parentCode\":\"0\",\"lng\":\"\",\"os\":\"android\",\"openid\":\"ho75492019050910006171\",\"version\":\"1.0.0.0001\",\"app_type\":\"1000460\",\"isRecursive\":\"1\",\"lat\":\"\",\"channel\":\"ceshi\"}]";

    public static final String locationUpdateMessage = "[\"locationUpdateMessage\",{\"deviceid\":\"1b4e83e1ce10a4cc6cb6a26d29e7bddc\",\"lng\":\"116.354957\",\"lat\":\"39.910639\"}]";

    public static void main( String[] args )
    {
        URI uri = URI.create(wsUri);
    }
}
