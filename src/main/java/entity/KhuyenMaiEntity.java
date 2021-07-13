/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Liêu Hy Quỳnh
 */
public class KhuyenMaiEntity implements Serializable {

    private int phanTramGiamGia;
    private int giaTienGiam;
    private String tenKhuyenMai;

    public int getPhanTramGiamGia() {
        return phanTramGiamGia;
    }

    public void setPhanTramGiamGia(int phanTramGiamGia) {
        this.phanTramGiamGia = phanTramGiamGia;
    }

    public int getGiaTienGiam() {
        return giaTienGiam;
    }

    public void setGiaTienGiam(int giaTienGiam) {
        this.giaTienGiam = giaTienGiam;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

}
