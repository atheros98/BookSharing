/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.connect.ConnectionDB;
import com.entity.ImageBook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Atheros
 */
public class ImageBookDAO {

    private final ConnectionDB db;
    private final Close close;

    public ImageBookDAO() throws Exception {
        db = new ConnectionDB();
        close = new Close();
    }

    public String getImageByBookID(String bookID) {
        String query = "select TOP 1 [urlImage] from ImageBook"
                + " where idBook='" + bookID + "'";
        String imageURL = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                imageURL = rs.getString(1);
            }

        } catch (Exception ex) {
            Logger.getLogger(ImageBookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close.closeResultSet(rs);
            close.closePreparedStatement(ps);
            close.closeConnection(conn);
        }

        return imageURL;
    }

    public ArrayList<ImageBook> getCoverBooks(int bookID) {
        String query = "select * from ImageBook"
                + " where idBook='" + bookID + "'";
        ArrayList<ImageBook> coverBooks = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                ImageBook ib = new ImageBook();
                ib.setId(rs.getInt(1));
                ib.setUrlImage(db.getCoverBookFolder() + rs.getString(2));
                ib.setIdBook(rs.getInt(3));
                coverBooks.add(ib);
            }

        } catch (Exception ex) {
            Logger.getLogger(ImageBookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close.closeResultSet(rs);
            close.closePreparedStatement(ps);
            close.closeConnection(conn);
        }

        return coverBooks;
    }

    public boolean insert(ImageBook imgb) {
        String sqlCommand = "INSERT INTO [ImageBook](urlImage, idBook) values(?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareCall(sqlCommand);
            ps.setString(1, imgb.getUrlImage());
            ps.setInt(2, imgb.getIdBook());
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

}
