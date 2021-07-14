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
                hd.setGiam_khtt(rs.getInt("giam_khtt"));
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
    
    @Override
    public int getMaHoaDonCuoi() {
        int ma = 0;
        try {
            String sql = "select hoadon_id from hoadon ORDER BY hoadon_id DESC LIMIT 1";
            MySQLDataHelper helper = new MySQLDataHelper();
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                ma = rs.getInt("hoadon_id");
            }
            helper.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ma;
    }
    
    @Override
    public int getMaChiTietHoaDonCuoi() {
        int ma = 0;
        try {
            String sql = "select chitiethoadon_id from chitiethoadon ORDER BY chitiethoadon_id DESC LIMIT 1";
            MySQLDataHelper helper = new MySQLDataHelper();
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                ma = rs.getInt("chitiethoadon_id");
            }
            helper.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ma;
    }
    
    @Override
    public boolean addDonHang(int id, int makh, java.util.Date ngayLap, int tongTien, int giam_khtt) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(ngayLap);
        String sql = "INSERT INTO hoadon(hoadon_id,makh, ngaylap, tongtien, giam_khtt) VALUES (" + "'"
                + id + "'" + ", " + "'"
                + makh + "'" + ", " + "'"
                + currentTime + "'" + ", " + "'"
                + tongTien + "'" + ", " + "'"
                + giam_khtt + "')";
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
    public boolean addChiTietHoaDon(int maChiTietHoaDonCuoi, int hoaDonId, int masp, int soLg, int giaSp, int tongTienBanDau, int chietKhau, int khuyenMaiKhac, int tongTienSauCung) {
        String sql = "INSERT INTO chitiethoadon(chitiethoadon_id ,ma_hoadon, masp, soluong, giagoc, giasaukhuyenmai, tongtien, chietkhau, khuyenmaikhac) VALUES (" + "'"
                + maChiTietHoaDonCuoi + "'" + ", " + "'"
                + hoaDonId + "'" + ", " + "'"
                + masp + "'" + ", " + "'"
                + soLg + "'" + ", " + "'"
                + giaSp + "'" + ", " + "'"
                + tongTienSauCung + "'" + ", " + "'"
                + tongTienBanDau + "'" + ", " + "'"
                + chietKhau + "'" + ", " + "'"
                + khuyenMaiKhac + "')";
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
