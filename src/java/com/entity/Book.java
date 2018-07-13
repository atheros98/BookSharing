/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

/**
 *
 * @author Administrator
 */
public class Book {
    private int iD, iDImage;
    private String title, author, iSBN, language, description, tag;
    private boolean status;

    public Book(int iDImage, String title, String author, String iSBN, String language, String description) {
        this.iDImage = iDImage;
        this.title = title;
        this.author = author;
        this.iSBN = iSBN;
        this.language = language;
        this.description = description;
    }
    
    public Book(int iDImage, String title, String author, String iSBN, String language, String description, String tag, boolean status) {
        this(iDImage, title, author, iSBN, language, description);
        this.tag = tag;
        this.status = status;
    }

    public Book(int iD, int iDImage, String title, String author, String iSBN, String language, String description, String tag, boolean status) {
        this(iDImage, title, author, iSBN, language, description, tag, status);
        this.iD = iD;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public int getiDImage() {
        return iDImage;
    }

    public void setiDImage(int iDImage) {
        this.iDImage = iDImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getiSBN() {
        return iSBN;
    }

    public void setiSBN(String iSBN) {
        this.iSBN = iSBN;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return this.iD; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        Book anotherBook = (Book)obj;
        return iSBN.equals(anotherBook.getiSBN()); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
