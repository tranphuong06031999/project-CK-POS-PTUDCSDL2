/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author NguyenThanhDat
 */
public class MySQLDataHelper {

    private Connection connection;

    public void open() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties connectionProps = new Properties();
            connectionProps.put("user", "root");
            connectionProps.put("password", "");
            connectionProps.put("characterEncoding", "utf-8");
            String ConnectionString = "jdbc:mysql://localhost:3306/quanlysanpham";
            this.connection = DriverManager.getConnection(ConnectionString, connectionProps);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet excuteQuery(String sql) {
        ResultSet rs = null;
        try {
            Statement st = this.connection.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int excuteUpdate(String sql) {
        int n = -1;
        try {
            Statement st = this.connection.createStatement();
            n = st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
}
