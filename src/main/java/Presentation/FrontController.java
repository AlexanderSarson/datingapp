/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Persistence.ProfileMapper;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import logic.Profile;
import logic.ProfileController;

/**
 *
 * @author Alex
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

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
        String cmd = request.getParameter("cmd");
        RequestDispatcher rd;
        
        switch (cmd) {
            case "createProfile":
                rd = request.getRequestDispatcher("createProfile.html");
                break;
            case "ShowProfiles":
                ProfileMapper pm = makeProfileMapper();
                ProfileController pc = pm.getAllProfiles();
                List<Profile> profiles = pc.getProfiles();
                request.setAttribute("profiles", profiles);
                rd = request.getRequestDispatcher("ShowProfiles.jsp");
                break;
            default:
                rd = request.getRequestDispatcher("index.html");
                break;
        }
        rd.forward(request, response);
    }
    
    public ProfileMapper makeProfileMapper(){
        return new ProfileMapper(pool);
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
