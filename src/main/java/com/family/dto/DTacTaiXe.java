package com.family.dto;

import javax.persistence.*;

@Entity
@Table(name = "DTac_Tai_xe")
public class DTacTaiXe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tenTaiXe;

    private String diaChi;

    private String soDienThoai;

    private String bienSoXe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenTaiXe() {
        return tenTaiXe;
    }

    public void setTenTaiXe(String tenTaiXe) {
        this.tenTaiXe = tenTaiXe;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getBienSoXe() {
        return bienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }
}
