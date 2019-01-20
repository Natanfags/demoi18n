package br.com.db1.demo.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/task")
    public String getInternationalPage() {
        return "task";
    }
}
