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
public class KhachHangEntity implements Serializable{

    private int makh;
    private String tenkh;
    private int sodu;
    private String sodienthoai;
    private int loaikh;

    public int getLoaikh() {
        return loaikh;
    }

    public void setLoaikh(int loaikh) {
        this.loaikh = loaikh;
    }
    
    public KhachHangEntity() {
    }

    public KhachHangEntity(int makh, String tenkh, int sodu, String sodienthoai) {
        this.makh = makh;
        this.tenkh = tenkh;
        this.sodu = sodu;
        this.sodienthoai = sodienthoai;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public int getSodu() {
        return sodu;
    }

    public void setSodu(int sodu) {
        this.sodu = sodu;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

}
