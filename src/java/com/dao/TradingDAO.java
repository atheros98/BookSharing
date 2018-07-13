/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.connect.ConnectionDB;
import com.entity.Trading;
import com.entity.User;
import com.service.getDate;
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
public class TradingDAO {

    private final ConnectionDB db;
    private final Close close;

    public TradingDAO() throws Exception {
        db = new ConnectionDB();
        close = new Close();
    }

    /**
     *
     * @param trading
     * @return
     */
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

    /**
     *
     * @param idTrading
     * @return
     */
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

    /**
     *
     * @param idTrading
     * @return
     */
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

    /**
     *
     * @param idTrading
     * @return
     */
    public boolean deleteTrading(int idTrading) {
        String sql = "DELETE FROM Trading"
                + "WHERE id = " + idTrading;
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            if (getStatusBook(idTrading) == 0) {
                ps.executeUpdate();
                conn.close();
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     *
     * @param idBook
     * @return
     */
    public ArrayList<User> getAllBookOwner(int idBook) {
        ArrayList<User> list = new ArrayList<>();
        String sql = "select * from Trading where idBook = " + idBook;
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            UserDAO u = new UserDAO();
            while (rs.next()) {
                int idOwner = rs.getInt("idOwner");
                User user = u.searchUserByID(idOwner);
                if (user != null) {
                    list.add(user);
                }
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    /**
     *
     * @param idBook
     * @return
     */
    public ArrayList<User> getAllBookBorrower(int idBook) {
        ArrayList<User> list = new ArrayList<>();
        String sql = "select * from Trading where idBook = " + idBook;
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            UserDAO u = new UserDAO();
            while (rs.next()) {
                int idOwner = rs.getInt("idBorrower");
                User user = u.searchUserByID(idOwner);
                if (user != null) {
                    list.add(user);
                }
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    /**
     *
     * @param idOwner
     * @return
     */
    public ArrayList<Trading> getAllTradingByOwnerID(int idOwner) {
        ArrayList<Trading> list = new ArrayList<>();
        String sql = "select * from Trading where idOwner = " + idOwner;
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Trading trading = null;
            while (rs.next()) {
                int id = rs.getInt(1);
                int idOwn = rs.getInt(2);
                int idBorrower = rs.getInt(3);
                int idBook = rs.getInt(4);
                boolean sb = false;
                if (rs.getInt(5) == 1) {
                    sb = true;
                }
                boolean sc = false;
                if (rs.getInt(6) == 1) {
                    sc = true;
                };
                Date create = rs.getDate(7);
                Date complete = rs.getDate(8);
                trading = new Trading(id, idOwn, idBorrower, idBook, sb, sc, create, complete);
                list.add(trading);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    /**
     * 
     * @param idBorrowe
     * @return 
     */
    public ArrayList<Trading> getAllTradingByBorrowerID(int idBorrowe) {
        ArrayList<Trading> list = new ArrayList<>();
        String sql = "select * from Trading where idBorrower = " + idBorrowe;
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Trading trading = null;
            while (rs.next()) {
                int id = rs.getInt(1);
                int idOwner = rs.getInt(2);
                int idBorrower = rs.getInt(3);
                int idBook = rs.getInt(4);
                boolean sb = false;
                if (rs.getInt(5) == 1) {
                    sb = true;
                }
                boolean sc = false;
                if (rs.getInt(6) == 1) {
                    sc = true;
                };
                Date create = rs.getDate(7);
                Date complete = rs.getDate(8);
                trading = new Trading(id, idOwner, idBorrower, idBook, sb, sc, create, complete);
                list.add(trading);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

}
