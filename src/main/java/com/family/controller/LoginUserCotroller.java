package com.family.controller;

import com.family.dto.KhachHang;
import com.family.dto.LoginAdmin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import sun.security.mscapi.CPublicKey;

@Controller
public class LoginUserCotroller {

    @PostMapping("/user/login")
    public ModelAndView login(KhachHang model) {
        ModelAndView loginUserView = new ModelAndView("KhachHang");
        ModelAndView loginIndex = new ModelAndView("loginUser");
        if(model.getEmail().equals(this.login())&& model.getMatKhau().equals(this.login())){
            return loginIndex;
        }
        loginUserView.addObject("ErrorMessage","Tên đăng nhập hoặc mật khẩu không đúng");
        return loginUserView;
    }

    @GetMapping("/user/login")
    public String login(){
        return "loginUser";
    }

    @PostMapping("/user/signin")
    public ModelAndView signin(KhachHang khachHang){
        ModelAndView signinUserView = new ModelAndView("KhachHang");
        ModelAndView signinIndex = new ModelAndView("signinUser");
        String tenKhachHang = new String("Ten khach hang");
        String diaChi = new String("Dia chi");
        String soDienThoai = new String("So Dien Thoai");
        String email = new String("Email");
        String matKhau = new String("Mat khau");
        if (signinUserView.isEmpty()){
            if (tenKhachHang.equals(tenKhachHang)) {
                signinIndex.addObject("ErrorMessage","Chưa nhập tên tài khoản");
            }
            if (!diaChi.equals(diaChi)) {
                signinIndex.addObject("ErrorMessage","Chưa nhập tên địa chỉ");
            }
            if (!soDienThoai.equals(soDienThoai)) {
                signinIndex.addObject("ErrorMessage","Chưa nhập số điện thoại");
            }
            if (!email.equals(email)) {
                signinIndex.addObject("ErrorMessage","Chưa nhập email");
            }
            if (!matKhau.equals(matKhau)) {
                signinIndex.addObject("ErrorMessage","Chưa nhập mật khẩu");
            }
        }else {
            signinUserView.addObject(khachHang);
            signinUserView.hasView();
            signinIndex.addObject("ErrorMessage","Đăng kí thành công");



        return login(khachHang);
    }

        return signinUserView;
    }
}
    
