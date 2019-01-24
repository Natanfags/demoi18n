package br.com.db1.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("api/messages")
public class MessageController {

    public static final String MESSAGE_HELLO_WORLD = "message.hello.world";

    @Autowired
    private MessageSource messageSource;

    @GetMapping("pt-BR")
    public String getPortuguese() {
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
}

