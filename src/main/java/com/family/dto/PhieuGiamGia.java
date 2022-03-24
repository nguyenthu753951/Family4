package com.family.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "phieu_giam_gia")
public class PhieuGiamGia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int phanTram;

    private Date hanSuDung;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(int phanTram) {
        this.phanTram = phanTram;
    }

    public Date getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(Date hanSuDung) {
        this.hanSuDung = hanSuDung;
    }
}
