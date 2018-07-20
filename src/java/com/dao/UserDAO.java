/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.connect.ConnectionDB;
import com.entity.User;
import com.service.getDate;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class UserDAO {

    private final ConnectionDB db;
    private final Close close;

    public UserDAO() throws Exception {
        db = new ConnectionDB();
        close = new Close();
    }

    // Kiểm tra đăng nhập
    public User checkLogin(String username, String password) {
        User user = new User();
        String sqlCommand = "SELECT * FROM [User] where username = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sqlCommand);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                String correctPass = rs.getString(6);
                if (correctPass.equals(password)) { // Kiểm tra password
                    user.setId(rs.getInt(1));
                    user.setFullName(rs.getString(2));
                    user.setBirthday(rs.getDate(3));
                    user.setAvatar(rs.getString(4));
                    user.setUserName(rs.getString(5));
                    user.setEmail(rs.getString(7));
                    user.setAddress(rs.getString(8));
                    user.setPhoneNumber(rs.getString(9));
                    user.setLinkFacebook(rs.getString(10));
                    user.setUserPoint(rs.getInt(11));
                    user.setCreateDate(rs.getDate(12));
                    return user;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
            close.closeResultSet(rs);
        }
        return null;
    }

    // Kiểm tra tài khoản và email người dùng (Không trùng với tài khoản hoặc email đã tồn tại)
    public String checkRegistered(String usernameInput, String emailInput) {
        String sqlCommand = "SELECT * FROM [User]";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sqlCommand);
            rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString(3); // Lấy email trong db
                String username = rs.getString(6); // Lấy username trong db
                if (username.equals(usernameInput)) { // so sánh username
                    return "error01";
                } else if (email.equals(emailInput)) { // so sánh email
                    return "error02";
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
            close.closeResultSet(rs);
        }
        return null; // Thỏa mản đk không trùng tài khoản & email
    }

    // Tạo tài khoản trên hệ thống
    public boolean createUser(User user) {
        String sqlCommand = "INSERT INTO [User](fullName, birthday, username, password, email, createDate) values(?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareCall(sqlCommand);
            ps.setString(1, user.getFullName());
            ps.setString(2, new getDate().convertString(user.getBirthday()));
            ps.setString(3, user.getUserName());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());
            ps.setString(6, new getDate().getCurrentDate());
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
        }
        return true;
    }

    // Search account by name
    public ArrayList<User> searchUser(String input) {
        String sqlCommand = "SELECT * FROM [User] WHERE fullName like '%?%'";
        ArrayList<User> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sqlCommand);
            ps.setString(1, input);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setFullName(rs.getString(2));
                user.setBirthday(rs.getDate(3));
                user.setAvatar(rs.getString(4));
                user.setUserName(rs.getString(5));
                user.setEmail(rs.getString(7));
                user.setAddress(rs.getString(8));
                user.setPhoneNumber(rs.getString(9));
                user.setLinkFacebook(rs.getString(10));
                user.setUserPoint(rs.getInt(11));
                user.setCreateDate(rs.getDate(12));
                users.add(user);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
            close.closeResultSet(rs);
        }
        return users;
    }

    public User getUserById(String idUser) {
        User user = new User();
        String sqlCommand = "SELECT * FROM [User] where id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sqlCommand);
            ps.setString(1, idUser);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setFullName(rs.getString(2));
                user.setBirthday(rs.getDate(3));
                user.setAvatar(rs.getString(4));
                user.setUserName(rs.getString(5));
                user.setEmail(rs.getString(7));
                user.setAddress(rs.getString(8));
                user.setPhoneNumber(rs.getString(9));
                user.setLinkFacebook(rs.getString(10));
                user.setUserPoint(rs.getInt(11));
                user.setCreateDate(rs.getDate(12));
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
            close.closeResultSet(rs);
        }
        return user;
    }
}
