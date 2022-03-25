/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatdg.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phatdg.product.CartObject;
import phatdg.product.OrderDetail;
import phatdg.product.OrderObject;
import phatdg.product.ProductDAO;
import phatdg.product.ProductDTO;
import phatdg.utils.MyAppConstants;
import phatdg.utils.RandomString;

/**
 *
 * @author Phat
 */
public class CheckOutServlet extends HttpServlet {

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
        String url = siteMap.getProperty(MyAppConstants.CheckOutFeatures.CART_PAGE);
        try {
            HttpSession session = request.getSession(false);
            boolean check = true;
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart != null) {
                ProductDAO dao = new ProductDAO();
                for (String key : cart.getKeys()) {
                    ProductDTO dto = dao.getProduct(key);
                    if (cart.getItems().get(key).getQuantity() > dto.getQuantity()) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    String orderID = RandomString.randomString();
                    String name = request.getParameter("fullname");
                    String address = request.getParameter("address");
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
                    LocalDateTime now = LocalDateTime.now();
                    String date = dtf.format(now);
                    int total = cart.getTotalPrice();
                    OrderObject orderObject = new OrderObject(orderID, date, total, name, address);
                    boolean flag = dao.createOrder(orderObject);
                    if (flag) {
                        for (String key : cart.getKeys()) {
                            String detailID = RandomString.randomString();
                            String productID = cart.getItems().get(key).getId();
                            int price = cart.getItems().get(key).getPrice();
                            int quantity = cart.getItems().get(key).getQuantity();
                            int updateQuantity = dao.getProduct(key).getQuantity() - cart.getItems().get(key).getQuantity();
                            dao.updateQuantity(updateQuantity, key);
                            OrderDetail detail = new OrderDetail(detailID, orderID, quantity, productID, price);
                            boolean flag2 = dao.createOrderDetail(detail);
                        }
                    }
                    session.removeAttribute("CART");
                    request.setAttribute("MESSAGE", "Thank you");
                    url = siteMap.getProperty(MyAppConstants.CheckOutFeatures.SUCCESS_CHECKOUT);
                } else {
                    request.setAttribute("MESSAGE_ERROR", "Your quantity is out of range our quantity! please select again!");
                }
            }
        } catch (SQLException e) {
            log("CheckOutServlet _ SQL _ " + e.getMessage());
        } catch (NamingException e) {
            log("CheckOutServlet _ Naming _" + e.getMessage());
        } finally {

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
