/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.impl;

import entity.LichBanHangEnity;
import helper.MySQLDataHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;
import repository.ILichBanHangRepository;

/**
 *
 * @author THAIHUYNH
 */
@Repository
public class LichBanHangRepository implements ILichBanHangRepository {

    @Override
    public List<LichBanHangEnity> paging(int page) {
        page = (page - 1) * 10;
        List<LichBanHangEnity> list = new ArrayList<>();

        String sql = "SELECT khachhang.makh, hoadon.hoadon_id, khachhang.tenkh, SUM(hoadon.tongtien) AS tongtien, hoadon.ngaylap\n"
                + "FROM hoadon, khachhang\n"
                + "WHERE hoadon.makh = khachhang.makh\n"
                + "GROUP BY khachhang.makh, date(hoadon.ngaylap)\n"
                + "ORDER BY hoadon.ngaylap DESC\n"
                + "LIMIT " + page + ",10";
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                list = null;
            } else {
                do {
                    LichBanHangEnity lbh = new LichBanHangEnity();
                    lbh.setMakh(rs.getInt("makh"));
                    lbh.setHoadon_id(rs.getInt("hoadon_id"));
                    lbh.setTenkh(rs.getString("tenkh"));
                    lbh.setTongtien(rs.getInt("tongtien"));
                    lbh.setNgaylap(rs.getString("ngaylap"));
                    list.add(lbh);
                } while (rs.next());
            }
            helper.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public int count() {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*)\n"
                    + "FROM hoadon\n"
                    + "GROUP BY hoadon.makh, date(hoadon.ngaylap)";
            MySQLDataHelper helper = new MySQLDataHelper();
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                count++;
            }
            helper.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public List<LichBanHangEnity> pagingSearch(int page, int makh) {
        page = (page - 1) * 10;
        List<LichBanHangEnity> list = new ArrayList<>();

        String sql = "SELECT khachhang.makh, hoadon.hoadon_id, khachhang.tenkh, SUM(hoadon.tongtien) AS tongtien, hoadon.ngaylap\n"
                + "FROM hoadon join khachhang on hoadon.makh = khachhang.makh\n"
                + "WHERE hoadon.makh like '%" + makh + "%' \n"
                + "GROUP BY khachhang.makh, date(hoadon.ngaylap)\n"
                + "ORDER BY hoadon.ngaylap DESC\n"
                + "LIMIT " + page + ",10";
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                list = null;
            } else {
                do {
                    LichBanHangEnity lbh = new LichBanHangEnity();
                    lbh.setMakh(rs.getInt("makh"));
                    lbh.setHoadon_id(rs.getInt("hoadon_id"));
                    lbh.setTenkh(rs.getString("tenkh"));
                    lbh.setTongtien(rs.getInt("tongtien"));
                    lbh.setNgaylap(rs.getString("ngaylap"));
                    list.add(lbh);
                } while (rs.next());
            }
            helper.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public int countSearch(int makh) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*)\n"
                    + "FROM hoadon\n"
                    + "WHERE makh like '%" + makh + "%'\n"
                    + "GROUP BY hoadon.makh, date(hoadon.ngaylap)";
            MySQLDataHelper helper = new MySQLDataHelper();
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                count++;
            }
            helper.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

}
