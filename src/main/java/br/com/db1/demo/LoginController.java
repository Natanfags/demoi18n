package br.com.db1.demo;

import br.com.db1.demo.model.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Locale;

import static java.util.Locale.US;

@RestController
@RequestMapping("/")
public class LoginController {

    /*@PostMapping("loginform")
    public String processLogin(@Valid LoginForm form) {
        return "Success";

    }*/

    @Autowired
    private MessageSource messageSource;

    @PostMapping("loginform")
    public String processLogin(LoginForm form) {
        Locale ptBR = new Locale("pt", "BR");
        Locale es_ES = new Locale("es", "ES");

        String defaultMessage = messageSource.getMessage("email.notempty", null, es_ES);
        return defaultMessage;
    }
}