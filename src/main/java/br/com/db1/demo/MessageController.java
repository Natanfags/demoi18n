package br.com.db1.demo;

import br.com.db1.demo.model.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

import static java.util.Locale.US;

@RestController
@RequestMapping("/")
public class MessageController {

    /*@PostMapping("loginform")
    public String processLogin(@Valid LoginForm form) {
        return "Success";

    }*/

    @Autowired
    private MessageSource messageSource;

    @PostMapping("loginform")
    public String processLogin(LoginForm form) {
        Locale pt_BR = new Locale("pt", "BR");
        Locale es_ES = new Locale("es", "ES");

        String defaultMessage = messageSource.getMessage("teste.maiko", new String[] { "Maiko", "Cunha", "Teste"}, US);
        return defaultMessage;
    }
}