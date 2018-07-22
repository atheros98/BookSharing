/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

/**
 *
 * @author Chi Nguyen
 */
public class ImageBook {

    private int id;
    private String urlImage;
    private int idBook;

    public ImageBook() {
    }

    public ImageBook(String urlImage, int idBook) {
        this.urlImage = urlImage;
        this.idBook = idBook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

}
