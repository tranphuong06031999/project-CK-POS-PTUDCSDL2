/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.impl;

import entity.HoaDonEntity;
import helper.MySQLDataHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;
import repository.IHoaDonRepository;

/**
 *
 * @author NguyenThanhDat
 */
@Repository
public class HoaDonRepository implements IHoaDonRepository {
    
    @Override
    public ArrayList<HoaDonEntity> findAll() {
        ArrayList<HoaDonEntity> list = new ArrayList<>();
        String sql = "select * from hoadon";
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                HoaDonEntity hd = new HoaDonEntity();
                hd.setHoadon_id(rs.getInt("hoadon_id"));
                hd.setMakh(rs.getInt("makh"));
                hd.setNgaylap(rs.getString("ngaylap"));
                hd.setTongtien(rs.getInt("tongtien"));
                list.add(hd);
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    @Override
    public ArrayList<HoaDonEntity> search(int makh) {
        ArrayList<HoaDonEntity> list = new ArrayList<>();
        String sql = "select * from hoadon where makh = " + makh;
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                list = null;
            } else {
                do {
                    HoaDonEntity hd = new HoaDonEntity();
                    hd.setHoadon_id(rs.getInt("hoadon_id"));
                    hd.setMakh(rs.getInt("makh"));
                    hd.setNgaylap(rs.getString("ngaylap"));
                    hd.setTongtien(rs.getInt("tongtien"));
                    list.add(hd);
                } while (rs.next());
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    @Override
    public HoaDonEntity findOne(int mahd) {
        HoaDonEntity hd = new HoaDonEntity();
        String sql = "select * from hoadon where hoadon_id = " + mahd;
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                hd.setHoadon_id(rs.getInt("hoadon_id"));
                hd.setMakh(rs.getInt("makh"));
                hd.setNgaylap(rs.getString("ngaylap"));
                hd.setTongtien(rs.getInt("tongtien"));
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return hd;
    }
    
    @Override
    public int count() {
        int count = 0;
        try {
            String sql = "select count(*) count from hoadon";
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
    public ArrayList<HoaDonEntity> paging(int page) {
        page = (page - 1) * 10;
        ArrayList<HoaDonEntity> list = new ArrayList<>();
        String sql = "select * from hoadon limit " + page + ",10";
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                HoaDonEntity hd = new HoaDonEntity();
                hd.setHoadon_id(rs.getInt("hoadon_id"));
                hd.setMakh(rs.getInt("makh"));
                hd.setNgaylap(rs.getString("ngaylap"));
                hd.setTongtien(rs.getInt("tongtien"));
                list.add(hd);
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    @Override
    public ArrayList<HoaDonEntity> searchPaging(int makh, int page) {
        page = (page - 1) * 10;
        ArrayList<HoaDonEntity> list = new ArrayList<>();
        String sql = "select * from hoadon where makh = " + makh + " limit " + page + ",10";
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                list = null;
            } else {
                do {
                    HoaDonEntity hd = new HoaDonEntity();
                    hd.setHoadon_id(rs.getInt("hoadon_id"));
                    hd.setMakh(rs.getInt("makh"));
                    hd.setNgaylap(rs.getString("ngaylap"));
                    hd.setTongtien(rs.getInt("tongtien"));
                    list.add(hd);
                } while (rs.next());
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    @Override
    public int countSearch(int makh) {
        int count = 0;
        try {
            String sql = "select count(*) count from hoadon where makh = " + makh;
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
