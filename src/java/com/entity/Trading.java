/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import com.dao.BookDAO;
import com.dao.UserDAO;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author vip
 */
public class Trading {

    private int id;
    private int idOwner;
    private int idBorrower;
    private int idBook;
    private int statusBook;
    private boolean statusComplete;
    private Date createDate;
    private Date completeDate;
    
    public Trading() {
        statusBook = 0;
        statusComplete = true;
    }

    public Trading(int ID, int idOwner, int idBorrower, int idBook, int statusBook, boolean statusComplete, Date createDate, Date completeDate) {
        this.id = ID;
        this.idOwner = idOwner;
        this.idBorrower = idBorrower;
        this.idBook = idBook;
        this.statusBook = statusBook;
        this.statusComplete = statusComplete;
        this.createDate = createDate;
        this.completeDate = completeDate;
    }

    public Book getBook() throws Exception{
        return new BookDAO().getBookByBookID(String.valueOf(idBook));
    }
    
    public User getUser() throws Exception{
        return new UserDAO().getUserById(String.valueOf(idOwner));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public int getIdBorrower() {
        return idBorrower;
    }

    public void setIdBorrower(int idBorrower) {
        this.idBorrower = idBorrower;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getStatusBook() {
        return statusBook;
    }

    public void setStatusBook(int statusBook) {
        this.statusBook = statusBook;
    }

    public boolean isStatusComplete() {
        return statusComplete;
    }

    public void setStatusComplete(boolean statusComplete) {
        this.statusComplete = statusComplete;
    }

    public Date getCreateDate() {
        return createDate;
    }
    
    public String getDateString(){
        return new SimpleDateFormat("dd/MM/yyyy").format(createDate);
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    @Override
    public String toString() {
        return "Tradding{" + "ID=" + id + ", idOwner=" + idOwner + ", idBorrower=" + idBorrower + ", idBook=" + idBook + ", statusBook=" + statusBook + ", statusComplete=" + statusComplete + ", createDate=" + createDate + ", completeDate=" + completeDate + '}';
    }

    public Object[] toObject() {
        return new Object[]{
            id,
            idOwner,
            idBorrower,
            idBook,
            statusBook,
            statusComplete,
            createDate,
            completeDate
        };
    }

}
