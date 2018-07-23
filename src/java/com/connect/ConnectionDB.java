/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Administrator
 */
public class ConnectionDB {

//    private String serverName;
//    private String port;
//    private String username;
//    private String pwd;
//    private String dbName;
//    private String imgFolder;
    private final String serverName = "localhost";
    private final String dbName = "BookShareProject";
    private final String port = "1433";
    private final String username = "sa";   
    private final String pwd = "123456";
    private final String imgFolder = "img/";

    public ConnectionDB() throws Exception {
//        Properties prop = new Properties();
//        prop.load(new FileInputStream("com/connect/dbConfig.properties"));
//        serverName = prop.getProperty("serverName");
//        dbName = prop.getProperty("dbName");
//        port = prop.getProperty("portNumber");
//        username = prop.getProperty("userID");
//        pwd = prop.getProperty("password");
//        imgFolder = prop.getProperty("imgFolder");
    }

    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection("jdbc:sqlserver://" + serverName + ":" + port + ";databaseName=" + dbName, username, pwd);
    }

    public String getResource() throws Exception {
        return imgFolder;
    }

}
