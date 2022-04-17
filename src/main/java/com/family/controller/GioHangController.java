package com.family.controller;
import com.family.dto.GioHangItem;
import com.family.dto.Menu;
import com.family.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import  javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.menu;

@Controller
public class GioHangController extends HttpServlet {


    @Autowired
    ProductRepository productRepository;

    @Autowired
    HttpSession httpSession;

    @GetMapping("/themvaogiohang/{id}/{soLuong}")
    public String themVaoGioHang̣(@PathVariable Long id, @PathVariable Long soLuong)  {
        Menu menu = productRepository.getMenuByid(id).get();
        List<GioHangItem> gioHang = (List<GioHangItem>) httpSession.getAttribute("gioHang");
        if (gioHang == null || gioHang.size() == 0) {
            gioHang = new ArrayList<>();
            gioHang.add(new GioHangItem(Math.toIntExact(soLuong), menu, menu.getGiaBan().multiply(BigDecimal.valueOf(soLuong))));
        } else {
            for (GioHangItem item: gioHang) {
                if (menu.getId().intValue() == item.getMenu().getId().intValue()) {
                    item.setSoLuong((int) (item.getSoLuong() + soLuong));
                    item.setTongTien(item.getMenu().getGiaBan().multiply(BigDecimal.valueOf(item.getSoLuong())));
                    httpSession.setAttribute("gioHang", gioHang);
                    return "redirect:/xemGioHang";
                }
            }
            gioHang.add(new GioHangItem(Math.toIntExact(soLuong), menu, menu.getGiaBan().multiply(BigDecimal.valueOf(soLuong))));

        }
        httpSession.setAttribute("gioHang", gioHang);
        return "redirect:/xemGioHang";
    }

    @GetMapping("/xemGioHang")
    public ModelAndView getGiohang() {
        ModelAndView modelAndView = new ModelAndView("cart_page");
        List<GioHangItem> gioHang = (List<GioHangItem>) httpSession.getAttribute("gioHang");
        modelAndView.addObject("giohang",gioHang);
        return modelAndView;
    }
    @GetMapping("/xoaMon/{id}")
    public String xoaGioHang(@PathVariable Long id){
        List<GioHangItem> gioHang = (List<GioHangItem>) httpSession.getAttribute("gioHang");
        if (gioHang != null) {
            for (GioHangItem item:gioHang) {
                if (item.getMenu().getId() ==  id) {
                    gioHang.remove(item);
                    httpSession.setAttribute("gioHang", gioHang);
                    break;
                }
            }
        }
        return "redirect:/xemGioHang";
    }
}
