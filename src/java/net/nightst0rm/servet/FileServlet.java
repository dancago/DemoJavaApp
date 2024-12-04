/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.nightst0rm.servet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.nightst0rm.entities.Logger;
import net.nightst0rm.utils.EncryptionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author buxuqua
 */
@WebServlet(name = "FileServlet", urlPatterns = {"/Upload.9st0rm"})
public class FileServlet extends HttpServlet {

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
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        String alert = "";
        Logger logger = new Logger();
        logger.setUser_id((Integer)request.getSession().getAttribute("user_id"));
        if (request.getSession().getAttribute("username") == null || request.getSession().getAttribute("username").equals("")) {
            alert = "Login Pls!!! ";
            response.setContentType("text/plain");
            response.getWriter().write(alert);
        }
        String username = (String) request.getSession().getAttribute("username");
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> multiparts = upload.parseRequest(request);
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String filename = item.getName();
//                        if(!filename.endsWith(".jpg")){
//                            alert = "Only JPG extension Pls!!! ";
//                            response.setContentType("text/plain");
//                            response.getWriter().write(alert);
//                            return;
//                        }
                        String destinationFile = request.getServletContext().getRealPath("") + File.separator + "avatar" + File.separator + EncryptionUtils.encryptMD5(username);
                        File dir = new File(destinationFile);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        File avatarFile = new File(destinationFile + File.separator + "avatar.jpg");
                        item.write(avatarFile);
                    }
                }
                alert = "Avatar changes successfully!!!!!! ";
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
            }
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
