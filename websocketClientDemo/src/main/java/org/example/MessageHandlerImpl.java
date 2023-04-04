package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageHandlerImpl implements javaxClientUtil.MessageHandler {
    private static Logger logger = LoggerFactory.getLogger(MessageHandlerImpl.class);

    @Override
    public void handleMessage(String message) {
        logger.info("收到消息:{}",message);
    }
}
