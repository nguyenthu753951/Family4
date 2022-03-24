package com.family.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "phieu_giao_hang")
public class PhieuGiaoHang {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date ngayLapPhieu;

    private Date thoiGianGiao;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ma_nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ma_tai_xe")
    private DTacTaiXe dTacTaiXe;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ma_don_hang")
    private DonHang donHang;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getNgayLapPhieu() {
        return ngayLapPhieu;
    }

    public void setNgayLapPhieu(Date ngayLapPhieu) {
        this.ngayLapPhieu = ngayLapPhieu;
    }

    public Date getThoiGianGiao() {
        return thoiGianGiao;
    }

    public void setThoiGianGiao(Date thoiGianGiao) {
        this.thoiGianGiao = thoiGianGiao;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public DTacTaiXe getdTacTaiXe() {
        return dTacTaiXe;
    }

    public void setdTacTaiXe(DTacTaiXe dTacTaiXe) {
        this.dTacTaiXe = dTacTaiXe;
    }

    public DonHang getDonHang() {
        return donHang;
    }

    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
    }
}

