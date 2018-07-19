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

    private int ID;
    private int idOwner;
    private int idBorrower;
    private int idBook;
    private boolean statusBook;
    private boolean statusComplete;
    private Date createDate;
    private Date completeDate;

    public Trading() {
        statusBook = true;
        statusComplete = true;
    }

    public Trading(int ID, int idOwner, int idBorrower, int idBook, boolean statusBook, boolean statusComplete, Date createDate, Date completeDate) {
        this.ID = ID;
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
        return new UserDAO().searchUserByID(idOwner);
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public boolean isStatusBook() {
        return statusBook;
    }

    public void setStatusBook(boolean statusBook) {
        this.statusBook = statusBook;
    }

    public boolean isStatusComplete() {
        return statusComplete;
    }

    public void setStatusComplete(boolean statusComplete) {
        this.statusComplete = statusComplete;
    }

    public String getCreateDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(createDate);
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
        return "Tradding{" + "ID=" + ID + ", idOwner=" + idOwner + ", idBorrower=" + idBorrower + ", idBook=" + idBook + ", statusBook=" + statusBook + ", statusComplete=" + statusComplete + ", createDate=" + createDate + ", completeDate=" + completeDate + '}';
    }

    public Object[] toObject() {
        return new Object[]{
            ID,
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
