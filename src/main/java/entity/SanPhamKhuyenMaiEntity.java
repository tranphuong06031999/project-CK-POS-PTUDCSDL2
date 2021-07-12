/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Liêu Hy Quỳnh
 */
public class SanPhamKhuyenMaiEntity implements Serializable {

    private int maGiamGia;
    private int soTienGiamGia;
    private int maSanPham;
    private String tenSanPham;
    private String tenKhuyenMai;
    private int giaSanPham;
    private java.util.Date ngayBatDauKhuyenMai;
    private java.util.Date ngayKetThucKhuyenMai;
    private int toiDaKhuyenMai;

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getToiDaKhuyenMai() {
        return toiDaKhuyenMai;
    }

    public void setToiDaKhuyenMai(int toiDaKhuyenMai) {
        this.toiDaKhuyenMai = toiDaKhuyenMai;
    }

    public int getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(int maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public int getSoTienGiamGia() {
        return soTienGiamGia;
    }

    public void setSoTienGiamGia(int soTienGiamGia) {
        this.soTienGiamGia = soTienGiamGia;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public Date getNgayBatDauKhuyenMai() {
        return ngayBatDauKhuyenMai;
    }

    public void setNgayBatDauKhuyenMai(Date ngayBatDauKhuyenMai) {
        this.ngayBatDauKhuyenMai = ngayBatDauKhuyenMai;
    }

    public Date getNgayKetThucKhuyenMai() {
        return ngayKetThucKhuyenMai;
    }

    public void setNgayKetThucKhuyenMai(Date ngayKetThucKhuyenMai) {
        this.ngayKetThucKhuyenMai = ngayKetThucKhuyenMai;
    }

}
