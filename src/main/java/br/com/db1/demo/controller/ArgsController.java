package br.com.db1.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/api/messages/args")
public class ArgsController {

    public static final String MESSAGE_RECEIVED = "message.received";

    @Autowired
    private MessageSource messageSource;

    @GetMapping("pt-BR")
    public String getPortugueseMessage(@RequestParam("args")String message) {
        return getMessageLocale(message,new Locale("pt", "BR"));
    }

    @GetMapping("en-US")
    public String getEnglishMesasge(@RequestParam("args")String message) {
        return getMessageLocale(message, new Locale("en", "US"));
    }

    @GetMapping("es-ES")
    public String getSpanishMessage(@RequestParam("args")String message) {
        return getMessageLocale(message, new Locale("es", "ES"));
    }

    private String getMessageLocale(String message, Locale locale) {
        message = messageSource.getMessage(MESSAGE_RECEIVED, new String[] {message}, locale);
        return message;
    }
}