package com.family.controller;

import com.family.dto.LoginAdmin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginAdminController {
    private String emailAdmin = "admin@gmail.com";

    private String password = "123456789";

    @PostMapping("/login")
    public ModelAndView login(LoginAdmin model) {
        ModelAndView loginAdminView = new ModelAndView("loginAdmin");
        ModelAndView adminIndex = new ModelAndView("indexAdmin");
        if(model.email.equals(this.emailAdmin) && model.password.equals(this.password)){
            return adminIndex ;
        }
        loginAdminView.addObject("ErrorMessage","Tên đăng nhập hoặc mật khẩu không đúng");
        return loginAdminView;
    }

    @GetMapping("/login")
        public ModelAndView loginIndex() {
            ModelAndView loginAdminView = new ModelAndView("loginAdmin");
            return loginAdminView;

    }
}
