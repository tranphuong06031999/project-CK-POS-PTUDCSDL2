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
        List<LichBanHangEnity> list = new ArrayList<LichBanHangEnity>();

        String sql = "SELECT khachhang.makh, hoadon.hoadon_id, khachhang.tenkh, sanpham.tensp, chitiethoadon.soluong, SUM(chitiethoadon.tongtien) AS tongtien, hoadon.ngaylap\n"
                + "FROM hoadon, chitiethoadon, khachhang, sanpham\n"
                + "WHERE hoadon.hoadon_id=chitiethoadon.ma_hoadon\n"
                + "AND hoadon.makh=khachhang.makh\n"
                + "AND chitiethoadon.masp = sanpham.masp\n"
                + "GROUP BY (khachhang.makh)\n"
                + "ORDER BY hoadon.ngaylap DESC\n"
                + "LIMIT "+page+",10";
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
                    lbh.setTensp(rs.getString("tensp"));
                    lbh.setSoluong(rs.getInt("soluong"));
                    lbh.setTongtien(rs.getInt("tongtien"));
                    lbh.setNgaylap(rs.getString("ngaylap"));
                    list.add(lbh);
                } while (rs.next());
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
