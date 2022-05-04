package com.family.dto;

import java.math.BigDecimal;

public class GioHangItem {

    public GioHangItem(int soLuong, Menu menu, BigDecimal tongTien) {
        this.soLuong = soLuong;
        this.menu = menu;
        this.tongTien = tongTien;
    }

    private int soLuong;

    private Menu menu;

    private BigDecimal tongTien;
    private static int TongSL;
    private  double TongThanhTien;

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien =  tongTien;
    }

    public int getTongSL() {
        return TongSL;
    }

    public static void setTongSL(int tongSL) {
        TongSL = tongSL;
    }

    public double getTongThanhTien() {
        return TongThanhTien;
    }

    public void setTongThanhTien(double tongThanhTien) {
        TongThanhTien = tongThanhTien;
    }
}
