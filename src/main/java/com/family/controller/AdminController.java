package com.family.controller;

import com.family.DTOpro.ProductsDTO;
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
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/admin/img";


    @Autowired
    ProductService productService;
    @Autowired
    StaffService staffService;
    private String emailAdmin = "admin@gmail.com";
    private String password = "123456789";

    @GetMapping("/admin")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("indexAdmin");
        return modelAndView;
    }
    //Staff
    @GetMapping("/admin/staffs")
    public String getAccstaff(Model model){
        model.addAttribute("Staffs", staffService.getAllStaff());
        return "Staff";
    }
    @GetMapping("/admin/staffs/add")
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
        return "redirect:/admin/staffs";
    }
    @GetMapping("/admin/staffs/delete/{id}")
    public String deletestaff(@PathVariable Long id){
        staffService.removestaffById(id);
        return "redirect:/admin/staffs";
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
            return "StaffAdd";
        }else {
            return "404";
        }

    }
    @GetMapping("/admin/products")
    public String getPro(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "Product";
    }//view all products

    @GetMapping("/admin/products/add")
    public String getProAdd(Model model){
        model.addAttribute("productsDTO", new ProductsDTO());
        return "ProductAdd";
    }// form add new product
    @PostMapping("/admin/products/add")
    public String postProAdd(@ModelAttribute("productsDTO") ProductsDTO productsDTO,
                             @RequestParam("productImage") MultipartFile fileProductImage,
                             @RequestParam("imgName") String imgName) throws IOException {
        //convert dto > entity
        Menu meNu = new Menu();
        BigDecimal number= productsDTO.getGiaBan();
        meNu.setId(productsDTO.getId());
        meNu.setTenMon(productsDTO.getTenMon());
//        meNu.setGiaBan(productsDTO.getGiaBan());
        meNu.setGiaBan(number);
        meNu.setLoai(productsDTO.getLoai());
        String imageUUID;
        if(!fileProductImage.isEmpty()){
            imageUUID = fileProductImage.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, fileProductImage.getBytes());
        }else {
            imageUUID = imgName;
        }//save image
        meNu.setHinhAnh(imageUUID);
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
            productService.updateProduct(productDTO);
            model.addAttribute("productsDTO", productDTO);
            return "ProductAdd";
        }else {
            return "404";
        }

    }//form edit prod

}

