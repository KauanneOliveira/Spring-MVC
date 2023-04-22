package br.com.codelab.resgescweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home"; //renderiza o arquivo que está na na pasta templates/home.html
    }
}
