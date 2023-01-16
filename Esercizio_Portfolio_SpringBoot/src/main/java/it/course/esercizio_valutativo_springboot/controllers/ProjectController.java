package it.course.esercizio_valutativo_springboot.controllers;

import it.course.esercizio_valutativo_springboot.businesses.interfaces.ProjectBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjectController {

    @Autowired
    ProjectBO projectBO;

    @GetMapping(path = {"/projects"})
    public ModelAndView projects() {
        return new ModelAndView("/jsp/projects.jsp", "ListProjects", this.projectBO.getProjects());
    }

}
