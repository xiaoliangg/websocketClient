package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App
{
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
        JavaBean javaBean = JavaBean.builder().name("testName").build();
        logger.info("test logger" + javaBean.toString());
        System.out.println( "Hello World!" );
    }
}
