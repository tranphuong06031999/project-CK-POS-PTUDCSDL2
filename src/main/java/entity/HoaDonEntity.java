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
public class HoaDonEntity implements Serializable {

    private int hoadon_id;
    private int makh;
    private String ngaylap;
    private int tongtien;
    private int giam_khtt;

    public HoaDonEntity() {
    }

    public int getHoadon_id() {
        return hoadon_id;
    }

    public void setHoadon_id(int hoadon_id) {
        this.hoadon_id = hoadon_id;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public String getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(String ngaylap) {
        this.ngaylap = ngaylap;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public int getGiam_khtt() {
        return giam_khtt;
    }

    public void setGiam_khtt(int giam_khtt) {
        this.giam_khtt = giam_khtt;
    }

}
