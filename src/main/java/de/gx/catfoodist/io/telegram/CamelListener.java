package de.gx.catfoodist.io.telegram;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "telegram")
public class CamelListener extends RouteBuilder {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${telegram-bot-token}")
    private String authorization_token;

    @Value("${telegram-chatId}")
    private String chatId;

    @Autowired
    private TelegramProcessor telegramProcessor;

    @Override
    public void configure() throws Exception {
        logger.info("[TelegramProcessor] Setup Telegram Camelroute with API Token: " + authorization_token );
        from("telegram:bots/" + authorization_token)
//                .to("log:INFO?showHeaders=true")
                .bean(telegramProcessor).id("telegramProcessor")
                .to("telegram:bots/" + authorization_token + "?chatId=" + chatId);
    }
}
