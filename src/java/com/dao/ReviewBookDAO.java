/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.connect.ConnectionDB;
import com.entity.RateBook;
import com.entity.ReviewBook;
import com.service.getDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ReviewBookDAO {

    private final ConnectionDB db;
    private final Close close;

    public ReviewBookDAO() throws Exception {
        db = new ConnectionDB();
        close = new Close();
    }

    // Tạo review book mới
    public boolean createReviewBook(ReviewBook review) {
        String sqlCommand = "INSERT INTO [ReviewBook](idBook, idUser, content, createDate) values(?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareCall(sqlCommand);
            ps.setInt(1, review.getIdBook());
            ps.setInt(2, review.getIdUser());
            ps.setString(3, review.getContent());
            ps.setString(4, new getDate().getCurrentDate());
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

    // Lấy ra top input review book gần nhất
    public ArrayList<ReviewBook> getReviewBookByTop(String top, String idBook) {
        String sqlCommand = "select TOP " + top + " r.*, u.username from ReviewBook r, [User] u where r.idUser=u.id and r.idBook = ? order by r.id desc";
        ArrayList<ReviewBook> review = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sqlCommand);
            ps.setString(1, idBook);
            rs = ps.executeQuery();
            while (rs.next()) {
                ReviewBook r = new ReviewBook();
                r.setId(rs.getInt(1));
                r.setIdBook(rs.getInt(2));
                r.setIdUser(rs.getInt(3));
                r.setContent(rs.getString(4));
                r.setCreateDate(rs.getDate(5));
                r.setUsername(rs.getString(6));
                review.add(r);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
            close.closeResultSet(rs);
        }
        return review;
    }

    // update
    public boolean insertRateStar(RateBook rate) {
        String sqlCommand = "INSERT INTO [RateBook](idBook, idUser, rate) values(?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareCall(sqlCommand);
            ps.setInt(1, rate.getIdBook());
            ps.setInt(2, rate.getIdUSer());
            ps.setInt(3, rate.getRate());
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

    // insert
    public boolean updateRateBook(RateBook rate) {
        String sqlCommand = "UPDATE RateBook set rate = ? where idBook = ? and idUser = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareCall(sqlCommand);
            ps.setInt(1, rate.getRate());
            ps.setInt(2, rate.getIdBook());
            ps.setInt(3, rate.getIdUSer());
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

    public boolean checkRateExist(RateBook rate) {
        String sqlCommand = "Select Count(*) from RateBook where idBook = ? and idUser =?";
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sqlCommand);
            ps.setInt(1, rate.getIdBook());
            ps.setInt(2, rate.getIdUSer());
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == 0) {
                    check = true;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
            close.closeResultSet(rs);
        }
        return check;
    }

    // select
    public int getRate(int idBook, int idUser) {
        String sqlCommand = "Select * from RateBook where idBook = ? and idUser = ?";
        int point = 5;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sqlCommand);
            ps.setInt(1, idBook);
            ps.setInt(2, idUser);
            rs = ps.executeQuery();
            while (rs.next()) {
                point = rs.getInt(4);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 5;
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
            close.closeResultSet(rs);
        }
        return point;
    }

    public int getRateOfBook(int idBook) {
        String sqlCommand = "Select * from RateBook where idBook = ?";
        int point = 0, count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sqlCommand);
            ps.setInt(1, idBook);
            rs = ps.executeQuery();
            while (rs.next()) {
                point += rs.getInt(4);
                count += 1;
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 5;
        } finally {
            close.closeConnection(conn);
            close.closePreparedStatement(ps);
            close.closeResultSet(rs);
        }
        return point / count;
    }

    public static void main(String[] args) {
        try {
            ReviewBookDAO r = new ReviewBookDAO();
            System.out.println(r.getRate(3, 1));
            System.out.println(r.getRateOfBook(3));
        } catch (Exception ex) {
            Logger.getLogger(ReviewBookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
