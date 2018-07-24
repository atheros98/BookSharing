/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Atheros
 */
public class TradingDetail implements Serializable{
    private int bookID;
    private String title;
    private Date createDate;
    private int status;
    private int userID;
    private int username;

    public TradingDetail() {
    }

    public TradingDetail(int bookID, String title, Date createDate, int status, int userID, int username) {
        this.bookID = bookID;
        this.title = title;
        this.createDate = createDate;
        this.status = status;
        this.userID = userID;
        this.username = username;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }
    
    
    
}
