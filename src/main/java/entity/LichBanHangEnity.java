/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author THAIHUYNH
 */
public class LichBanHangEnity extends AbstractEntity implements Serializable{
    private int hoadon_id;
    private String tenkh;
    private String tensp;
    private int soluong;
    private int tongtien;
    private String ngaylap;

    public int getHoadon_id() {
        return hoadon_id;
    }

    public void setHoadon_id(int hoadon_id) {
        this.hoadon_id = hoadon_id;
    }

    
    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public String getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(String ngaylap) {
        this.ngaylap = ngaylap;
    }
    
    
}
