/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.connect.ConnectionDB;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class UserDAO {

    private final ConnectionDB db;

    public UserDAO() throws Exception {
        db = new ConnectionDB();
    }

    // code here
}
