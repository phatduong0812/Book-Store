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
public class AccountUpdateServlet extends HttpServlet {

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
        Properties properties = (Properties) context.getAttribute("SITE_MAP");
        //get request parameter
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String fullName = request.getParameter("txtFullName");
        String searchValue = request.getParameter("lastSearchValue");
        String valueCheckAdmin = request.getParameter("checkAdmin");
        String id = request.getParameter("txtID");
        String name = request.getParameter("txtName");
        boolean role = false;
        AccountCreateError errors = new AccountCreateError();
        boolean foundError = false;
        String url = properties.getProperty(MyAppConstants.UpdateAccountFeatures.SEARCH_ACCTION) + "?txtSearch=" + searchValue;
        try {
            if (searchValue != null) {
                if (username != null) {
                    if (password.trim().length() < 6 || password.trim().length() > 30) {
                        foundError = true;
                        errors.setPasswordLengthError("Password requires from 6 to 30 chars");
                    }
                    if (fullName.trim().length() < 2 || fullName.trim().length() > 50) {
                        foundError = true;
                        errors.setFullnameLengthError("Full name requires form 2 to 50 char");
                    }
                    if (foundError) {
                        request.setAttribute("UPDATE_ERROR", errors);
                        request.setAttribute("UPDATE_ERROR_PK", username);
                        return;
                    }
                    if (valueCheckAdmin != null) {
                        role = true;
                    }
                    AccountDAO dao = new AccountDAO();
                    boolean result = dao.updateAccount(username, password, role, fullName);
                    if (!result) {
                        url = properties.getProperty(MyAppConstants.UpdateAccountFeatures.ERROR_PAGE);
                    }
                } else if (id != null) {
                    if (name.trim().length() < 2 || name.trim().length() > 50) {
                        foundError = true;
                        errors.setFullnameLengthError("Full name requires form 2 to 50 char");
                    }
                    if (foundError) {
                        request.setAttribute("UPDATE_ERROR", errors);
                        request.setAttribute("UPDATE_ERROR_PK", id);
                        return;
                    }
                    if (valueCheckAdmin != null) {
                        role = true;
                    }
                    AccountDAO dao = new AccountDAO();
                    boolean result = dao.updateAccount(id, name, role);
                    if (!result) {
                        url = properties.getProperty(MyAppConstants.UpdateAccountFeatures.ERROR_PAGE);
                    }
                }
            }
        } catch (SQLException e) {
            log("AccountUpdateServlet _ SQL _ " + e.getMessage());
        } catch (NamingException e) {
            log("AccountUpdateServlet _ Naming _ " + e.getMessage());
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
