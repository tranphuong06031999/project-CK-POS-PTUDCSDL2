/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.impl;

import entity.PhieuThuEntity;
import helper.MySQLDataHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import repository.IPhieuThuRepository;

/**
 *
 * @author NguyenThanhDat
 */
@Repository
public class PhieuThuRepository implements IPhieuThuRepository {

    @Override
    public boolean create(PhieuThuEntity pt) {
        String sql = String.format("INSERT INTO `phieuthu` (`makh`, `tenkh`,`sotiennap`,`sodu`) VALUES('%d','%s','%d','%d')",
                pt.getMakh(), pt.getTenkh(), pt.getSotiennap(), pt.getSodu());
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
    public ArrayList<PhieuThuEntity> findAll() {
        ArrayList<PhieuThuEntity> list = new ArrayList<>();
        String sql = "SELECT * from phieuthu";
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                PhieuThuEntity pt = new PhieuThuEntity();
                pt.setMaphieuthu(rs.getInt("maphieuthu"));
                pt.setMakh(rs.getInt("makh"));
                pt.setTenkh(rs.getString("tenkh"));
                pt.setSotiennap(rs.getInt("sotiennap"));
                pt.setSodu(rs.getInt("sodu"));
                pt.setNgaylap(rs.getDate("ngaylap").toString());
                list.add(pt);
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public ArrayList<PhieuThuEntity> search(String keyword) {
        ArrayList<PhieuThuEntity> list = new ArrayList<>();
        String sql = "SELECT * from phieuthu where makh like '%" + keyword + "%' or tenkh like '%" + keyword + "%'";
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                list = null;
            } else {
                do {
                    PhieuThuEntity pt = new PhieuThuEntity();
                    pt.setMaphieuthu(rs.getInt("maphieuthu"));
                    pt.setMakh(rs.getInt("makh"));
                    pt.setTenkh(rs.getString("tenkh"));
                    pt.setSotiennap(rs.getInt("sotiennap"));
                    pt.setSodu(rs.getInt("sodu"));
                    pt.setNgaylap(rs.getDate("ngaylap").toString());
                    list.add(pt);
                } while (rs.next());
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public PhieuThuEntity findOne(int id) {
        PhieuThuEntity pt = new PhieuThuEntity();
        String sql = "SELECT * from phieuthu where maphieuthu = " + id;
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                pt = null;
            } else {
                do {
                    pt.setMaphieuthu(rs.getInt("maphieuthu"));
                    pt.setMakh(rs.getInt("makh"));
                    pt.setTenkh(rs.getString("tenkh"));
                    pt.setSotiennap(rs.getInt("sotiennap"));
                    pt.setSodu(rs.getInt("sodu"));
                    pt.setNgaylap(rs.getDate("ngaylap").toString());
                } while (rs.next());
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pt;
    }

    @Override
    public int count() {
        int count = 0;
        try {
            String sql = "select count(*) count from phieuthu";
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
    public ArrayList<PhieuThuEntity> paging(int page) {
        page = (page - 1) * 10;
        ArrayList<PhieuThuEntity> list = new ArrayList<>();
        String sql = "SELECT * from phieuthu limit " + page + ",10";
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            while (rs.next()) {
                PhieuThuEntity pt = new PhieuThuEntity();
                pt.setMaphieuthu(rs.getInt("maphieuthu"));
                pt.setMakh(rs.getInt("makh"));
                pt.setTenkh(rs.getString("tenkh"));
                pt.setSotiennap(rs.getInt("sotiennap"));
                pt.setSodu(rs.getInt("sodu"));
                pt.setNgaylap(rs.getDate("ngaylap").toString());
                list.add(pt);
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
