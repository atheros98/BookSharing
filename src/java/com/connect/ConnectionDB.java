/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect;

import com.dao.Close;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ConnectionDB {

    Close close = new Close();
    Connection connection;
    Statement statement;
    private String serverName;
    private String port;
    private String username;
    private String pwd;
    private String dbName;
    private String imgFolder;

    public ConnectionDB() {
        /* ====================== Edit here ====================== */
        serverName = "JMT";
        port = "1433";
        username = "sa";
        pwd = "sa";
        dbName = "BookShareProject";
        imgFolder = "img/";
    }

    // Lấy đường dẫn thư mục ảnh từ file config
    public String getResource() throws Exception {
        return imgFolder;
    }

    // Thiết lập kết nối Database
    public boolean initialize() {
        try {
            String url = "jdbc:sqlserver://" + serverName + ":" + port + ";databaseName=" + dbName;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = (Connection) DriverManager.getConnection(url, username, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            close.closeConnection(connection); // Đóng connect
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if (connection == null) {
            close.closeConnection(connection); // Đóng connect
            return false;
        }
        return true;
    }

    // dùng câu lệnh sql để lấy ResultSet
    public ResultSet getResultSet(String sqlCommand) throws Exception {
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement(); // 
            rs = statement.executeQuery(sqlCommand);
        } catch (Exception e) {
            close.closeConnection(connection); // Đóng connect
            close.closeResultSet(rs); // Đóng ResultSet
            throw new Exception("Error get Store " + e.getMessage());
        }
        return rs;
    }

    // gọi Stored Procedure có câu điều kiện truyền vào, trả về ResultSet
    public ResultSet getResultSetParameter(String storeName, String cmd) throws Exception {
        ResultSet rs = null;
        CallableStatement cs = null;
        try {
            String callStore = createCommandStoredProcedure(storeName, 1); // Tạo dòng lệnh sql với tên Stored Procedure 
            cs = connection.prepareCall(callStore);
            cs.setString(1, cmd); // Thiết lập câu điều kiện cho Stored Procedure 
            cs.executeQuery();
            rs = cs.getResultSet();
        } catch (Exception e) {
            close.closeConnection(connection); // Đóng connect
            close.closeResultSet(rs); // Đóng ResultSet
            close.closeCallableStatement(cs); //Đóng CallableStatement
            throw new Exception("Error get Store " + e.getMessage());
        }
        return rs;
    }

    // Chèn thêm dữ liệu vào database
    public boolean insertData(String tableName, Vector vector) {
        PreparedStatement ps = null;
        String callStore = createSqlCommandInsert(tableName, vector.size()); // Tạo câu lệnh insert
        try {
            ps = connection.prepareStatement(callStore);
            for (int i = 1; i <= vector.size(); i++) {
                ps.setString(i, vector.elementAt(i - 1).toString());
            }
            ps.execute();
            return true;
        } catch (SQLException ex) {
            close.closeConnection(connection); // Đóng connect
            close.closePreparedStatement(ps); // Đóng PreparedStatement
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Cập nhật dữ liệu DB
    public boolean updateData(String storeName, Vector vector) { // storeName: Tên Stored Procedure, vector: dữ liệu update
        CallableStatement cs = null;
        String callStore = createCommandStoredProcedure(storeName, vector.size());
        try {
            cs = connection.prepareCall(callStore);
            for (int i = 0; i < vector.size(); i++) {
                cs.setString(i + 1, vector.elementAt(i).toString()); // set giá trị update tương ứng với mỗi giá trị
            }
            cs.executeUpdate();
            return true;
        } catch (SQLException ex) {
            close.closeConnection(connection); // Đóng connect
            close.closeCallableStatement(cs); //Đóng CallableStatement
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // delete data
    public boolean delete(String command, int id) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(command);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            close.closeConnection(connection); // Đóng connect
            close.closePreparedStatement(ps); // Đóng PreparedStatement
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Hàm chạy câu lệnh sql truyền vào với tham số
    public boolean runCommandSql(String command, Vector vector) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(command);
            for (int i = 1; i <= vector.size(); i++) {
                ps.setString(i, vector.elementAt(i - 1).toString());
            }
            ps.execute();
            return true;
        } catch (SQLException ex) {
            close.closeConnection(connection); // Đóng connect
            close.closePreparedStatement(ps); // Đóng PreparedStatement
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Tạo câu lệnh insert dữ liệu
    public String createSqlCommandInsert(String tableName, int size) {
        String sqlCommand = "INSERT INTO " + tableName + " VALUES (";
        for (int i = 0; i < size; i++) {
            sqlCommand += "?";
            if (i != size - 1) {
                sqlCommand += ",";
            } else {
                sqlCommand += ")";
            }
        }
        return sqlCommand;
    }

    // Tạo câu lệnh Stored Procedure 
    public String createCommandStoredProcedure(String storeName, int size) {
        String sqlCommand = "{Call " + storeName + " (";
        for (int i = 0; i < size; i++) {
            if (i == (size - 1)) {
                sqlCommand += "?";
            } else {
                sqlCommand += " ?,";
            }
        }
        sqlCommand += ")}";
        return sqlCommand;
    }
}
