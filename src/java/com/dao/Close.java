/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Close {

    // Hàm này đóng connection database
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Close.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Hàm này đóng ResultSet
    public void closeResultSet(ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Close.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Hàm này đóng PreparedStatement
    public void closePreparedStatement(PreparedStatement ps) {
        try {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Close.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Hàm này đóng CallableStatement
    public void closeCallableStatement(CallableStatement cs) {
        try {
            if (cs != null && !cs.isClosed()) {
                cs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Close.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
