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
    private int id;
    private int bookID;
    private String title;
    private Date createDate;
    private int status;
    private int userID;
    private String username;

    public TradingDetail() {
    }

    public TradingDetail(int id, int bookID, String title, Date createDate, int status) {
        this.id = id;
        this.bookID = bookID;
        this.title = title;
        this.createDate = createDate;
        this.status = status;
        }

    
  
    public TradingDetail(int id, int bookID, String title, Date createDate, int status, int userID, String username) {
        this.id = id;
        this.bookID = bookID;
        this.title = title;
        this.createDate = createDate;
        this.status = status;
        this.userID = userID;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
}
