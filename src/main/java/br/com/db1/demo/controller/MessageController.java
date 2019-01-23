package br.com.db1.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("api/")
public class MessageController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("pt-BR")
    public String getPortuguese (){
        Locale locale = new Locale("pt", "BR");
        return messageSource.getMessage(MESSAGE_HELLO_WORLD, null, locale);
    }

    @GetMapping("en-US")
    public String getEnglish() {
        Locale locale = new Locale("en", "US");
        return messageSource.getMessage(MESSAGE_HELLO_WORLD, null, locale);
    }

    @GetMapping("es-ES")
    public String getSpanish() {
       Locale locale = new Locale("es", "ES");
       return messageSource.getMessage(MESSAGE_HELLO_WORLD, null, locale);
    }

//-------------------------------message--------------------------------------------------

    @GetMapping("pt-BR/message")
    public String getPortugueseMessage(@RequestParam("message")String message) {
        Locale locale = new Locale("pt", "BR");
        return getMessageLocale(message,new Locale("pt", "BR"));
    }

    @GetMapping("en-US/message")
    public String getEnglishMesasge(@RequestParam("message")String message) {
        return getMessageLocale(message, new Locale("en", "US"));
    }

    @GetMapping("es-ES/message")
    public String getSpanishMessage(@RequestParam("message")String message) {
        return getMessageLocale(message, new Locale("es", "ES"));
    }

    private String getMessageLocale(String message, Locale locale) {
        message = messageSource.getMessage(MESSAGE_RECEIVED, new String[] {message}, locale);
        return message;
    }

    public static final String MESSAGE_RECEIVED = "message.received";
    public static final String MESSAGE_HELLO_WORLD = "message.hello.world";
}

