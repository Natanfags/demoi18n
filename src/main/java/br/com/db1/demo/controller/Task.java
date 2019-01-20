package br.com.db1.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Task {

    @RequestMapping("/task")
    public String gohome(Model model) {
        return "task";
    }
}
