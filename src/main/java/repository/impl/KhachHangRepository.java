/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.impl;

import entity.KhachHangEntity;
import helper.MySQLDataHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;
import repository.IKhachHangRepository;

/**
 *
 * @author NguyenThanhDat
 */
@Repository
public class KhachHangRepository implements IKhachHangRepository {
    
    @Override
    public ArrayList<KhachHangEntity> findAll() {
        ArrayList<KhachHangEntity> list = new ArrayList<>();
        String sql = "SELECT * from khachhang";
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                KhachHangEntity kh = new KhachHangEntity();
                kh.setMakh(rs.getInt("makh"));
                kh.setTenkh(rs.getString("tenkh"));
                kh.setSodu(rs.getInt("sodu"));
                kh.setSodienthoai(rs.getString("sodienthoai"));
                kh.setLoaikh(rs.getInt("loaikh"));
                list.add(kh);
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    @Override
    public ArrayList<KhachHangEntity> search(String keyword) {
        ArrayList<KhachHangEntity> list = new ArrayList<>();
        String sql = "SELECT * from khachhang where makh like '%" + keyword + "%' or tenkh like '%" + keyword + "%' or sodienthoai like '%" + keyword + "%'";
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                list = null;
            } else {
                do {
                    KhachHangEntity kh = new KhachHangEntity();
                    kh.setMakh(rs.getInt("makh"));
                    kh.setTenkh(rs.getString("tenkh"));
                    kh.setSodu(rs.getInt("sodu"));
                    kh.setSodienthoai(rs.getString("sodienthoai"));
                    kh.setLoaikh(rs.getInt("loaikh"));
                    list.add(kh);
                } while (rs.next());
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    @Override
    public boolean create(KhachHangEntity kh) {
        String sql = String.format("INSERT INTO `khachhang` (`tenkh`, `sodu`, `sodienthoai`) VALUES('%s','%d','%s')", kh.getTenkh(), 0, kh.getSodienthoai());
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
    public boolean update(KhachHangEntity kh) {
        String sql = String.format("update khachhang set tenkh = \"%s\", sodu = %d,sodienthoai=\"%s\" where makh = %s", kh.getTenkh(), kh.getSodu(), kh.getSodienthoai(), kh.getMakh());
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
    public KhachHangEntity findOne(int id) {
        KhachHangEntity kh = new KhachHangEntity();
        String sql = "SELECT * from khachhang where makh = " + id;
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                kh = null;
            } else {
                do {
                    kh.setMakh(rs.getInt("makh"));
                    kh.setTenkh(rs.getString("tenkh"));
                    kh.setSodu(rs.getInt("sodu"));
                    kh.setSodienthoai(rs.getString("sodienthoai"));
                    kh.setLoaikh(rs.getInt("loaikh"));
                } while (rs.next());
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return kh;
    }
    
    @Override
    public boolean updateSodu(int makh, int tiennap) {
        tiennap += this.findOne(makh).getSodu();
        String sql = String.format("update khachhang set sodu = %d where makh = %s", tiennap, makh);
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
    public boolean findOneByPhone(String sodienthoai) {
        String sql = "SELECT * from khachhang where sodienthoai = " + sodienthoai;
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                helper.close();
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        helper.close();
        return true;
    }
    
    @Override
    public boolean updateVip(int makh, int loaikh) {
        String sql = String.format("update khachhang set loaikh = %d where makh = %d", loaikh, makh);
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
    public int count() {
        int count = 0;
        try {
            String sql = "select count(*) count from khachhang";
            MySQLDataHelper helper = new MySQLDataHelper();
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                count = rs.getInt("count");
            }
            helper.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    
    @Override
    public ArrayList<KhachHangEntity> paging(int page) {
        page = (page - 1) * 10;
        ArrayList<KhachHangEntity> list = new ArrayList<>();
        String sql = "select * from khachhang limit " + page + ",10";
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                KhachHangEntity kh = new KhachHangEntity();
                kh.setMakh(rs.getInt("makh"));
                kh.setTenkh(rs.getString("tenkh"));
                kh.setSodu(rs.getInt("sodu"));
                kh.setSodienthoai(rs.getString("sodienthoai"));
                kh.setLoaikh(rs.getInt("loaikh"));
                list.add(kh);
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    @Override
    public ArrayList<KhachHangEntity> searchPaging(String keyword, int page) {
        page = (page - 1) * 10;
        ArrayList<KhachHangEntity> list = new ArrayList<>();
        String sql = "SELECT * from khachhang where makh like '%" + keyword + "%' or tenkh like '%" + keyword + "%' or sodienthoai like '%" + keyword + "%' limit " + page + ",10";
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                list = null;
            } else {
                do {
                    KhachHangEntity kh = new KhachHangEntity();
                    kh.setMakh(rs.getInt("makh"));
                    kh.setTenkh(rs.getString("tenkh"));
                    kh.setSodu(rs.getInt("sodu"));
                    kh.setSodienthoai(rs.getString("sodienthoai"));
                    kh.setLoaikh(rs.getInt("loaikh"));
                    list.add(kh);
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
            String sql = "SELECT count(*) count from khachhang where makh like '%" + keyword + "%' or tenkh like '%" + keyword + "%' or sodienthoai like '%" + keyword + "%'";
            MySQLDataHelper helper = new MySQLDataHelper();
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                count = rs.getInt("count");
            }
            helper.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}
