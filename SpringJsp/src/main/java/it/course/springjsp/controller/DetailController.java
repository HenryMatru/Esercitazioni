package it.course.springjsp.controller;

import it.course.springjsp.business.interfaces.WebSiteInfoBO;
import it.course.springjsp.model.WebSiteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DetailController {

    @Autowired
    WebSiteInfoBO webSiteInfoBO;

    @GetMapping(path = {"/details"})
    public ModelAndView home() {
        List<WebSiteInfo> listWebSiteInfo = this.webSiteInfoBO.getAllWebSiteInfo();
        return new ModelAndView("/jsp/details.jsp", "ListWebSiteIngo", listWebSiteInfo);
    }

}
