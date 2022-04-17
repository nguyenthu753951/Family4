package com.family.controller;


import com.family.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/product/{id}")
    public String getproducts(@PathVariable Long id, Model model) {
        model.addAttribute("products",productRepository.getMenuByid(id).get());
        model.addAttribute("Menu",productRepository.findByid(id));
        return "product_detail_page";
    }
}
