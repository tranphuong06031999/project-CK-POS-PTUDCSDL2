/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.impl;

import entity.QuyCachEntity;
import helper.MySQLDataHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import repository.IQuyCachRepository;

/**
 *
 * @author NguyenThanhDat
 */
@Repository
public class QuyCachRepository implements IQuyCachRepository {

    @Override
    public ArrayList<QuyCachEntity> findAllByMaSP(int masp) {
        ArrayList<QuyCachEntity> list = new ArrayList<>();
        String sql = "SELECT * from quycach where masp = " + masp;
        MySQLDataHelper helper = new MySQLDataHelper();
        try {
            helper.open();
            ResultSet rs = helper.excuteQuery(sql);
            if (rs.next() == false) {
                list = null;
            } else {
                do {
                    QuyCachEntity qc = new QuyCachEntity();
                    qc.setMasp(rs.getInt("masp"));
                    qc.setId(rs.getInt("id"));
                    qc.setQuycach(rs.getInt("quycach"));
                    qc.setGiagoc(rs.getInt("giagoc"));
                    list.add(qc);
                } while (rs.next());
            }
            helper.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean create(QuyCachEntity quycach) {
        String sql = "INSERT INTO quycach(masp, quycach, giagoc) VALUES (" + "'"
                + quycach.getMasp() + "'" + ", " + "'"
                + quycach.getQuycach() + "'" + ", " + "'"
                + quycach.getGiagoc() + "')";
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
    public boolean update(QuyCachEntity quycach) {
        String sql = "update quycach set quycach = " + quycach.getQuycach() + ", giagoc = " + quycach.getGiagoc() + " where id = " + quycach.getId();
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
        String sql = "delete from quycach where id = " + id;
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
