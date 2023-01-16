package it.course.esercizio_valutativo_springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping(path = {"/", "/home", "/index"})
    public ModelAndView home() {
        return new ModelAndView("/jsp/index.jsp");
    }

}
