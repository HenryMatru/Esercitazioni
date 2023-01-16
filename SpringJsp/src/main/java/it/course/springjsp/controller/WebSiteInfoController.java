package it.course.springjsp.controller;

import it.course.springjsp.business.interfaces.WebSiteInfoBO;
import it.course.springjsp.model.WebSiteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebSiteInfoController {

    @Autowired
    WebSiteInfoBO webSiteInfoBO;

    @GetMapping(path = {"/", "/index", "/home"})
    public ModelAndView home() {
        WebSiteInfo webSiteInfo = this.webSiteInfoBO.getWebSiteInfo();
        return new ModelAndView("/jsp/index.jsp", "Info", webSiteInfo);
    }

    @GetMapping("/createInfo")
    public ModelAndView createInfo() {
        return new ModelAndView("/jsp/createInfo.jsp");
    }

    @PostMapping("/createInfo")
    public ModelAndView insertInfo(@RequestParam String description, @RequestParam String webSiteName) {
        WebSiteInfo webSiteInfo = new WebSiteInfo();
        webSiteInfo.setDescription(description);
        webSiteInfo.setName(webSiteName);
        this.webSiteInfoBO.insertWebSiteInfo(webSiteInfo);
        return new ModelAndView("/jsp/createInfo.jsp", "operation", "Insert succeded");
    }

    @GetMapping("/updateInfo")
    public ModelAndView updateInfo(@RequestParam long id) {
        WebSiteInfo webSiteInfo = this.webSiteInfoBO.getInfo(id);
        return new ModelAndView("/jsp/updateInfo.jsp", "WebSiteInfo", webSiteInfo);
    }

    @PostMapping("/updateInfo")
    public ModelAndView updateInfo(@RequestParam long id, @RequestParam String description, @RequestParam String webSiteName) {
        WebSiteInfo webSiteInfo = this.webSiteInfoBO.getInfo(id);
        webSiteInfo.setDescription(description);
        webSiteInfo.setName(webSiteName);
        this.webSiteInfoBO.updateWebSiteInfo(webSiteInfo);
        ModelAndView model = new ModelAndView("/jsp/updateInfo.jsp", "operation", "The element has been modified!");
        return model;
    }

    @GetMapping("/deleteInfo")
    public ModelAndView deleteInfo(@RequestParam long id) {
        WebSiteInfo webSiteInfo = this.webSiteInfoBO.getInfo(id);
        return new ModelAndView("/jsp/deleteInfo.jsp", "WebSiteInfo", webSiteInfo);
    }

    @PostMapping("/deleteInfo")
    public ModelAndView deleteInfo(@RequestParam long id, @RequestParam String description, @RequestParam String webSiteName) {
        WebSiteInfo webSiteInfo = this.webSiteInfoBO.getInfo(id);
        this.webSiteInfoBO.deleteWebSiteInfo(webSiteInfo.getId());
        ModelAndView model = new ModelAndView("/jsp/deleteInfo.jsp", "operation", "Elimination of:\n");
        model.addObject("websitename", webSiteName);
        model.addObject("description", description);
        return model;
    }

}
