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
public class SanPhamEntity implements Serializable {

    private int maSP;
    private String tenSP;
    private String donVi;
    private int soLuong;
    private String ghiChu;

    public SanPhamEntity() {
    }

    public SanPhamEntity(int maSP, String tenSP, String donVi, int soLuong, String ghiChu) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.donVi = donVi;
        this.soLuong = soLuong;
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

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
