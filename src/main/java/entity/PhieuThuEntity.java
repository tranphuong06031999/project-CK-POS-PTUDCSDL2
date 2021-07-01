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
public class PhieuThuEntity implements Serializable{

    private int maphieuthu;
    private int makh;
    private String tenkh;
    private int sotiennap;
    private int sodu;
    private String ngaylap;

    public PhieuThuEntity() {
    }

    public PhieuThuEntity(int maphieuthu, int makh, String tenkh, int sotiennap, int sodu, String ngaylap) {
        this.maphieuthu = maphieuthu;
        this.makh = makh;
        this.tenkh = tenkh;
        this.sotiennap = sotiennap;
        this.sodu = sodu;
        this.ngaylap = ngaylap;
    }

    public int getMaphieuthu() {
        return maphieuthu;
    }

    public void setMaphieuthu(int maphieuthu) {
        this.maphieuthu = maphieuthu;
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

    public int getSotiennap() {
        return sotiennap;
    }

    public void setSotiennap(int sotiennap) {
        this.sotiennap = sotiennap;
    }

    public int getSodu() {
        return sodu;
    }

    public void setSodu(int sodu) {
        this.sodu = sodu;
    }

    public String getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(String ngaylap) {
        this.ngaylap = ngaylap;
    }

}
