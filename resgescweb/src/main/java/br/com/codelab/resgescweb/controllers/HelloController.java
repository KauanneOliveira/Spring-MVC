package br.com.codelab.resgescweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public ModelAndView hello(){
        ModelAndView mv = new ModelAndView( "hello");
        mv.addObject( "nome", "Maria!" );
        return mv;
    }

    @GetMapping("/hello-model")
    public String hello(Model model){
        model.addAttribute( "nome", "Zezinho!");
        return "hello";

    }

    @GetMapping("/hello-sevlet")
    public String hello(HttpServletRequest request){
        request.setAttribute("nome", "Samuka");
        return "hello";
    }

}