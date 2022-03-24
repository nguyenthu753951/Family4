package com.family.dto;

import javax.persistence.*;

@Entity
@Table(name = "chi_tiet_don_hang")
public class ChiTietDonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ma_mon_an")
    private Menu menu;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ma_don_hang")
    private DonHang donHang;

    private int soLuong;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public DonHang getDonHang() {
        return donHang;
    }

    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
