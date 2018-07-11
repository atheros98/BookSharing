/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class User {

    private int id;
    private String fullName;
    private Date birthday;
    private String avatar;
    private String userName;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
    private String linkFacebook;
    private int userPoint;
    private Date createDate;

    public User() {
    }

    public User(String fullName, Date birthday, String avatar, String userName, String password, String email, String adress, String phoneNumber, String linkFacebook, int userPoint, Date createDate) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.avatar = avatar;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.address = adress;
        this.phoneNumber = phoneNumber;
        this.linkFacebook = linkFacebook;
        this.userPoint = userPoint;
        this.createDate = createDate;
    }

    public User(int id, String fullName, Date birthday, String avatar, String userName, String password, String email, String adress, String phoneNumber, String linkFacebook, int userPoint, Date createDate) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
        this.avatar = avatar;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.address = adress;
        this.phoneNumber = phoneNumber;
        this.linkFacebook = linkFacebook;
        this.userPoint = userPoint;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLinkFacebook() {
        return linkFacebook;
    }

    public void setLinkFacebook(String linkFacebook) {
        this.linkFacebook = linkFacebook;
    }

    public int getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(int userPoint) {
        this.userPoint = userPoint;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return id + fullName + birthday + avatar + userName + password + email + address + phoneNumber + linkFacebook + userPoint + createDate; //To change body of generated methods, choose Tools | Templates.
    }

}
