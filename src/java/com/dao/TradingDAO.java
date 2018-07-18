/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.connect.ConnectionDB;
import com.entity.Trading;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TradingDAO {

    private final ConnectionDB db;
    private final Close close;

    public TradingDAO() throws Exception {
        db = new ConnectionDB();
        close = new Close();
    }

    // Tạo tài khoản trên hệ thống
    public boolean createTrading(Trading user) {
        String sqlCommand = "INSERT INTO [Trading](fullName, birthday, username, password, email, createDate) values(?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareCall(sqlCommand);

            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(TradingDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
        }
        return true;
    }

    public Trading getTradingById(String idTrading) {
        Trading trading = new Trading();
        String sqlCommand = "SELECT * FROM Trading where id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sqlCommand);
            ps.setString(1, idTrading);
            rs = ps.executeQuery();
            while (rs.next()) {
                trading.setId(rs.getInt(1));
                trading.setIdOwner(rs.getInt(2));
                trading.setIdBorrower(rs.getInt(3));
                trading.setIdBook(rs.getInt(4));
                trading.setStatusBook(rs.getBoolean(5));
                trading.setStatusComplete(rs.getBoolean(6));
                trading.setCreateDate(rs.getDate(7));
                trading.setCompleteDate(rs.getDate(7));
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
            close.closeResultSet(rs);
        }
        return trading;
    }

    // Search account by name
//    public ArrayList<Trading> searchTrading(String input) {
//        String sqlCommand = "SELECT * FROM [Trading] WHERE fullName like '%?%'";
//        ArrayList<Trading> users = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            conn = db.getConnection();
//            ps = conn.prepareStatement(sqlCommand);
//            ps.setString(1, input);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Trading user = new Trading();
//                user.setId(rs.getInt(1));
//                user.setFullName(rs.getString(2));
//                user.setBirthday(rs.getDate(3));
//                user.setAvatar(rs.getString(4));
//                user.setTradingName(rs.getString(5));
//                user.setEmail(rs.getString(7));
//                user.setAddress(rs.getString(8));
//                user.setPhoneNumber(rs.getString(9));
//                user.setLinkFacebook(rs.getString(10));
//                user.setTradingPoint(rs.getInt(11));
//                user.setCreateDate(rs.getDate(12));
//                users.add(user);
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(TradingDAO.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        } finally {
//            close.closeConnection(conn);
//            close.closePreparedStatement(ps);
//            close.closeResultSet(rs);
//        }
//        return users;
//    }
}
