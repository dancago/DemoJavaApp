/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.nightst0rm.servet;

import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.nightst0rm.connection.MysqlConnection;
import net.nightst0rm.entities.Token;
import net.nightst0rm.utils.DataUtils;
import net.nightst0rm.utils.EncryptionUtils;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author buxuqua
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login.9st0rm"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            if (request.getParameter("username") == null || request.getParameter("password") == null || request.getParameter("username").trim().length() == 0 || request.getParameter("password").trim().length() == 0 ) {
                response.sendRedirect("login.jsp");
            }
            String username = request.getParameter("username").trim();
            String password = request.getParameter("password").trim();
            if (MysqlConnection.open()) {
                String query = "SELECT * FROM 9st0rm_user WHERE username = ? AND password = ? ";
                pstmt = (PreparedStatement) MysqlConnection.cnn.prepareStatement(query);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    String role = rs.getString("role");
                    Integer user_id = rs.getInt("id");
                    HttpSession session = request.getSession();
//                    session.invalidate();
//                    session = request.getSession(true);
                    session.setAttribute("username", username);
                    session.setAttribute("user_id", user_id);
                    session.setAttribute("role", role);
                    String schema = request.getScheme();
                    String hostname = request.getServerName();
                    Integer port = request.getServerPort();
                    String avatarPath = "/" + "avatar" +"/" + EncryptionUtils.encryptMD5(username) + "/" + "avatar" + ".jpg";
                    String url = schema+"://"+hostname+":"+port.toString()+  avatarPath;
                    session.setAttribute("avatarPath", url);
                    
                    //get avatar
//                    query = "SELECT avatar_path FROM 9st0rm_avatar WHERE user_id = ?";
//                    pstmt.setInt(1, user_id);
//                    rs = pstmt.executeQuery();
//                    if (rs.next()) {
//                        String avatar_path = rs.getString("avatar_path");
//                        if(avatar_path != null && avatar_path != ""){
//                            session.setAttribute("avatar_path", avatar_path);
//                        }
//                    }
                    Token t = new Token();
                    String random_text = RandomStringUtils.random(10, true, true);
                    session.setAttribute("random_text", random_text);
                    t.setUser_id(user_id);
                    t.setToken_value(random_text);
                    //token
                    String insert_query = "Insert into 9st0rm_token (id, user_id, token_value, username) values (default,?, ?, ?)";
                    pstmt = (PreparedStatement) MysqlConnection.cnn.prepareStatement(insert_query);
                    pstmt.setInt(1, user_id);
                    pstmt.setString(2, random_text);
                    pstmt.setString(3, username);
                    pstmt.execute();
                    String insert_query2 = String.format("Insert into 9st0rm_logger (id, user_id, event) values (default,%d, '%s')",user_id,"User Login: " + username.replace("'", ""));
                    pstmt = (PreparedStatement) MysqlConnection.cnn.prepareStatement(insert_query2);
                    pstmt.execute();
                    String csrf_token = DataUtils.createObject(t);
                    session.setAttribute("csrf_token", csrf_token);
                    response.sendRedirect("/home.jsp");
                }else{
                    response.sendRedirect("/login.jsp");
                }
            }

        } catch (Exception e) {
            response.sendRedirect("/home.jsp");
        } finally {
            MysqlConnection.close(pstmt, rs);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
