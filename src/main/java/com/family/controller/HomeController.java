package com.family.controller;

import com.family.dto.ChiTietDonHang;
import com.family.dto.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

public class HomeController {


    @GetMapping("/")
    public String index() {

        return "index";
    }
}
