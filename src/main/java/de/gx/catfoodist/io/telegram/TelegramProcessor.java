package de.gx.catfoodist.io.telegram;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.telegram.model.IncomingMessage;
import org.apache.camel.component.telegram.model.InlineKeyboardButton;
import org.apache.camel.component.telegram.model.OutgoingTextMessage;
import org.apache.camel.component.telegram.model.ReplyKeyboardMarkup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
//@PropertySource("classpath:application.yml")
public class TelegramProcessor implements Processor {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void process(Exchange exchange) {
        logger.debug("[Telegram] received:\" {} \"", exchange.getIn().getBody(IncomingMessage.class));
        logger.debug("[Telegram] type is:\" {} \"", exchange.getIn().getClass());
        logger.debug("[Telegram] body is:\" {} \"", exchange.getIn().getBody().getClass());

        IncomingMessage incomingMessage = exchange.getIn().getBody(IncomingMessage.class);

        if (exchange.getIn().getBody(IncomingMessage.class).getPhoto() != null) {
            logger.debug("[Telegram] message contains an image?:\" {} \"", exchange.getIn().getBody(IncomingMessage.class).getPhoto() != null);
            processImage(exchange);
        }
        else if (exchange.getIn().getBody(IncomingMessage.class).getText() != null) {
            processText(exchange);
        }







        //check if initial start
//        if (exchange.getIn().getBody(String.class).contains("start")) {
//        }
//        else {
//            TelegramMessage telegramMessage = new TelegramMessage();
//            telegramMessage.setDate(new Date());
//            telegramMessage.setMessageText(exchange.getIn().getBody(String.class));
////        telegramMessage.setSender(exchange.getIn().getHeader("CamelTelegramChatId").toString());
//            telegramMessage.setSender(exchange.getIn().getHeader("chatId").toString());
//
//            telegramService.saveTelegramMessage(telegramMessage);
//            exchange.getOut().setBody(String.class);
//        }

        //check if binary(image) or text(message)

    }




//    private void createUI(Exchange exchange) {
//        OutgoingTextMessage msg = new OutgoingTextMessage();
//        msg.setText("Choose one option!");
//
//        InlineKeyboardButton buttonOptionOneI = InlineKeyboardButton.builder()
//                .text("Option One - I").build();
//
//        InlineKeyboardButton buttonOptionOneII = InlineKeyboardButton.builder()
//                .text("Option One - II").build();
//
//        InlineKeyboardButton buttonOptionTwoI = InlineKeyboardButton.builder()
//                .text("Option Two - I").build();
//
//        ReplyKeyboardMarkup replyMarkup = ReplyKeyboardMarkup.builder()
//                .keyboard()
//                .addRow(Arrays.asList(buttonOptionOneI, buttonOptionOneII))
//                .addRow(Arrays.asList(buttonOptionTwoI))
//                .close()
//                .oneTimeKeyboard(true)
//                .build();
//
//        msg.setReplyKeyboardMarkup(replyMarkup);
//
//        exchange.getIn().setBody(msg);
//    }


    private void processText(Exchange exchange) {
        IncomingMessage incomingMessage = exchange.getIn().getBody(IncomingMessage.class);

        switch (incomingMessage.getText()) {
            case("/showItems"):
                logger.debug("[Telegram] showItems:\" {} \"", exchange.getIn().getBody(String.class));
            case("/addItem"):
                logger.debug("[Telegram] addItem:\" {} \"", exchange.getIn().getBody(String.class));
            default: {
                if (incomingMessage.getText().startsWith("/barcode")) {
                    logger.debug("[Telegram] barcode:\" {} \"", exchange.getIn().getBody(String.class));
                }
                else {
                    createUI(exchange);
                }
            }
        }
    }

    private void processImage(Exchange exchange) {

    }


    private void createUI(Exchange exchange) {
        OutgoingTextMessage msg = new OutgoingTextMessage();
        msg.setText("Choose one option!");

        InlineKeyboardButton buttonOptionOneI = InlineKeyboardButton.builder()
                .text("show items").build();

        InlineKeyboardButton buttonOptionOneII = InlineKeyboardButton.builder()
                .text("add item").build();

        InlineKeyboardButton buttonOptionTwoI = InlineKeyboardButton.builder()
                .text("add barcode").build();

        ReplyKeyboardMarkup replyMarkup = ReplyKeyboardMarkup.builder()
                .keyboard()
                .addRow(Arrays.asList(buttonOptionOneI, buttonOptionOneII))
                .addRow(Arrays.asList(buttonOptionTwoI))
                .close()
                .oneTimeKeyboard(true)
                .build();

        msg.setReplyKeyboardMarkup(replyMarkup);

        exchange.getIn().setBody(msg);
    }

}
