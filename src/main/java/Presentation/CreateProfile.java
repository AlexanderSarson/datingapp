/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Persistence.BasisConnectionPool;
import Persistence.ProfileMapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import logic.Profile;

/**
 *
 * @author Alex
 */
@WebServlet(name = "CreateProfile", urlPatterns = {"/CreateProfile"})
@MultipartConfig(
        location = "c:\\temp\\pictures",
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 2 * 5 * 1024 * 1024)
public class CreateProfile extends HttpServlet {

    private DataSource pool;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            // Create a JNDI Initial context to be able to lookup the DataSource
            InitialContext ctx = new InitialContext();
            // Lookup the DataSource, which will be backed by a pool
            //   that the application server provides.
            pool = (DataSource) ctx.lookup("java:comp/env/jdbc/mysql_datingapp");
            if (pool == null) {
                throw new ServletException("Unknown DataSource 'jdbc/mysql_datingapp'");
            }
        } catch (NamingException ex) {
            Logger.getLogger(CreateProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProfileMapper pm = new ProfileMapper(pool);
        int id = pm.getNextProfileId();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String date = request.getParameter("birthday");
        LocalDate birthday = LocalDate.parse(date);
//        String filename = firstName + lastName + ".jpg";
        Part part = request.getPart("picture");
//        part.write(filename);
        String fileName = getFileName(part);
        File uploads = new File("c:/temp/pictures");
        File file = new File(uploads, fileName);
        try ( InputStream input = part.getInputStream()) {
            Files.copy(input, file.toPath());
        }
        
        String path = "c:/temp/pictures/" + fileName;
        Profile profile = new Profile(id, firstName, lastName, birthday, path);
        pm.createProfile(profile);

    }
    
    // Gets the file name from the "content-disposition" header
   private String getFileName(Part part) {
    for (String token : part.getHeader("content-disposition").split(";")) {
      if (token.trim().startsWith("filename")) {
        String file =  token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
        String[] fileSplit = file.split("\\\\");
        return fileSplit[fileSplit.length - 1];
      }
    }
    return null;
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
