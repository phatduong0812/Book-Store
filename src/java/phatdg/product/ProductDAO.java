/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatdg.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phatdg.utils.DBHelper;

/**
 *
 * @author Phat
 */
public class ProductDAO implements Serializable {

    public List<ProductDTO> loadProduct()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Select name, price, id, quantity "
                        + "From Product";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    String id = rs.getString("id");
                    int quantity = rs.getInt("quantity");
                    ProductDTO dto = new ProductDTO(id, name, price, quantity);
                    listProduct.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listProduct;
    }

    public ProductDTO getProduct(String id)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO productDTO = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Select name, price, id, quantity "
                        + "From Product "
                        + "Where id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    productDTO = new ProductDTO(id, name, price, quantity);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return productDTO;
    }

    public boolean createOrder(OrderObject orderObject)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ProductDTO productDTO = null;
        boolean effectRows = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Insert into OrderProduct(orderId, name, address, total, date) "
                        + "Values(?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderObject.getOrderID());
                stm.setString(2, orderObject.getName());
                stm.setString(3, orderObject.getAddress());
                stm.setInt(4, orderObject.getTotal());
                stm.setString(5, orderObject.getDate());
                effectRows = stm.executeUpdate() > 0 ? true : false;
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return effectRows;
    }

    public boolean createOrderDetail(OrderDetail orderDetail)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ProductDTO productDTO = null;
        boolean effectRows = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Insert into OrderDetail(idDetail, orderDetailID, quantity, price, productID) "
                        + "Values(?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderDetail.getIdDetail());
                stm.setString(2, orderDetail.getOrderID());
                stm.setInt(3, orderDetail.getQuantity());
                stm.setInt(4, orderDetail.getPrice());
                stm.setString(5, orderDetail.getProductID());
                effectRows = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return effectRows;
    }

    public void updateQuantity(int quantity, String id)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ProductDTO productDTO = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Update Product "
                        + "Set quantity = ? "
                        + "Where id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setString(2, id);
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }
}
