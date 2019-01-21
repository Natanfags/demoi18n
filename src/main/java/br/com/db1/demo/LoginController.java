package br.com.db1.demo;

import br.com.db1.demo.model.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/")
public class LoginController {

    @PostMapping("loginform")
    public String processLogin(@Valid LoginForm form) {
        return "Success";

    }

 /*   @Autowired
    private MessageSource messageSource;

    @PostMapping("loginform")
    public String processLogin(LoginForm form) {
        String message = messageSource.getMessage("email.notempty", null, Locale.US);
        return "Success";
    }*/
}
