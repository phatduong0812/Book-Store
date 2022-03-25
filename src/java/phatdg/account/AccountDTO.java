/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatdg.account;

import java.io.Serializable;

/**
 *
 * @author natton
 */
public class AccountDTO implements Serializable{
    private String username;
    private String password;
    private String fullname;
    private boolean role;
    private String id;
    private String name;
    private String email;

    public AccountDTO() {
    }

    public AccountDTO(String username, String password, String fullname, boolean role) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    public AccountDTO(String username, boolean role, String id, String name, String email) {
        this.username = username;
        this.role = role;
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public boolean isRole() {
        return role;
    }

    @Override
    public String toString() {
        return "AccountDTO{" + "username=" + username + ", password=" + password + ", fullname=" + fullname + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
       
}
