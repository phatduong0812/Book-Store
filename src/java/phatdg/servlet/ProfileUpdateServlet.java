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
import javax.servlet.http.HttpSession;
import phatdg.account.AccountCreateError;
import phatdg.account.AccountDAO;
import phatdg.account.AccountDTO;
import phatdg.utils.MyAppConstants;

/**
 *
 * @author Phat
 */
public class ProfileUpdateServlet extends HttpServlet {

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String fullName = request.getParameter("txtFullName");
        String name = request.getParameter("txtName");
        String id = request.getParameter("txtID");
        String checkAdmin = request.getParameter("checkAdmin");
        boolean role = false;
        AccountCreateError errors = new AccountCreateError();
        boolean foundError = false;
        String url = siteMap.getProperty(MyAppConstants.UpdateProfileFeatures.PROFILE_PAGE);
        try {
            if (checkAdmin != null) {
                role = true;
            }
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
                AccountDAO dao = new AccountDAO();
                boolean result = dao.updateAccount(username, password, false, fullName);
                if (result) {
                    AccountDTO afterUpdate = dao.getAccountByUsernameAndPassword(username, password);
                    HttpSession session = request.getSession(false);
                    session.setAttribute("USER", afterUpdate);
                } else {
                    url = siteMap.getProperty(MyAppConstants.UpdateAccountFeatures.ERROR_PAGE);
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
                AccountDAO dao = new AccountDAO();
                boolean result = dao.updateAccount(id, name, role);
                if (result) {
                    AccountDTO afterUpdate = dao.getAccountByEmail(id);
                    HttpSession session = request.getSession(false);
                    session.setAttribute("USER_GOOGLE", afterUpdate);
                } else {
                    url = siteMap.getProperty(MyAppConstants.UpdateAccountFeatures.ERROR_PAGE);
                }
            }
        } catch (SQLException e) {
            log("ProfileUpdateServlet _ SQL _ " + e.getMessage());
        } catch (NamingException e) {
            log("ProfileUpdateServlet _ Naming _" + e.getMessage());
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
