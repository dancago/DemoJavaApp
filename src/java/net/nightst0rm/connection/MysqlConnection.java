/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.nightst0rm.connection;

import com.mysql.jdbc.PreparedStatement;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author buxuqua
 */
public class MysqlConnection {

    public static String driver = "com.mysql.jdbc.Driver";
    public static String url = "jdbc:mysql://localhost/ctf_web3?autoReconnect=true&useSSL=false";
//    public static String user = "root";
//    public static String pass = "";
//    public static String user = "ctf_web03_user";
//    public static String pass = "p4ssw0rd_t0o_h4rD";
    public static String user = "";
    public static String pass = "";
    public static Connection cnn;

    public static boolean open() {
        try {
            //ket noi db o day
            Config cfg = new Config();
            url = cfg.getProperty("url");
            user = cfg.getProperty("user");
            pass = cfg.getProperty("pass");

            if (cnn == null || cnn.isClosed()) {
                Class.forName(driver);
                cnn = DriverManager.getConnection(url, user, pass);
                return true;
            }

        } catch (SQLException ex) {
            //xu ly ex
        } catch (ClassNotFoundException ex) {
            //xu ly ex

        }

        return false;

    }

    public static void close() {
        try {
            if (cnn != null && !cnn.isClosed()) {

                cnn.close();

            }
        } catch (SQLException ex) {
            //xu ly ex
        }
    }

    public static void close(PreparedStatement ps) {
        try {

            if (ps != null && !ps.isClosed()) {

                ps.close();

            }
        } catch (SQLException ex) {
            //xu ly ex
        }
        close();
    }

    public static void close(PreparedStatement ps, ResultSet rs) {
        try {

            if (rs != null && !rs.isClosed()) {

                rs.close();

            }
        } catch (SQLException ex) {
            //xu ly ex
        }

        close(ps);
    }
}
