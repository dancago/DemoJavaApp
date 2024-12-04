/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.nightst0rm.entities;

import com.mysql.jdbc.PreparedStatement;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import net.nightst0rm.connection.MysqlConnection;
import net.nightst0rm.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author qubu
 */
public class Token implements Serializable {

    private Integer id;
    private Integer user_id;
    private String token_value;
    private String username;
    private Logger logger;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getToken_value() {
        return token_value;
    }

    public void setToken_value(String token_value) {
        this.token_value = token_value;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public void insert() {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            String insert_query = String.format("Insert into 9st0rm_token (id, user_id, token_value, username) values (default,%d, '%s', '%s')", user_id, token_value, username);
            if (MysqlConnection.open()) {
                pstmt = (PreparedStatement) MysqlConnection.cnn.prepareStatement(insert_query);
                pstmt.execute();
                if (logger != null) {
                    if (StringUtils.isNotEmpty(logger.getEvent())) {
                        String insertLog_query = String.format("Insert into 9st0rm_logger (id, user_id, event) values (default,%d, '%s')", user_id, logger.getEvent());
                        pstmt = (PreparedStatement) MysqlConnection.cnn.prepareStatement(insertLog_query);
                        pstmt.execute();
                    }
                }
            }

        } catch (Exception e) {
        } finally {
            MysqlConnection.close(pstmt, rs);
        }
    }

    public ArrayList<Token> getList() {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        ArrayList<Token> arrayList = new ArrayList<>();
        try {
            String select_query = String.format("select * from 9st0rm_token where user_id = %d order by id desc limit 8", user_id);
            if (MysqlConnection.open()) {
                pstmt = (PreparedStatement) MysqlConnection.cnn.prepareStatement(select_query);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Token tk = new Token();
                    tk.setId(rs.getInt("id"));
                    tk.setUser_id(rs.getInt("user_id"));
                    tk.setToken_value(rs.getString("token_value"));
                    arrayList.add(tk);
                }
                if (logger != null) {
                    if (StringUtils.isNotEmpty(logger.getEvent())) {
                        if (SecurityUtils.SQL_filter(logger.getEvent())){
                            String insertLog_query = String.format("Insert into 9st0rm_logger (id, user_id, event) values (default,%d, '%s')", user_id, logger.getEvent());
                            pstmt = (PreparedStatement) MysqlConnection.cnn.prepareStatement(insertLog_query);
                            pstmt.execute();
                        }else{
                            String mess = "filtered pattern = ([\\%#]|\\+|\\&|\\-|\\/\\/|into|>|<|file|case|group|order|offset|limit|and|xor|not|null|union|where|if|ascii|char|ord|case|when|div|mod)</br> Get flag from 9st0rm_s3cr3t";
                            String insertLog_query = String.format("Insert into 9st0rm_logger (id, user_id, event) values (default,%d, '%s')", user_id, mess);
                            pstmt = (PreparedStatement) MysqlConnection.cnn.prepareStatement(insertLog_query);
                            pstmt.execute();
                        }
                    }
                }
            }

        } catch (Exception e) {

        } finally {
            MysqlConnection.close(pstmt, rs);
        }
        return arrayList;
    }

}
