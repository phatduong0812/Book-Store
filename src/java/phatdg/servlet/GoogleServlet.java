/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatdg.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phatdg.account.AccountDAO;
import phatdg.account.AccountDTO;
import phatdg.utils.MyAppConstants;
import phatdg.utils.NetUtils;

/**
 *
 * @author Phat
 */
public class GoogleServlet extends HttpServlet {

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
        String url = MyAppConstants.GoogleServlet.LOGIN_PAGE;
        try {
            String code = request.getParameter("code");
            if (code == null || code.isEmpty()) {
            } else {
                HttpSession session = request.getSession();
                String accessToken = NetUtils.getToken(code);
                AccountDTO accountDTO = NetUtils.getUserInfo(accessToken);
                AccountDAO accountDAO = new AccountDAO();
                String email = accountDTO.getEmail();
                AccountDTO validUser = accountDAO.getAccountByEmail(email);
                if (validUser == null) {
                    String name = accountDTO.getName();
                    String id = accountDTO.getId();
                    String username = accountDTO.getEmail();
                    accountDAO.registerAccount(username, id, email, name, false);
                    accountDTO.setUsername(email);
                    accountDTO.setRole(false);
                    session.setAttribute("USER_GOOGLE", accountDTO);
                    session.setAttribute("ROLE", accountDTO.isRole());
                } else {
                    session.setAttribute("USER_GOOGLE", validUser);
                    session.setAttribute("ROLE", validUser.isRole());
                }
                url = MyAppConstants.GoogleServlet.PROFILE_PAGE;
            }
        } catch (SQLException e) {
            
        } catch (NamingException e) {
            
        } finally {
            response.sendRedirect(url);
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
