/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.connect.ConnectionDB;
import com.entity.Trading;
import com.service.getDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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

    public boolean insertTrading(Trading trading) {
        String sql = "INSERT INTO Trading (idOwner, idBorrower, idBook, statusBook, statusComplete, createDate, completeDate)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?);";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareCall(sql);
            ps.setInt(1, trading.getIdOwner());
            ps.setInt(2, trading.getIdBorrower());
            ps.setInt(3, trading.getIdBook());
            ps.setBoolean(4, trading.isStatusBook());
            ps.setBoolean(5, trading.isStatusComplete());
            ps.setString(6, new getDate().getCurrentDate());
            ps.setString(7, "null");
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
        }
        return true;
        
    }

    public int getStatusBook(int idTrading) {
        String sql = "select statusBook from Trading where id = " + idTrading;
        int currentStatusBook = 0;
        try {
            Connection conn = db.getConnection();
            currentStatusBook = conn.prepareStatement(sql).executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return currentStatusBook;
    }

    public boolean updateStatus(int idTrading) {
        String sql = "UPDATE Trading "
                + "SET statusBook = ?, statusComplete = ?"
                + "WHERE id = " + idTrading;
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            if (getStatusBook(idTrading) == 1) {
                ps.setInt(1, 0);
                ps.setInt(2, 0);
            } else {
                ps.setInt(1, 1);
                ps.setInt(1, 1);
            }
            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(TradingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
