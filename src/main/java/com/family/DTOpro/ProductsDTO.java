package com.family.DTOpro;


import java.math.BigDecimal;

public class ProductsDTO {
    private Long id;

    private String tenMon;

    private BigDecimal giaBan;

    private String loai;

    private String hinhAnh;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public ProductsDTO(){
        super();
    }
    public ProductsDTO(Long id, String tenMon, BigDecimal giaBan, String loai,  String hinhAnh) {
        this.id = id;
        this.tenMon = tenMon;
        this.giaBan = giaBan;
        this.loai = loai;
        this.hinhAnh = hinhAnh;
        }

}
