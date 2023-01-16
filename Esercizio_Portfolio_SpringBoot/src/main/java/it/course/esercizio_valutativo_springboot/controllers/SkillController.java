package it.course.esercizio_valutativo_springboot.controllers;

import it.course.esercizio_valutativo_springboot.businesses.interfaces.SkillBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SkillController {

    @Autowired
    SkillBO skillBO;

    @GetMapping(path = {"/skills"})
    public ModelAndView skills() {
        return new ModelAndView("/jsp/skills.jsp", "ListSkills", this.skillBO.getSkills());
    }

}
