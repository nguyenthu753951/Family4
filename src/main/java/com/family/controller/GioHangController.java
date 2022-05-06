package com.family.controller;
import com.family.dto.*;
import com.family.repository.ChiTietDonHangRepository;
import com.family.repository.DonHangRepository;
import com.family.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class GioHangController extends HttpServlet {

@Autowired
    DonHangRepository donHangRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ChiTietDonHangRepository chiTietDonHangRepository;

    @Autowired
    HttpSession httpSession;


    @GetMapping("/themvaogiohang/{id}/{soLuong}")
    public String themVaoGioHang̣(@PathVariable Long id, @PathVariable Long soLuong,Model model)  {
        Menu menu = productRepository.getMenuByid(id).get();
        int sl=1;
        double total = 0;
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
                }
            }
            gioHang.add(new GioHangItem(Math.toIntExact(soLuong), menu, menu.getGiaBan().multiply(BigDecimal.valueOf(soLuong))));
        }
        httpSession.setAttribute("gioHang", gioHang);
        return "redirect:/xemGioHang";
    }
    public BigDecimal getAmount() {
        List<GioHangItem> gioHang = (List<GioHangItem>) httpSession.getAttribute("gioHang");
        if (gioHang ==  null) {
            return new BigDecimal(0);
        }
        return gioHang.stream().map(i->i.getTongTien()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @GetMapping("/xemGioHang")
    public ModelAndView getGiohang() {
        ModelAndView modelAndView = new ModelAndView("cart_page");
        List<GioHangItem> gioHang = (List<GioHangItem>) httpSession.getAttribute("gioHang");
        modelAndView.addObject("giohang",gioHang);
        modelAndView.addObject("total",getAmount());
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
    @GetMapping("/updateGioHang/{id}/{soLuong}")
    public String updateGioHang(@PathVariable Long id, @PathVariable Long soLuong) {
        Menu menu = productRepository.getMenuByid(id).get();
        List<GioHangItem> gioHang = (List<GioHangItem>) httpSession.getAttribute("gioHang");
        if (gioHang == null || gioHang.size() == 0) {
            gioHang = new ArrayList<>();
        } else {
            for (GioHangItem monHang : gioHang) {
                if (menu.getId().intValue() == monHang.getMenu().getId().intValue()) {
                    monHang.setSoLuong(Math.toIntExact(soLuong));
                    monHang.setTongTien(monHang.getMenu().getGiaBan().multiply(BigDecimal.valueOf(monHang.getSoLuong())));
                    httpSession.setAttribute("gioHang", gioHang);
                    break;
                }
            }
        }
        return "redirect:/xemGioHang";
    }
    @GetMapping("/thanhToan")
    public ModelAndView thanhToan() {
        ModelAndView thanhToan = new ModelAndView("checkout");
        List<GioHangItem> gioHang = (List<GioHangItem>) httpSession.getAttribute("gioHang");
        thanhToan.addObject("giohang",gioHang);
        thanhToan.addObject("total",getAmount());
        return thanhToan;
    }

    @PostMapping("/thanhToan")
    public ModelAndView getpayment(String ngayHenGiao) throws ParseException {
        ModelAndView thanhToan = new ModelAndView("checkout");
        ModelAndView homeIndex =  new ModelAndView("redirect:/");
        ModelAndView userLogin =  new ModelAndView("redirect:/user/login");
        List<GioHangItem> gioHang = (List<GioHangItem>) httpSession.getAttribute("gioHang");
        KhachHang khachHangDangNhap = (KhachHang) httpSession.getAttribute("KhachHangDangNhap");

        if (khachHangDangNhap == null) {
            return userLogin;
        }
        DonHang donHang = storeDonHang(khachHangDangNhap, ngayHenGiao);
        for (GioHangItem gioHangItem: gioHang ) {
            ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
            chiTietDonHang.setDonHang(donHang);
            chiTietDonHang.setMenu(gioHangItem.getMenu());
            chiTietDonHang.setSoLuong(gioHangItem.getSoLuong());
            chiTietDonHangRepository.save(chiTietDonHang);
        }
        httpSession.removeAttribute("gioHang");
        return homeIndex;
    }

    private DonHang storeDonHang(KhachHang khachHang, String ngayHenGiao) throws ParseException {
        DonHang donHang = new DonHang();
        donHang.setKhachHang(khachHang);
        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(ngayHenGiao);
        donHang.setNgayHenGiao(date);
        donHang.setNgayLapHD(new Date());
        donHang.setTongTien(getAmount());
        donHangRepository.save(donHang);
        return donHang;
    }
}



