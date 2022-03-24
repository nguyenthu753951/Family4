package com.family.controller;


import com.family.dto.Menu;
import com.family.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    MenuRepository menuRepository;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Menu> menuList = menuRepository.findAll();
        modelAndView.addObject("menuList", menuList);
        return modelAndView;
    }
}
