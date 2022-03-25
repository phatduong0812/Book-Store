/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatdg.product;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Phat
 */
public class CartObject implements Serializable {

    private Map<String, ProductDTO> items;
    private int totalPrice = 0;

    public Map<String, ProductDTO> getItems() {
        return items;
    }
    
    public Set<String> getKeys() {
        return this.items.keySet();
    }

    public int getTotalPrice(){
        return this.totalPrice;
    }

    public CartObject() {

    }

    public void addItemsToCart(String id, ProductDTO productDTO) {
        //1.Check items has existed
        if (productDTO == null) {
            return;
        }

        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //2. Check item exist in items
        int quantity = productDTO.getQuantity();
        if (this.items.containsKey(id)) {
            int tmp = this.items.get(id).getQuantity();
            productDTO.setQuantity(quantity + tmp);
        }
        this.items.put(id, productDTO);
        this.totalPrice = this.totalPrice + (productDTO.getPrice() * quantity);
    }

    public void removeItem(String id) {
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(id)) {
            this.totalPrice = this.totalPrice - (this.items.get(id).getPrice()*this.items.get(id).getQuantity());
            this.items.remove(id);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

}
