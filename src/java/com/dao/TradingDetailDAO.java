/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.connect.ConnectionDB;
import com.entity.TradingDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Atheros
 */
public class TradingDetailDAO {

    private final ConnectionDB db;
    private final Close close;
    private final int NOT_AVAILABLE = -1;
    private final int AVAILABLE = 0;
    private final int PENDING = 1;
    private final int LENDING = 2;
    private final int COMPLETE = 3;

    public TradingDetailDAO() throws Exception {
        db = new ConnectionDB();
        close = new Close();
    }

    public List<TradingDetail> getAvailableTrading(int idOwner) {
        List<TradingDetail> tradings = new ArrayList<>();
        String sqlCommand = "SELECT t.id, t.idBook, b.title, t.createDate, u.id, u.username"
                + " FROM Trading t"
                + " INNER JOIN Book b on b.id = t.idBook"
                + " LEFT JOIN [User] u on u.id = t.idBorrower"
                + " where t.statusBook = ? and t.idOwner = ? ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sqlCommand);
            ps.setInt(1, AVAILABLE);
            ps.setInt(2, idOwner);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int bookID = rs.getInt(2);
                String title = rs.getString(3);
                Date createDate = rs.getDate(4);
                int status = rs.getInt(5);
                int userID = rs.getInt(6);
                int username = rs.getInt(7);
                TradingDetail trading = new TradingDetail(id, bookID, title,
                        createDate, status, userID, username);
                tradings.add(trading);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
            close.closeResultSet(rs);
        }
        return tradings;
    }

    public List<TradingDetail> getOwnerPedingTrading(int idOwner) {
        List<TradingDetail> tradings = new ArrayList<>();
        String sqlCommand = "SELECT t.id, t.idBook, b.title, t.createDate, u.id, u.username"
                + " FROM Trading t"
                + " INNER JOIN Book b on b.id = t.idBook"
                + " INNER JOIN [User] u on u.id = t.idBorrower"
                + " where t.statusBook = ? and t.idOwner = ? ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sqlCommand);
            ps.setInt(1, PENDING);
            ps.setInt(2, idOwner);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int bookID = rs.getInt(2);
                String title = rs.getString(3);
                Date createDate = rs.getDate(4);
                int status = rs.getInt(5);
                int userID = rs.getInt(6);
                int username = rs.getInt(7);
                TradingDetail trading = new TradingDetail(id, bookID, title,
                        createDate, status, userID, username);
                tradings.add(trading);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
            close.closeResultSet(rs);
        }
        return tradings;
    }

    public List<TradingDetail> getBorrowerPedingTrading(int idBorrower) {
        List<TradingDetail> tradings = new ArrayList<>();
        String sqlCommand = "SELECT t.id, t.idBook, b.title, t.createDate, u.id, u.username"
                + " FROM Trading t"
                + " INNER JOIN Book b on b.id = t.idBook"
                + " INNER JOIN [User] u on u.id = t.idOwner"
                + " where t.statusBook = ? and t.idBorrower = ? ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sqlCommand);
            ps.setInt(1, PENDING);
            ps.setInt(2, idBorrower);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int bookID = rs.getInt(2);
                String title = rs.getString(3);
                Date createDate = rs.getDate(4);
                int status = rs.getInt(5);
                int userID = rs.getInt(6);
                int username = rs.getInt(7);
                TradingDetail trading = new TradingDetail(id, bookID, title,
                        createDate, status, userID, username);
                tradings.add(trading);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
            close.closeResultSet(rs);
        }
        return tradings;
    }

    public List<TradingDetail> getLendingTrading(int idOwner) {
        List<TradingDetail> tradings = new ArrayList<>();
        String sqlCommand = "SELECT t.id, t.idBook, b.title, t.createDate, u.id, u.username"
                + " FROM Trading t"
                + " INNER JOIN Book b on b.id = t.idBook"
                + " INNER JOIN [User] u on u.id = t.idBorrower"
                + " where t.statusBook = ? and t.idOwner = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sqlCommand);
            ps.setInt(1, LENDING);
            ps.setInt(2, idOwner);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int bookID = rs.getInt(2);
                String title = rs.getString(3);
                Date createDate = rs.getDate(4);
                int status = rs.getInt(5);
                int userID = rs.getInt(6);
                int username = rs.getInt(7);
                TradingDetail trading = new TradingDetail(id, bookID, title,
                        createDate, status, userID, username);
                tradings.add(trading);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
            close.closeResultSet(rs);
        }
        return tradings;
    }

    public List<TradingDetail> getBorrowingTrading(int idBorrower) {
        List<TradingDetail> tradings = new ArrayList<>();
        String sqlCommand = "SELECT t.id, t.idBook, b.title, t.createDate, u.id, u.username"
                + " FROM Trading t"
                + " INNER JOIN Book b on b.id = t.idBook"
                + " INNER JOIN [User] u on u.id = t.idOwner"
                + " where t.statusBook = ? and t.idBorrower = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sqlCommand);
            ps.setInt(1, LENDING);
            ps.setInt(2, idBorrower);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int bookID = rs.getInt(2);
                String title = rs.getString(3);
                Date createDate = rs.getDate(4);
                int status = rs.getInt(5);
                int userID = rs.getInt(6);
                int username = rs.getInt(7);
                TradingDetail trading = new TradingDetail(id, bookID, title,
                        createDate, status, userID, username);
                tradings.add(trading);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
            close.closeResultSet(rs);
        }
        return tradings;
    }

    public List<TradingDetail> getCompleteLending(int idOwner) {
        List<TradingDetail> tradings = new ArrayList<>();
        String sqlCommand = "SELECT t.id, t.idBook, b.title, t.createDate, u.id, u.username"
                + " FROM Trading t"
                + " INNER JOIN Book b on b.id = t.idBook"
                + " INNER JOIN [User] u on u.id = t.idBorrower"
                + " where t.statusBook = ? and t.idOwner = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sqlCommand);
            ps.setInt(1, COMPLETE);
            ps.setInt(2, idOwner);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int bookID = rs.getInt(2);
                String title = rs.getString(3);
                Date createDate = rs.getDate(4);
                int status = rs.getInt(5);
                int userID = rs.getInt(6);
                int username = rs.getInt(7);
                TradingDetail trading = new TradingDetail(id, bookID, title,
                        createDate, status, userID, username);
                tradings.add(trading);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
            close.closeResultSet(rs);
        }
        return tradings;
    }

    public List<TradingDetail> getCompleteBorrowing(int idBorrower) {
        List<TradingDetail> tradings = new ArrayList<>();
        String sqlCommand = "SELECT t.id, t.idBook, b.title, t.createDate, u.id, u.username"
                + " FROM Trading t"
                + " INNER JOIN Book b on b.id = t.idBook"
                + " INNER JOIN [User] u on u.id = t.idOwner"
                + " where t.statusBook = ? and t.idBorrower = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sqlCommand);
            ps.setInt(1, COMPLETE);
            ps.setInt(2, idBorrower);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int bookID = rs.getInt(2);
                String title = rs.getString(3);
                Date createDate = rs.getDate(4);
                int status = rs.getInt(5);
                int userID = rs.getInt(6);
                int username = rs.getInt(7);
                TradingDetail trading = new TradingDetail(id, bookID, title,
                        createDate, status, userID, username);
                tradings.add(trading);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
            close.closeResultSet(rs);
        }
        return tradings;
    }
}
