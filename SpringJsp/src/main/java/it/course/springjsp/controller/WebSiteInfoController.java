package it.course.springjsp.controller;

import it.course.springjsp.business.interfaces.WebSiteInfoBO;
import it.course.springjsp.model.WebSiteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebSiteInfoController {

    @Autowired
    WebSiteInfoBO webSiteInfoBO;

    @GetMapping(path = {"/", "/index"})
    public ModelAndView home() {
        WebSiteInfo webSiteInfo = this.webSiteInfoBO.getWebSiteInfo();
        return new ModelAndView("/jsp/index.jsp", "Info", webSiteInfo);
    }

    @GetMapping("/createInfo")
    public ModelAndView createInfo() {
        return new ModelAndView("/jsp/createInfo.jsp");
    }

    @PostMapping("/createInfo")
    public ModelAndView insertInfo(@RequestParam String description, @RequestParam String websitename) {
        WebSiteInfo webSiteInfo = new WebSiteInfo();
        webSiteInfo.setDescription(description);
        webSiteInfo.setName(websitename);
        this.webSiteInfoBO.insertWebSiteInfo(webSiteInfo);
        return new ModelAndView("/jsp/createInfo.jsp", "operation", "Insert succeded");
    }

}
