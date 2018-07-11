/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.connect.ConnectionDB;
import com.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class UserDAO {

    ConnectionDB db = new ConnectionDB();

    public UserDAO() {
        db.initialize();
    }

    /**
     *
     * @param userName user enter
     * @param password user enter
     * @return user if username and password exited
     */
    public User login(String userName, String password) {
        User user = new User();
        try {
            String procedure = "usp_SelectUsersDynamic";
            String sql = "username = '" + userName + "'";
            ResultSet rs = db.getResultSetParameter(procedure, sql);
            while (rs.next()) {
                if (password.equals(rs.getString(6))) {
                    user.setId(rs.getInt(1));
                    user.setFullName(rs.getString(2));
                    user.setBirthday(rs.getDate(3));
                    user.setAvatar(rs.getString(4));
                    user.setUserName(rs.getString(5));
                    user.setPassword(rs.getString(6));
                    user.setEmail(rs.getString(7));
                    user.setAddress(rs.getString(8));
                    user.setPhoneNumber(rs.getString(9));
                    user.setLinkFacebook(rs.getString(10));
                    user.setUserPoint(rs.getInt(11));
                    user.setCreateDate(rs.getDate(12));
                }
                return user;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     *
     * @param email user enter when register an account
     * @return error01 if duplicate email else return null
     */
    public String duplicateEmail(String email) {
        try {
            String sql = "Select * from [User]";
            ResultSet rs = db.getResultSet(sql);
            while (rs.next()) {
                if (email.equals(rs.getString(7))) {
                    return "error01";
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     *
     * @param userName user enter when register an account
     * @return error02 if duplicate userName else return null
     */
    public String duplicateUserName(String userName) {
        try {
            String sql = "Select * from [User]";
            ResultSet rs = db.getResultSet(sql);
            while (rs.next()) {
                if (userName.equals(rs.getString(5))) {
                    return "error02";
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     *
     * @param user
     * @return
     */
    public boolean inserUser(User user) {
        Vector v = new Vector();
        v.add(user.getFullName());
        v.add(user.getBirthday());
        v.add(user.getAvatar());
        v.add(user.getUserName());
        v.add(user.getPassword());
        v.add(user.getEmail());
        v.add(user.getAddress());
        v.add(user.getPhoneNumber());
        v.add(user.getLinkFacebook());
        v.add(user.getUserPoint());
        v.add(user.getCreateDate());
        return db.insertData("[User]", v);
    }

    /**
     *
     * @param user
     * @return
     */
    public boolean updateUserInfor(User user) {
        Vector v = new Vector();
        v.add(user.getId());
        v.add(user.getFullName());
        v.add(user.getBirthday());
        v.add(user.getAvatar());
        v.add(user.getUserName());
        v.add(user.getPassword());
        v.add(user.getEmail());
        v.add(user.getAddress());
        v.add(user.getPhoneNumber());
        v.add(user.getLinkFacebook());
        v.add(user.getUserPoint());
        v.add(user.getCreateDate());
        return db.updateData("usp_UpdateUser", v);
    }

    /**
     *
     * @param userID the id of user
     * @return User
     */
    public User getUserById(int userID) {
        ResultSet rs = null;
        User user = new User();
        try {
            String sql = "select * from [User] where id = userID";
            rs = db.getResultSet(sql);
            if (rs.next()) {
                user.setId(rs.getInt(1));
                user.setFullName(rs.getString(2));
                user.setBirthday(rs.getDate(3));
                user.setAvatar(rs.getString(4));
                user.setUserName(rs.getString(5));
                user.setPassword(rs.getString(6));
                user.setEmail(rs.getString(7));
                user.setAddress(rs.getString(8));
                user.setPhoneNumber(rs.getString(9));
                user.setLinkFacebook(rs.getString(10));
                user.setUserPoint(rs.getInt(11));
                user.setCreateDate(rs.getDate(12));
            }
        } catch (Exception ex) {
            new Close().closeResultSet(rs);
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    /**
     *
     * @param userID the id of user
     * @param point the point by another user trade
     * @param currentPoint the current point of user
     */
    public void updatePointUser(int userID, int point, int currentPoint) {
        try {
            int newPoint = (point + currentPoint) / 2;
            String sql = "UPDATE [User] SET userPoint =" + newPoint + " WHERE id = " + userID;
            //update point 
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Lấy toàn bộ danh sách người dục ( Mục quản lí người dùng)
//    public ArrayList<User> allUser() {
//        ArrayList<User> dataUser = new ArrayList<>(); // Tạo mảng chứa tất cả user
//        try {
//            String sqlCommand = "SELECT * FROM Users";
//            ResultSet rs = db.getResultSet(sqlCommand);
//            while (rs.next()) {
//                User user = new User(); // Tạo model user
//                user.setId(rs.getInt(1));
//                user.setName(rs.getString(2));
//                user.setEmail(rs.getString(3));
//                user.setEmailCode(rs.getString(4));
//                user.setTitle(rs.getString(5));
//                user.setUserName(rs.getString(6));
//                user.setPassword(rs.getString(7));
//                user.setAvatar(rs.getString(8));
//                user.setCreateDate(rs.getString(9));
//                user.setStatus(rs.getByte(10));
//                user.setNote(rs.getString(11));
//                dataUser.add(user); // add user vào mảng
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return dataUser;
//    }
}
