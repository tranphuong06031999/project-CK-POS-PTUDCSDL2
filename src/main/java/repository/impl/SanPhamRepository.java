/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.impl;

import entity.SanPhamEntity;
import helper.MySQLDataHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import repository.ISanPhamRepository;

/**
 *
 * @author Trần Đinh Phương
 */
@Repository
public class SanPhamRepository implements ISanPhamRepository {

    @Override
    public SanPhamEntity findById(int masp) {
        SanPhamEntity sp = new SanPhamEntity();
        String sql = "SELECT masp, tensp, donvi, soluong, ghichu FROM sanpham WHERE masp = " + masp;
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                sp.setMaSP(rs.getInt("masp"));
                sp.setTenSP(rs.getString("tensp"));
                sp.setDonVi(rs.getString("donvi"));
                sp.setSoLuong(rs.getInt("soluong"));
                sp.setGhiChu(rs.getString("ghichu"));
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sp;
    }

    @Override
    public boolean create(SanPhamEntity sp) {
        String sql = String.format("INSERT INTO sanpham (tensp, donvi, soluong, ghichu) VALUES ('%s', '%s', '%d', '%s')",
                sp.getTenSP(),
                sp.getDonVi(),
                sp.getSoLuong(),
                sp.getGhiChu());
        MySQLDataHelper helper = new MySQLDataHelper();
        helper.open();
        int n = helper.excuteUpdate(sql);
        if (n > 0) {
            helper.close();
            return true;
        } else {
            helper.close();
            return false;
        }
    }

    @Override
    public boolean update(SanPhamEntity sp) {
        String sql = String.format("UPDATE sanpham SET tensp = '%s', donvi = '%s', soluong = '%d', ghichu = '%s' WHERE masp = '%d'",
                sp.getTenSP(),
                sp.getDonVi(),
                sp.getSoLuong(),
                sp.getGhiChu(),
                sp.getMaSP());
        MySQLDataHelper helper = new MySQLDataHelper();
        helper.open();
        int n = helper.excuteUpdate(sql);
        if (n > 0) {
            helper.close();
            return true;
        } else {
            helper.close();
            return false;
        }
    }

    @Override
    public ArrayList<SanPhamEntity> search(String keyword) {
        ArrayList<SanPhamEntity> list = new ArrayList<>();
        String sql = "SELECT * from sanpham where lower(tensp) like '%" + keyword + "%'";
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                list = null;
            } else {
                do {
                    SanPhamEntity sp = new SanPhamEntity();
                    sp.setMaSP(rs.getInt("masp"));
                    sp.setTenSP(rs.getString("tensp"));
                    sp.setDonVi(rs.getString("donvi"));
                    sp.setSoLuong(rs.getInt("soluong"));
                    sp.setGhiChu(rs.getString("ghichu"));
                    list.add(sp);
                } while (rs.next());
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public ArrayList<SanPhamEntity> productsList() {
        ArrayList<SanPhamEntity> list = new ArrayList<>();
        String sql = "SELECT masp, tensp, donvi, soluong, ghichu FROM sanpham";
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                list = null;
            } else {
                do {
                    SanPhamEntity sp = new SanPhamEntity();
                    sp.setMaSP(rs.getInt("masp"));
                    sp.setTenSP(rs.getString("tensp"));
                    sp.setDonVi(rs.getString("donvi"));
                    sp.setSoLuong(rs.getInt("soluong"));
                    sp.setGhiChu(rs.getString("ghichu"));
                    list.add(sp);
                } while (rs.next());
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public int count() {
        int count = 0;
        try {
            String sql = "select count(*) count from sanpham";
            MySQLDataHelper helper = new MySQLDataHelper();
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                count = rs.getInt("count");
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    @Override
    public ArrayList<SanPhamEntity> paging(int page) {
        page = (page - 1) * 10;
        ArrayList<SanPhamEntity> list = new ArrayList<>();
        String sql = "SELECT masp, tensp, donvi, soluong, ghichu FROM sanpham limit " + page + ",10";
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                list = null;
            } else {
                do {
                    SanPhamEntity sp = new SanPhamEntity();
                    sp.setMaSP(rs.getInt("masp"));
                    sp.setTenSP(rs.getString("tensp"));
                    sp.setDonVi(rs.getString("donvi"));
                    sp.setSoLuong(rs.getInt("soluong"));
                    sp.setGhiChu(rs.getString("ghichu"));
                    list.add(sp);
                } while (rs.next());
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public ArrayList<SanPhamEntity> searchPaging(String keyword, int page) {
        page = (page - 1) * 10;
        ArrayList<SanPhamEntity> list = new ArrayList<>();
        String sql = "SELECT * from sanpham where lower(tensp) like '%" + keyword + "%' limit " + page + ",10";
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                list = null;
            } else {
                do {
                    SanPhamEntity sp = new SanPhamEntity();
                    sp.setMaSP(rs.getInt("masp"));
                    sp.setTenSP(rs.getString("tensp"));
                    sp.setDonVi(rs.getString("donvi"));
                    sp.setSoLuong(rs.getInt("soluong"));
                    sp.setGhiChu(rs.getString("ghichu"));
                    list.add(sp);
                } while (rs.next());
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public int countSearch(String keyword) {
        int count = 0;
        try {
            String sql = "SELECT count(*) count from sanpham where lower(tensp) like '%" + keyword + "%'";
            MySQLDataHelper helper = new MySQLDataHelper();
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                count = rs.getInt("count");
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    @Override
    public boolean updateSoluong(int masp, int soluong) {
        String sql = "update sanpham set soluong = " + soluong + " where masp = " + masp;
        MySQLDataHelper helper = new MySQLDataHelper();
        helper.open();
        int n = helper.excuteUpdate(sql);
        if (n > 0) {
            helper.close();
            return true;
        } else {
            helper.close();
            return false;
        }
    }

    @Override
    public ArrayList<Integer> getMaSanPham(int makh) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        String sql = "select sp.masp from giohang gh join sanpham sp on gh.masp=sp.masp where gh.makh = " + makh;
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                list = null;
            } else {
                do {
                    int km = rs.getInt("masp");
                    list.add(km);
                } while (rs.next());
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public int getGiaSanPham(int masp) {
        int giasp = 0;
        String sql = "select sp.gia from sanpham sp where sp.masp = " + masp;
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                giasp = 0;
            } else {
                do {
                    giasp = rs.getInt("gia");
                } while (rs.next());
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return giasp;
    }

}
