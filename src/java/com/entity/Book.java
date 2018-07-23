/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import com.dao.ImageBookDAO;

/**
 *
 * @author Administrator
 */
public class Book {

    private int id, idUser;
    private String title, author, iSBN, language, description, tag;
    private boolean status;

    public Book() {
    }

    public Book(int id, int idUser, String title, String author, String iSBN, String language, String description, String tag, boolean status) {
        this.id = id;
        this.idUser = idUser;
        this.title = title;
        this.author = author;
        this.iSBN = iSBN;
        this.language = language;
        this.description = description;
        this.tag = tag;
        this.status = status;
    }

    public String getImage() throws Exception {
        return new ImageBookDAO().getImageByBookID(String.valueOf(id));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
    public String toString() {
        return "Book{" + "id=" + id + ", idUser=" + idUser + ", title=" + title + ", author=" + author + ", iSBN=" + iSBN + ", language=" + language + ", description=" + description + ", tag=" + tag + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        return this.id; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        Book anotherBook = (Book) obj;
        return iSBN.equals(anotherBook.getiSBN()); //To change body of generated methods, choose Tools | Templates.
    }

}
