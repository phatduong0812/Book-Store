/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatdg.product;

import java.io.Serializable;

/**
 *
 * @author Phat
 */
public class OrderDetail implements Serializable {

    private String idDetail;
    private String orderID;
    private int quantity;
    private String productID;
    private int price;

    public OrderDetail() {
    }

    public OrderDetail(String idDetail, String orderID, int quantity, String productID, int price) {
        this.idDetail = idDetail;
        this.orderID = orderID;
        this.quantity = quantity;
        this.productID = productID;
        this.price = price;
    }
 
    /**
     * @return the idDetail
     */
    public String getIdDetail() {
        return idDetail;
    }

    /**
     * @param idDetail the idDetail to set
     */
    public void setIdDetail(String idDetail) {
        this.idDetail = idDetail;
    }

    /**
     * @return the orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the productID
     */
    public String getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }
    
    
}
