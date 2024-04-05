package br.senac.tialejo.controller;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {
    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("landingPage");
        return mv;
    }
}
