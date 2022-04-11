package com.family.controller;


import com.family.dto.Menu;
import com.family.repository.MenuRepository;
import com.family.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    MenuRepository menuRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Menu> menuList = menuRepository.findAll();
        modelAndView.addObject("menuList", menuList);
        return modelAndView;
    }
    @GetMapping("/home/product/{id}")
    public String getproducts(@PathVariable Long id, Model model) {
        model.addAttribute("products",productRepository.getMenuByid(id).get());
        model.addAttribute("Menu",productRepository.findByid(id));
        return "product_detail_page";
    }
}