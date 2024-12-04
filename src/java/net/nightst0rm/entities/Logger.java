/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.nightst0rm.entities;

import com.mysql.jdbc.PreparedStatement;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;
import net.nightst0rm.connection.MysqlConnection;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author qubu
 */
public class Logger implements Serializable {
    private Integer id;
    private Integer user_id;
    private String event;

    
    public void insert() {
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            String insert_query = String.format("Insert into 9st0rm_logger (id, user_id, event) values (default,%d, '%s')",user_id,event);
            if (MysqlConnection.open()) {
                    pstmt = (PreparedStatement) MysqlConnection.cnn.prepareStatement(insert_query);
                    pstmt.execute();
            }
        } catch (Exception e) {
        } finally {
            MysqlConnection.close(pstmt, rs);
        }
    }
    
    public ArrayList<Logger> getList(){
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        ArrayList<Logger> arrayList = new ArrayList<>();
        try {
            String select_query = String.format("select * from 9st0rm_logger where user_id = %d order by id desc limit 8", user_id);
            if (MysqlConnection.open()) {
                    pstmt = (PreparedStatement) MysqlConnection.cnn.prepareStatement(select_query);
                    rs = pstmt.executeQuery();
                    while (rs.next()){
                        Logger logger = new Logger();
                        logger.setId(rs.getInt("id"));
                        logger.setUser_id(rs.getInt("user_id"));
                        logger.setEvent(rs.getString("event"));
                        arrayList.add(logger);
                    }
            }

        } catch (Exception e) {
        } finally {
            MysqlConnection.close(pstmt, rs);
        }
        return arrayList;
    }
    
   

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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }



    
    
}
