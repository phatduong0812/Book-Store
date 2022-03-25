/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatdg.account;

import phatdg.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author natton
 */
public class AccountDAO {
    public AccountDTO getAccountByUsernameAndPassword(String username, String password) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        AccountDTO dto = null;
        try {
            //1. connect database
            con = DBHelper.getConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "SELECT fullname, role "
                        + "FROM Account "
                        + "WHERE username = ? AND password = ?";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4.Query Data 
                rs = stm.executeQuery();
                //5.Process Data
                if (rs.next()) {
                    String fullname = rs.getString("fullname");
                    boolean role = rs.getBoolean("role");
                    dto = new AccountDTO(username, password, fullname, role);
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
        return dto;
    }
    
    public List<AccountDTO> searchAccountByFullName(String searchValue, String username) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<AccountDTO> listAccount = new ArrayList<AccountDTO>();
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT username, password, fullname, role "
                        + "FROM Account "
                        + "WHERE fullname LIKE ? AND username != ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, username);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username1 = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    boolean role = rs.getBoolean("role");
                    AccountDTO dto = new AccountDTO(username1, password,
                            fullname, role);
                    listAccount.add(dto);
                }//end while rs not null
            }//end if con is not null
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
        return listAccount;
    }
    
     public List<AccountDTO> searchAccountGoogleByFullName(String searchValue, String username) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<AccountDTO> listAccount = new ArrayList<AccountDTO>();
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT username, email, name, role, id "
                        + "FROM Account "
                        + "WHERE name LIKE ? AND username != ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, username);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username1 = rs.getString("username");
                    String email = rs.getString("email");
                    String name = rs.getString("name");
                    boolean role = rs.getBoolean("role");
                    String id = rs.getString("id");
                    AccountDTO dto = new AccountDTO(username1, role, id, name, email);
                    listAccount.add(dto);
                }//end while rs not null
            }//end if con is not null
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
        return listAccount;
    }

    public boolean deleteAccountByUsername(String username) 
        throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Delete Account "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                int effectRows = stm.executeUpdate();
                if (effectRows > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
      
    
    public boolean updateAccount(String username, String password, boolean role, String fullName) 
        throws SQLException, NamingException {
        Connection con =  null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Update Account "
                        + "Set password = ?, role = ?, fullname = ? "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, fullName);
                stm.setString(4, username);
                int effectRows = stm.executeUpdate();
                if (effectRows > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
       public boolean updateAccount(String id, String name, boolean role) 
        throws SQLException, NamingException {
        Connection con =  null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Update Account "
                        + "Set name = ?, role = ? "
                        + "Where id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setBoolean(2, role);
                stm.setString(3, id);
                int effectRows = stm.executeUpdate();
                if (effectRows > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean registerAccount(String username, String password, String fullName, boolean role) 
        throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Insert into Account("
                        + "username, password, fullname, role) "
                        + "Values(?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullName);
                stm.setBoolean(4, role);
                int effectRows = stm.executeUpdate();
                if (effectRows > 0) {
                    return true;
                }
            }
        } finally {
            if(stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public AccountDTO getAccountByEmail(String email) 
        throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        AccountDTO accountDTO = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Select username, id, email, name, role "
                        + "From Account "
                        + "Where email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("username");
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    boolean role = rs.getBoolean("role");
                    accountDTO = new AccountDTO(username, role, id, name, email);                   
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
        return accountDTO;
    }
    
    public AccountDTO getAccountById(String id) 
        throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        AccountDTO accountDTO = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Select username, id, email, name, role "
                        + "From Account "
                        + "Where id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("username");
                    String id1 = rs.getString("id");
                    String name = rs.getString("name");
                    boolean role = rs.getBoolean("role");
                    String email = rs.getString("email");
                    accountDTO = new AccountDTO(username, role, id1, name, email);                   
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
        return accountDTO;
    }
    
    public boolean registerAccount(String username, String id, String email, String name, boolean role) 
        throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Insert into Account("
                        + "username, id, email, name, role) "
                        + "Values(?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, id);
                stm.setString(3, email);
                stm.setString(4, name);
                stm.setBoolean(5, role);
                int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            } 
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public List<AccountDTO> removeUserInSearchList(List<AccountDTO> accountDTO, String username) {
        if (accountDTO != null) {
            for (AccountDTO accountDTO1 : accountDTO) {
                if (accountDTO1.getUsername().equals(username)) {
                    accountDTO.remove(accountDTO1);
                }
            }
        }
        return accountDTO;
    }
}
