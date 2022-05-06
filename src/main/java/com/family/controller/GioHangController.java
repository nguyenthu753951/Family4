package com.family.controller;
import com.family.dto.*;
import com.family.repository.KhachHangRepository;
import com.family.repository.ProductRepository;
import com.family.repository.ThanhToanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

@Controller
public class GioHangController extends HttpServlet {

    @Autowired
    KhachHangRepository khachHangRepository;
    @Autowired
    ThanhToanRepository thanhToanRepository;

    @Autowired
    ProductRepository productRepository;

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
    public BigDecimal gteAmount() {
        List<GioHangItem> gioHang = (List<GioHangItem>) httpSession.getAttribute("gioHang");
        return gioHang.stream().map(i->i.getTongTien()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @GetMapping("/xemGioHang")
    public ModelAndView getGiohang() {
        ModelAndView modelAndView = new ModelAndView("cart_page");
        List<GioHangItem> gioHang = (List<GioHangItem>) httpSession.getAttribute("gioHang");
        modelAndView.addObject("giohang",gioHang);
        modelAndView.addObject("total",gteAmount());
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
    /*@RequestMapping(value = "checkout_page.html", method = RequestMethod.POST)
    public String thanhToan(@ModelAttribute("DonHang") DonHang donHang) {
        List<GioHangItem> gioHang = (List<GioHangItem>) httpSession.getAttribute("GioHangItem");
        if (gioHang == null) {
            gioHang = new ArrayList<>();
        }
        donHang.setNgayHenGiao(new Date().getTime());
        ThanhToanItem.setThanhToanItemTinhTrang(true);
        for (GioHangItem monHang : gioHang) {
            ThanhToanItem thanhToanItem = new ThanhToanItem();
            thanhToanItem.setDonHang(donHang);
            thanhToanItem.setMenu(monHang.getMenu());
            thanhToanItem.setThanhToanItemGia(thanhToanItem.getThanhToanItemGia());
            thanhToanItem.setThanhToanItemSoLuong(thanhToanItem.getThanhToanItemSoLuong());
            thanhToanItem.setThanhToanItemId(thanhToanItem.getThanhToanItemId());
            //thanhToanItem.create(ThanhToanItem);
        }
        ArrayList<GioHangItem> gioHangItems = new ArrayList<>();
        httpSession.setAttribute("thanhToan", gioHangItems);
        return "redirect:/checkout_page.html";
    }*/
    @GetMapping("/thanhToan")
    public ModelAndView thanhToan(KhachHang khachHang) {

        List<KhachHang> khachHangList = khachHangRepository.findByEmailAndMatKhau(khachHang.getEmail(), khachHang.getMatKhau());
        ModelAndView thanhToan = new ModelAndView("checkout_page");
        ModelAndView login=new ModelAndView("loginUser");
        if(khachHangList.size() == 0){
            return thanhToan;
        }
        return login;
    }
    /*public ModelAndView getCheckOut (KhachHang khachHang, GioHangItem gioHangItem){
        List<GioHangItem> gioHang = (List<GioHangItem>) httpSession.getAttribute("gioHang");
        if (gioHang == null || gioHang.size() == 0) {
            gioHang = new ArrayList<>();
        }
        List<KhachHang> khachHangList = khachHangRepository.findByEmailAndMatKhau(khachHang.getEmail(), khachHang.getMatKhau());
        ModelAndView checkoutView = new ModelAndView("checkout_page");
        if (khachHangList.size() > 0) {
            return checkoutView;
        }
        checkoutView.addObject("ErrorMessage","Tên đăng nhập hoặc mật khẩu không đúng");
        return checkoutView;

        DonHang donHang = (DonHang) httpSession.getAttribute("donHang");
        try {
            DonHang donHang = (DonHang) httpSession.getAttribute("donHang");
            for (DonHang donHang1 : donHang) {
                donHang1.getMenu().getId().intValue();
                donHang1.getNgayHenGiao();
                donHang1.getTongTien();
                donHang1.getKhachHang();
            }
            ArrayList<GioHangItem> gioHangItems = new ArrayList<>();
            httpSession.setAttribute("thanhToan", thanhToan());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }*/

}
