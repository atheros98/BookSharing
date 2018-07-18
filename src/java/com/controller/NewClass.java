/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.BookDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class NewClass {

    public static void main(String[] args) {
        try {
            BookDAO dao = new BookDAO();
            System.out.println(dao.isBookExisted("561"));
            System.out.println(dao.getBookByISBN("561"));
        } catch (Exception ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
