/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Trần Đinh Phương
 */
public class SanPhamEntity implements Serializable{
    private int maSP;
    private String tenSP;
    private int loai;
    private String donVi;
    private int soLuong;
    private int gia;
    private String ghiChu;

    public SanPhamEntity() {
    }

    public SanPhamEntity(int maSP, String tenSP, int loai, String donVi, int soLuong, int gia, String ghiChu) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loai = loai;
        this.donVi = donVi;
        this.soLuong = soLuong;
        this.gia = gia;
        this.ghiChu = ghiChu;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
}
