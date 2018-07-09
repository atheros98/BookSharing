/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.connect.ConnectionDB;

/**
 *
 * @author Administrator
 */
public class ReviewBookDAO {

    ConnectionDB db = new ConnectionDB();

    public ReviewBookDAO() {
        db.initialize();
    }

    // code here
}
