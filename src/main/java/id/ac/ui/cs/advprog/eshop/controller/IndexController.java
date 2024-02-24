package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.model.Model;


@Controller
@RequestMapping("")
public class IndexController {
    // @GetMapping("") 
    // public ModelAndView index() {
    //     return new ModelAndView("index");
    // }

    @GetMapping("") 
    public String createMainPage(Model model) {
        return "main";
    }
}
