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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TradingDAO {

    private final ConnectionDB db;
    private final Close close;
    private final int pageSize = 10;

    public TradingDAO() throws Exception {
        db = new ConnectionDB();
        close = new Close();
    }

    /**
     *
     * @param page
     * @return
     */
    public List<Trading> getLastedTrading(int page) {
        int from = (page - 1) * pageSize + 1;
        int to = page * pageSize;
        List<Trading> tradings = new ArrayList<>();

        String query = "select * from"
                + " (select *, row_number() over (order by createDate DESC)"
                + " as row from Trading) result"
                + " where result.row between " + from + " and " + to;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
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
                tradings.add(new Trading(id, idOwn, idBorrower, idBook, sb, sc, create, complete));
            }
        } catch (Exception ex) {
            Logger.getLogger(TradingDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close.closeResultSet(rs);
            close.closePreparedStatement(ps);
            close.closeConnection(conn);
        }

        return tradings;
    }

    public int getRowCount() {
        String query = "select count(*) from Trading";
        int pages = 0;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            rs = conn.prepareStatement(query).executeQuery();
            if (rs.next()) {
                pages = rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(TradingDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close.closeResultSet(rs);
            close.closeConnection(conn);
        }

        return pages;
    }

    public int getPages() throws Exception {
        int rows = getRowCount();
        return rows / (pageSize) + ((rows % pageSize) != 0 ? 1 : 0);
    }

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
        Connection conn = null;
        try {
            conn = db.getConnection();
            currentStatusBook = conn.prepareStatement(sql).executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            close.closeConnection(conn);
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
        Connection conn;
        PreparedStatement ps = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            if (getStatusBook(idTrading) == 1) {
                ps.setInt(1, 0);
                ps.setInt(2, 0);
            } else {
                ps.setInt(1, 1);
                ps.setInt(1, 1);
            }
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(TradingDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close.closePreparedStatement(ps);
            close.closePreparedStatement(ps);
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
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            if (getStatusBook(idTrading) == 0) {
                ps.executeUpdate();
                conn.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            close.closePreparedStatement(ps);
            close.closeConnection(conn);
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
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            UserDAO u = new UserDAO();
            while (rs.next()) {
                int idOwner = rs.getInt("idOwner");
                User user = u.getUserById(String.valueOf(idOwner));
                if (user != null) {
                    list.add(user);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            close.closePreparedStatement(ps);
            close.closeConnection(conn);
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
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            UserDAO u = new UserDAO();
            while (rs.next()) {
                int idOwner = rs.getInt("idBorrower");
                User user = u.getUserById(String.valueOf(idOwner));
                if (user != null) {
                    list.add(user);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            close.closeResultSet(rs);
            close.closePreparedStatement(ps);
            close.closeConnection(conn);
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
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
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
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            close.closeResultSet(rs);
            close.closePreparedStatement(ps);
            close.closeConnection(conn);
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
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
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
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            close.closeResultSet(rs);
            close.closePreparedStatement(ps);
            close.closeConnection(conn);
        }
        return list;
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
//                trading.setId(rs.getInt(1));
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

}
