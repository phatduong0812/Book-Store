/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatdg.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phatdg.account.AccountCreateError;
import phatdg.account.AccountDAO;
import phatdg.utils.MyAppConstants;

/**
 *
 * @author Phat
 */
public class AuthRegisterServlet extends HttpServlet {

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
        ServletContext context = request.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITE_MAP");
        String url = siteMap.getProperty(MyAppConstants.RegisterFeatures.REGISTER_PAGE);
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");
        boolean foundError = false;
        AccountCreateError errors = new AccountCreateError();
        try {
            if (username.trim().length() < 6 || username.trim().length() > 30) {
                foundError = true;
                errors.setUsernameLengthError("Username requires form 6 to 30 chars");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundError = true;
                errors.setPasswordLengthError("Password requires from 6 to 30 chars");
            } else if (!password.trim().equals(confirm.trim())) {
                foundError = true;
                errors.setConfirmNotMatchError("Confirm password not match");
            }
            if (fullName.trim().length() < 2 || fullName.trim().length() > 50) {
                foundError = true;
                errors.setFullnameLengthError("Full name requires form 2 to 50 char");
            }  
            if (foundError) {
                request.setAttribute("REGISTER_ERROR", errors);
                return;
            }
            AccountDAO dao = new AccountDAO();
            boolean result = dao.registerAccount(username, password, fullName, false);
            if (result) {
                url = siteMap.getProperty(MyAppConstants.RegisterFeatures.LOGIN_PAGE);
            }
        } catch( SQLException e) {
            String msg = e.getMessage();
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("REGISTER_ERROR", errors);
            }
            log("AccountDeleteServlet _ SQL _ " + e.getMessage());
        } catch( NamingException e) {
            log("AuthRegisterServlet _ Naming _ " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
