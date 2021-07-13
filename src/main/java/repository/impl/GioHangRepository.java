/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.impl;

import entity.GioHangEntity;
import entity.KhuyenMaiEntity;
import entity.SanPhamKhuyenMaiEntity;
import helper.MySQLDataHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import repository.IGioHangRepository;

/**
 *
 * @author NguyenThanhDat
 */
@Repository
public class GioHangRepository implements IGioHangRepository {

    @Override
    public boolean create(GioHangEntity cart) {
        String sql = "INSERT INTO giohang(makh,masp, tensp, soluong, gia,giatong) VALUES (" + "'"
                + cart.getMakh() + "'" + ", " + "'"
                + cart.getMasp() + "'" + ", " + "'"
                + cart.getTensp() + "'" + ", " + "'"
                + cart.getSoluong() + "'" + ", " + "'"
                + cart.getGia() + "'" + ", " + "'"
                + cart.getGiatong() + "')";
        MySQLDataHelper helper = new MySQLDataHelper();
        helper.open();
        int n = helper.excuteUpdate(sql);
        if (n == 1) {
            helper.close();
            return true;
        }
        helper.close();
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from giohang where magiohang = " + id;
        MySQLDataHelper helper = new MySQLDataHelper();
        helper.open();
        int n = helper.excuteUpdate(sql);
        if (n == 1) {
            helper.close();
            return true;
        }
        helper.close();
        return false;
    }

    @Override
    public boolean incremental(int id, int price, int qty) {
        GioHangEntity cart = this.findOne(id);
        qty += cart.getSoluong();
        price *= qty;
        String sql = "update giohang set soluong = " + qty + ", giatong = " + price + " where magiohang = " + id;
        MySQLDataHelper helper = new MySQLDataHelper();
        helper.open();
        int n = helper.excuteUpdate(sql);
        if (n == 1) {
            helper.close();
            return true;
        }
        helper.close();
        return false;
    }

    @Override
    public boolean update(GioHangEntity cart) {
        int total = cart.getSoluong() * cart.getGia();
        String sql = "update giohang set soluong = " + cart.getSoluong() + ", giatong = " + total + " where magiohang = " + cart.getMagiohang();
        MySQLDataHelper helper = new MySQLDataHelper();
        helper.open();
        int n = helper.excuteUpdate(sql);
        if (n == 1) {
            helper.close();
            return true;
        }
        helper.close();
        return false;
    }

    @Override
    public GioHangEntity findOne(int id) {
        GioHangEntity cart = new GioHangEntity();
        String sql = "select soluong from giohang where magiohang = " + id;
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                cart.setSoluong(rs.getInt("soluong"));
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cart;
    }

    @Override
    public int isExists(int masp, int makh) {
        int cart_id = 0;
        String sql = "select * from giohang where masp = " + masp + " and makh = " + makh;
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                cart_id = -1;
            } else {
                do {
                    cart_id = rs.getInt("magiohang");
                } while (rs.next());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        helper.close();
        return cart_id;
    }

    @Override
    public ArrayList<GioHangEntity> findAll(int makh) {
        ArrayList<GioHangEntity> list = new ArrayList<>();
        String sql = "select * from giohang where makh = " + makh;
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                list = null;
            } else {
                do {
                    GioHangEntity cart = new GioHangEntity();
                    cart.setMakh(rs.getInt("makh"));
                    cart.setMagiohang(rs.getInt("magiohang"));
                    cart.setTensp(rs.getString("tensp"));
                    cart.setMasp(rs.getInt("masp"));
                    cart.setGia(rs.getInt("gia"));
                    cart.setSoluong(rs.getInt("soluong"));
                    cart.setGiatong(rs.getInt("giatong"));
                    list.add(cart);
                } while (rs.next());
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int totalPrice(int makh) {
        int total = 0;
        String sql = "select SUM(giatong) as total  from giohang where makh = " + makh;
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                total = rs.getInt("total");
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return total;
    }
    
    @Override
    public int quantity(int makh){
        int total = 0;
        String sql = "select SUM(soluong) as total  from giohang where makh = " + makh;
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                total = rs.getInt("total");
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return total;
    }

    @Override
    public GioHangEntity findById(int magiohang) {
        GioHangEntity cart = new GioHangEntity();
        String sql = "select * from giohang where magiohang = " + magiohang;
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                cart.setMakh(rs.getInt("makh"));
                cart.setMagiohang(rs.getInt("magiohang"));
                cart.setTensp(rs.getString("tensp"));
                cart.setMasp(rs.getInt("masp"));
                cart.setGia(rs.getInt("gia"));
                cart.setSoluong(rs.getInt("soluong"));
                cart.setGiatong(rs.getInt("giatong"));
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cart;
    }
    
    @Override
    public SanPhamKhuyenMaiEntity getThongTinKhuyenMaiSanPham(int masp) {
        SanPhamKhuyenMaiEntity km = new SanPhamKhuyenMaiEntity();
        String sql = "select sp.masp, sp.tensp, sp.khuyenmai,sp.gia, km.ngay_bat_dau, km.ngay_ket_thuc, km.giam_gia, km.toi_da, km.ten"
                + " from sanpham sp, khuyenmai km where sp.masp = " + masp + " and sp.khuyenmai = km.khuyenmai_id";
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                km.setToiDaKhuyenMai(rs.getInt("toi_da"));
                km.setMaGiamGia(rs.getInt("giam_gia"));
                km.setMaSanPham(rs.getInt("masp"));
                km.setTenSanPham(rs.getString("tensp"));
                km.setGiaSanPham(rs.getInt("gia"));
                km.setTenKhuyenMai(rs.getString("ten"));
                km.setNgayBatDauKhuyenMai(rs.getDate("ngay_bat_dau"));
                km.setNgayKetThucKhuyenMai(rs.getDate("ngay_ket_thuc"));
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return km;
    }

    @Override
    public int soLuongSanPhamTrongGioHang(int masp, int makh){
        int total = 0;
        String sql = "select SUM(soluong) as total  from giohang where makh = " + makh + " and masp = " + masp;
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                total = rs.getInt("total");
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return total;
    }
         
    @Override
    public boolean xoaGioHang( int makh ){
        
        String sql = "delete from giohang where makh = " + makh;
        MySQLDataHelper helper = new MySQLDataHelper();
        helper.open();
        int n = helper.excuteUpdate(sql);
        if (n == 1) {
            helper.close();
            return true;
        }
        helper.close();
        return false;
    }    
}
