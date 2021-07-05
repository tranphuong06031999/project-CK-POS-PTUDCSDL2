/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author NguyenThanhDat
 */
public class ChiTietHoaDonEntity implements Serializable{
    private int chitiethoadon_id;
    private int ma_hoadon;
    private int masp;
    private int soluong;
    private int giagoc;
    private int giasaukhuyenmai;
    private int tongtien;
    private float chietkhau;

    public ChiTietHoaDonEntity() {
    }

    public int getChitiethoadon_id() {
        return chitiethoadon_id;
    }

    public void setChitiethoadon_id(int chitiethoadon_id) {
        this.chitiethoadon_id = chitiethoadon_id;
    }

    public int getMa_hoadon() {
        return ma_hoadon;
    }

    public void setMa_hoadon(int ma_hoadon) {
        this.ma_hoadon = ma_hoadon;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGiagoc() {
        return giagoc;
    }

    public void setGiagoc(int giagoc) {
        this.giagoc = giagoc;
    }

    public int getGiasaukhuyenmai() {
        return giasaukhuyenmai;
    }

    public void setGiasaukhuyenmai(int giasaukhuyenmai) {
        this.giasaukhuyenmai = giasaukhuyenmai;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public float getChietkhau() {
        return chietkhau;
    }

    public void setChietkhau(float chietkhau) {
        this.chietkhau = chietkhau;
    }
    
    
}
