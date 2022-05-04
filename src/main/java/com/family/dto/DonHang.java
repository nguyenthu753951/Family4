package com.family.dto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "don_hang")
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal phiGiaoHang;

    private Date ngayLapHD;

    private long ngayHenGiao;

    private String loai;

    private BigDecimal tongTien;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ma_mon_an")
    private Menu menu;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maKhachHang")
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maTaiXe")
    private DTacTaiXe dTacTaiXe;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ma_giam_gia")
    private PhieuGiamGia phieuGiamGia ;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPhiGiaoHang() {
        return phiGiaoHang;
    }

    public void setPhiGiaoHang(BigDecimal phiGiaoHang) {
        this.phiGiaoHang = phiGiaoHang;
    }

    public Date getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(Date ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public long getNgayHenGiao() {
        return ngayHenGiao;
    }

    public void setNgayHenGiao(long ngayHenGiao) {
        this.ngayHenGiao = ngayHenGiao;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public DTacTaiXe getdTacTaiXe() {
        return dTacTaiXe;
    }

    public void setdTacTaiXe(DTacTaiXe dTacTaiXe) {
        this.dTacTaiXe = dTacTaiXe;
    }

    public PhieuGiamGia getPhieuGiamGia() {
        return phieuGiamGia;
    }

    public void setPhieuGiamGia(PhieuGiamGia phieuGiamGia) {
        this.phieuGiamGia = phieuGiamGia;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }
}
