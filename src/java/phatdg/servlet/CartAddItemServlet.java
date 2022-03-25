/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatdg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phatdg.product.CartObject;
import phatdg.product.ProductDTO;
import phatdg.utils.MyAppConstants;

/**
 *
 * @author Phat
 */
public class CartAddItemServlet extends HttpServlet {

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
        
        String url = MyAppConstants.AddItemsFeatures.STORE_PAGE;
        String name = request.getParameter("txtName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int price = Integer.parseInt(request.getParameter("price"));
        String id = request.getParameter("txtID");
        try {
                //1. Customer goes to cart palce
                HttpSession session = request.getSession();
                //2. Customer takes cart --> attibutes
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart == null) {
                    cart = new CartObject();
                } //cart is not exist --> create
                //3. Take book item --> parameter
                ProductDTO dto = new ProductDTO(id, name, price, quantity);
                cart.addItemsToCart(id, dto);
                session.setAttribute("CART", cart);
                //4. Customer goes shopping
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
