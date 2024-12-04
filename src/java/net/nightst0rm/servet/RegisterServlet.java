/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.nightst0rm.servet;

import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.nightst0rm.connection.MysqlConnection;
import net.nightst0rm.utils.EncryptionUtils;
import net.nightst0rm.utils.FileUtils;
import static net.nightst0rm.utils.helper.saveImage;

/**
 *
 * @author buxuqua
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/Register.9st0rm"})
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            if (request.getParameter("username") == null || request.getParameter("password") == null || request.getParameter("username").trim().equals("") || request.getParameter("password").trim().equals("")) {
                response.sendRedirect("login.jsp");
            }
            String username = request.getParameter("username").trim();
            String password = request.getParameter("password").trim();
            if (MysqlConnection.open()) {
//                String query = "SELECT * FROM 9st0rm_user WHERE username = ? ";
                StringBuilder query = new StringBuilder();
                query.append("SELECT * FROM 9st0rm_user WHERE username = ");
                query.append(username);
                pstmt = (PreparedStatement) MysqlConnection.cnn.prepareStatement(query.toString());
//                pstmt.setString(1, username);
                rs = pstmt.executeQuery();
                if (rs.next() == false) {
                    String insert_query = "Insert into 9st0rm_user (id, username, password, role) values (default,?, ?, ?)";
                    pstmt = (PreparedStatement) MysqlConnection.cnn.prepareStatement(insert_query);
                    pstmt.setString(1, username.trim());
                    pstmt.setString(2, password.trim());
                    pstmt.setInt(3, 2);
                    boolean result = pstmt.execute();
                    //avatar
                    String filename = "avatar" + ".jpg";
                    String destinationFolder = request.getServletContext().getRealPath("") + "avatar" + File.separator + EncryptionUtils.encryptMD5(username);
                    String desFilePath = destinationFolder + File.separator + filename;
                    String sourceFilePath = request.getServletContext().getRealPath("") + "avatar" + File.separator + EncryptionUtils.encryptMD5("admin")+ File.separator + "avatar" + ".jpg";
                    File dir = new File(destinationFolder);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File source = new File(sourceFilePath);
                    File des = new File(desFilePath);
                    FileUtils.copyFileUsingFileStreams(source, des);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp?infor=Success!!!");
                    rd.forward(request, response);

                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp?infor=Username%20already%20exists!!!");
                    rd.forward(request, response);
                }
            } 

        } catch (Exception e) {
            e.printStackTrace();
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp?infor=Error!!!");
//            rd.forward(request, response);
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
