/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.connect.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Atheros
 */
public class ImageBookDAO {

    public String getImageByBookID(String bookID) throws Exception {
        String query = "select TOP 1 [urlImage] from ImageBook"
                + " where idBook='" + bookID + "'";
        Connection conn = new ConnectionDB().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        String imageURL = "";
        while (rs.next()){
            imageURL = rs.getString(1);
        }
        rs.close();
        conn.close();
        return imageURL;
    }
}
