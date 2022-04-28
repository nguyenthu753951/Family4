package com.family.controller;

import com.family.dto.KhachHang;
import com.family.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginUserCotroller {

    @Autowired
    KhachHangRepository khachHangRepository;

    @PostMapping("/user/login")

    public ModelAndView login(KhachHang model, Model dto) {
        KhachHang user=new KhachHang();
        dto.addAttribute("khachhang",user);
        ModelAndView loginUserView = new ModelAndView("khachHang");
        ModelAndView loginIndex = new ModelAndView("loginUser");
        ModelAndView homeView = new ModelAndView("index");
        if(model.getEmail().equals(user.getEmail())&& model.getMatKhau().equals(user.getMatKhau())){
            return homeView;
        }

        loginIndex.addObject("ErrorMessage","Tên đăng nhập hoặc mật khẩu không đúng");
        return loginIndex;
    }


    @GetMapping("/user/login")
    public ModelAndView login() {
        ModelAndView loginUserView = new ModelAndView("loginUser");
        return loginUserView;
    }

    @PostMapping("/user/signin")
    public ModelAndView signin(KhachHang khachHang){
        ModelAndView signinUserView = new ModelAndView("khachHang");  // lấy đâu ra ?
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

//        return signinUserView;
    }
    @GetMapping("/user/signin")
    public String signin(){
        return "signinUser";
    }
}
    
