/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Trading {

    private int id;
    private int idOwner;
    private int idBorrower;
    private int idBook;
    private boolean statusBook;
    private boolean statusComplete;
    private Date createDate;
    private Date completeDate;

    public Trading() {

    }

    public Trading(int id, int idOwner, int idBorrower, int idBook, boolean statusBook, boolean statusComplete, Date createDate, Date completeDate) {
        this.id = id;
        this.idOwner = idOwner;
        this.idBorrower = idBorrower;
        this.idBook = idBook;
        this.statusBook = statusBook;
        this.statusComplete = statusComplete;
        this.createDate = createDate;
        this.completeDate = completeDate;
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

    public Date getCreateDate() {
        return createDate;
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

}
