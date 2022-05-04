package com.family.controller;

import com.family.dto.Menu;
import com.family.dto.NhanVien;
import com.family.service.StaffService;
import com.family.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("indexAdmin");
        return modelAndView;
    }
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/img";
    @Autowired
    ProductService productService;
    @Autowired
    StaffService staffService;
    private String emailAdmin = "admin@gmail.com";
    private String password = "123456789";


    //Staff
    @GetMapping("/admin/Staffs")
    public String getAccstaff(Model model){
        model.addAttribute("Staffs", staffService.getAllStaff());
        return "Staff";
    }
    @GetMapping("/admin/Staffs/add")
    public String getstaffAdd(Model model){
        model.addAttribute("staffDTO", new NhanVien());
        return "staffAdd";
    }
    @PostMapping("/admin/staffs/add")
    public String poststaffAdd(@ModelAttribute NhanVien nhanVien) {
        //convert dto > entity
        NhanVien staff = new NhanVien();
        nhanVien.setId(staff.getId());
        nhanVien.setHoTenNV(staff.getHoTenNV());
        nhanVien.setChucVu(staff.getChucVu());
        nhanVien.setEmailNV(staff.getEmailNV());
        nhanVien.setGioiTinh(staff.getGioiTinh());
        nhanVien.setNgaySinh(staff.getNgaySinh());
        nhanVien.setSoDienThoai(staff.getSoDienThoai());
        staffService.updatestaff(staff);
        return "redirect:/admin/Staffs";
    }
    @GetMapping("/admin/Staff/delete/{id}")
    public String deletestaff(@PathVariable Long id){
        staffService.removestaffById(id);
        return "redirect:admin/Staffs";
    }//delete 1 staff

    @GetMapping("/admin/staffs/update/{id}")
    public String updatestaff(@PathVariable Long id, Model model){
       Optional<NhanVien> opStaff = staffService.getstaffById(id);
        if (opStaff.isPresent()){
            NhanVien staff = opStaff.get();
            //convert entity > dto
            NhanVien nhanVien = new NhanVien();
            nhanVien.setId(staff.getId());
            nhanVien.setHoTenNV(staff.getHoTenNV());
            nhanVien.setChucVu(staff.getChucVu());
            nhanVien.setEmailNV(staff.getEmailNV());
            nhanVien.setGioiTinh(staff.getGioiTinh());
            nhanVien.setNgaySinh(staff.getNgaySinh());
            nhanVien.setSoDienThoai(staff.getSoDienThoai());
            model.addAttribute("staffDTO", nhanVien);
            return "staffAdd";
        }else {
            return "404";
        }

    }
    @GetMapping("/admin/Menu")
    public String getPro(Model model){
        model.addAttribute("Menu", productService.getAllProduct());
        return "menu";
    }//view all products

    @GetMapping("/admin/products/add")
    public String getProAdd(Model model){
        model.addAttribute("Menu", new Menu());
        return "productsAdd";
    }// form add new product
    @PostMapping("/admin/products/add")
    public String postProAdd(@ModelAttribute("Menu") Menu menu,
                             @RequestParam("MenuImage") MultipartFile fileProductImage,
                             @RequestParam("imgName") String imgName) throws IOException {
        //convert dto > entity
        Menu meNu = new Menu();
        meNu.setId(menu.getId());
        meNu.setTenMon(menu.getTenMon());
        meNu.setGiaBan(menu.getGiaBan());
        meNu.setLoai(menu.getLoai());
        String imageUUID;
        if(!fileProductImage.isEmpty()){
            imageUUID = fileProductImage.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, fileProductImage.getBytes());
        }else {
            imageUUID = imgName;
        }//save image
        menu.setHinhAnh(imageUUID);

        productService.updateProduct(meNu);
        return "redirect:/admin/products";
    }//form add new product > do add
    @GetMapping("/admin/products/delete/{id}")
    public String deletePro(@PathVariable long id){
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }//delete 1 product

    @GetMapping("/admin/products/update/{id}")
    public String updatePro(@PathVariable long id, Model model){
        Optional<Menu> opMenu = productService.getMenuByid(id);
        if (opMenu.isPresent()){
           Menu menu = opMenu.get();
            //convert entity > dto
            Menu productDTO = new Menu();
            productDTO.setId(menu.getId());
            productDTO.setTenMon(menu.getTenMon());
            productDTO.setGiaBan(menu.getGiaBan());
            productDTO.setHinhAnh(menu.getHinhAnh());
            productDTO.setLoai(menu.getLoai());

            model.addAttribute("productDTO", productDTO);
            return "productsAdd";
        }else {
            return "404";
        }

    }//form edit prod

}

