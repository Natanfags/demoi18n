package br.com.db1.demo.controller;

import br.com.db1.demo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class PersonController {

    @Autowired
    private MessageSource messageSource;

    @PostMapping("validaperson")
    public String post(@Valid Person person) {

        return "Success";
    }
}
//@RestController
//@RequestMapping("/")
//public class LoginController {
//
//    @PostMapping("loginform")
//    public String processLogin(@Valid LoginForm form) {
//        return "Success";
//
//    }
