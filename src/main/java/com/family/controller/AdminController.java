package com.family.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("Admin");
        return modelAndView;
    }
}

