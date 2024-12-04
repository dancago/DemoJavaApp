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
import java.util.Base64;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.nightst0rm.connection.MysqlConnection;
import net.nightst0rm.entities.Logger;
import net.nightst0rm.entities.Token;
import net.nightst0rm.utils.DataUtils;
import net.nightst0rm.utils.EncryptionUtils;
import net.nightst0rm.utils.helper;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author buxuqua
 */
@WebServlet(name = "ImageServlet", urlPatterns = {"/ChangeImage.9st0rm"})
public class ImageServlet extends HttpServlet {

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
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String alert = "";
        Logger logger = new Logger();
        logger.setUser_id((Integer)request.getSession().getAttribute("user_id"));
        try {
            if (request.getSession().getAttribute("username") == null && request.getSession().getAttribute("user_id") == null) {
                response.sendRedirect("/login.jsp");
            }
            if (!StringUtils.isNotEmpty(request.getParameter("p")) || !StringUtils.isNotEmpty(request.getParameter("csrf_token"))) {
                alert = "Oops! image url isn't empty!";
                response.setContentType("text/plain");
                response.getWriter().write(alert);
                return;
            }
            String imageUrl = new String(Base64.getDecoder().decode(request.getParameter("p").trim()));
//            if(imageUrl.toLowerCase().contains("file://") || imageUrl.toLowerCase().contains("gopher://") || imageUrl.toLowerCase().contains("dict://") 
//                    || imageUrl.toLowerCase().contains("ldap://") || imageUrl.toLowerCase().contains("ftp://")){
//                alert = "Oops! only Http is allowed!  ";
//                response.setContentType("text/plain");
//                response.getWriter().write(alert);
//                return;
//            }
            if (!imageUrl.startsWith("http://") || !imageUrl.startsWith("https://")){
                alert = "Oops! only Http is allowed!  ";
                response.setContentType("text/plain");
                response.getWriter().write(alert);
                return;
            }
            String username = (String) request.getSession().getAttribute("username");
            String filename = "avatar" + ".jpg";
            String destinationFile = request.getServletContext().getRealPath("") + File.separator + "avatar" + File.separator + EncryptionUtils.encryptMD5(username);
//            String imagePath = saveImage(imageUrl, destinationFile, filename);
            helper.saveStream(imageUrl, destinationFile, filename);

            alert = "Avatar changes successfully!!! ";
            logger.setEvent("Avatar was changed!");
            logger.insert();
            response.setContentType("text/plain");
            response.getWriter().write(alert);

        } catch (Exception e) {
            alert = "Oops! Something wrong!. The error message has been logged.";
            logger.setEvent(e.getMessage() + " | File: " + this.getClass().getCanonicalName() + "| Location: " + this.getClass().getProtectionDomain().getCodeSource().getLocation());
            logger.insert();
            response.setContentType("text/plain");
            response.getWriter().write(alert);
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
