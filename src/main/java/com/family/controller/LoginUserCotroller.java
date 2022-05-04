package com.family.controller;

import com.family.dto.GioHangItem;
import com.family.dto.KhachHang;
import com.family.dto.Menu;
import com.family.repository.KhachHangRepository;
import com.family.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class LoginUserCotroller {

    @Autowired
    KhachHangRepository khachHangRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    HttpSession httpSession;

    @PostMapping("/user/login")
    public ModelAndView login(KhachHang khachHang) {
        List<KhachHang> khachHangList = khachHangRepository.findByEmailAndMatKhau(khachHang.getEmail(), khachHang.getMatKhau());
        ModelAndView loginView = new ModelAndView("loginUser");
        ModelAndView homeIndex =  new ModelAndView("redirect:/");
        if (khachHangList.size() > 0) {
            return homeIndex;
        }
        loginView.addObject("ErrorMessage","Tên đăng nhập hoặc mật khẩu không đúng");
        return loginView;
    }

    @GetMapping("/user/login")
    public ModelAndView login() {
        ModelAndView loginView = new ModelAndView("loginUser");
        return loginView;
    }

    @PostMapping("/user/signin")
    public ModelAndView signin(KhachHang khachHang){
        ModelAndView signinView = new ModelAndView("signinUser");
        ModelAndView loginView=new ModelAndView("loginUser");

        if (khachHang.getTenKhachHang().isEmpty()) {
            signinView.addObject("ErrorMessage","Chưa nhập tên tài khoản");
            return signinView;
        }
        if (khachHang.getDiaChi().isEmpty()) {
            signinView.addObject("ErrorMessage","Chưa nhập tên địa chỉ");
            return signinView;
        }
        if (khachHang.getSoDienThoai().isEmpty()) {
            signinView.addObject("ErrorMessage","Chưa nhập số điện thoại");
            return signinView;
        }
        if (khachHang.getEmail().isEmpty()) {
            signinView.addObject("ErrorMessage","Chưa nhập email");
            return signinView;
        }
        if (khachHang.getMatKhau().isEmpty()) {
            signinView.addObject("ErrorMessage","Chưa nhập mật khẩu");
            return signinView;
        }

        khachHangRepository.save(khachHang);

        return loginView;
   }
    @GetMapping("/user/signin")
    public String signin(){
        return "signinUser";
    }
}