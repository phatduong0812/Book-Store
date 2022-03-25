/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatdg.servlet;

import phatdg.account.AccountDAO;
import phatdg.account.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phatdg.utils.MyAppConstants;

/**
 *
 * @author natton
 */
public class AccountSearchServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ServletContext context = request.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITE_MAP");
        String url = (String) siteMap.get(MyAppConstants.SearchAccountFeatures.SEARCH_PAGE);
        try {
            String searchValue = request.getParameter("txtSearch");
            if (searchValue != null && searchValue.trim().length() > 0) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    AccountDTO accountDTO = (AccountDTO) session.getAttribute("USER");
                    AccountDAO dao = new AccountDAO();
                    if (accountDTO != null) {
                        List<AccountDTO> result
                                = dao.searchAccountByFullName(searchValue, accountDTO.getUsername());
                        request.setAttribute("SEARCH_RESULT", result);
                        List<AccountDTO> resultGG
                                = dao.searchAccountGoogleByFullName(searchValue, accountDTO.getUsername());
                        request.setAttribute("SEARCH_RESULT_GOOGLE", resultGG);
                    } else {
                        accountDTO = (AccountDTO) session.getAttribute("USER_GOOGLE");
                        List<AccountDTO> result
                                = dao.searchAccountByFullName(searchValue, accountDTO.getUsername());
                        request.setAttribute("SEARCH_RESULT", result);
                        List<AccountDTO> resultGG
                                = dao.searchAccountGoogleByFullName(searchValue, accountDTO.getUsername());
                        request.setAttribute("SEARCH_RESULT_GOOGLE", resultGG);
                    }
                }
            }
        } catch (SQLException e) {
            log("AccountSearchServlet _ SQL _ " + e.getMessage());
        } catch (NamingException e) {
            log("AccountSearchServlet _ Naming _ " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
