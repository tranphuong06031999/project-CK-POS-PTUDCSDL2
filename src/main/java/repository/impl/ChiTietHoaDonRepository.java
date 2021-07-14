/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.impl;

import entity.ChiTietHoaDonEntity;
import helper.MySQLDataHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import repository.IChiTietHoaDonRepository;

/**
 *
 * @author NguyenThanhDat
 */

@Repository
public class ChiTietHoaDonRepository implements IChiTietHoaDonRepository {

    @Override
    public ArrayList<ChiTietHoaDonEntity> findByMaHD(int mahd) {
        ArrayList<ChiTietHoaDonEntity> list = new ArrayList<>();
        String sql = "select * from chitiethoadon where ma_hoadon = " + mahd;
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                ChiTietHoaDonEntity cthd = new ChiTietHoaDonEntity();
                cthd.setChitiethoadon_id(rs.getInt("chitiethoadon_id"));
                cthd.setMa_hoadon(rs.getInt("ma_hoadon"));
                cthd.setMasp(rs.getInt("masp"));
                cthd.setSoluong(rs.getInt("soluong"));
                cthd.setGiagoc(rs.getInt("giagoc"));
                cthd.setGiasaukhuyenmai(rs.getInt("giasaukhuyenmai"));
                cthd.setTongtien(rs.getInt("tongtien"));
                cthd.setChietkhau(rs.getFloat("chietkhau"));
                cthd.setKhuyenmaikhac(rs.getFloat("khuyenmaikhac"));
                list.add(cthd);
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
