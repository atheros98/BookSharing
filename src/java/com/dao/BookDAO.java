/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.connect.ConnectionDB;
import com.entity.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class BookDAO {

    private final ConnectionDB db;
    private final Close close;
    private final int pageSize = 15;

    public BookDAO() throws Exception {
        db = new ConnectionDB();
        close = new Close();
    }

    public int insertBook(Book book) {
        int idBook = -1;
        String sqlCommand = "INSERT INTO [Book](title, author, ISBN, language, description, tag, idUser) values(?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getiSBN());
            ps.setString(4, book.getLanguage());
            ps.setString(5, book.getDescription());
            ps.setString(6, book.getTag());
            ps.setInt(7, book.getIdUser());
            int row = ps.executeUpdate();
            if (row != 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    idBook = rs.getInt(1);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close.closeResultSet(rs);
            close.closePreparedStatement(ps);
            close.closeConnection(conn);
        }
        return idBook;
    }

    //Statement must start wiht "Select * from book"
    private List<Book> getBooksByStatement(String statement) {

        List<Book> bookList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement prepareStatement = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            prepareStatement = conn.prepareStatement(statement);
            rs = prepareStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String ISBN = rs.getString(4);
                String language = rs.getString(5);
                String description = rs.getString(6);
                String tag = rs.getString(7);
                boolean status = rs.getBoolean(8);
                int idUser = rs.getInt(9);

                Book tmp = new Book(id, idUser, title, author, ISBN, language, description, tag, status);

                bookList.add(tmp);
            }
        } catch (Exception ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close.closeResultSet(rs);
            close.closePreparedStatement(prepareStatement);
            close.closeConnection(conn);
        }

        return bookList;
    }

    public Book getBookByISBN(String xISBN) {
        //Main function is in try block.
        try {
            Book result;
            List<Book> bookList = getBooksByStatement("Select * from Book where ISBN = '" + xISBN + "'");
            result = bookList.get(0);
            return result;
        } catch (Exception ex) {
//            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //If any exceptions occur, return null.
        return null;
    }

    public boolean isBookExisted(String xISBN) {
        boolean result = true;
        if (getBookByISBN(xISBN) == null) {
            result = false;
        }
        return result;
    }

    public List<Book> getLastedBook(int page) throws Exception {
        int from = (page - 1) * pageSize + 1;
        int to = page * pageSize;
        String query = "select * from"
                + " (select *, row_number() over (order by id DESC)"
                + " as row from Book) result"
                + " where result.row between " + from + " and " + to;
        return getBooksByStatement(query);
    }

    public Book getBookByBookID(String idBook) {
        Book book = null;
        String query = "select * from Book where id='" + idBook + "'";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String ISBN = rs.getString(4);
                String language = rs.getString(5);
                String description = rs.getString(6);
                String tag = rs.getString(7);
                boolean status = rs.getBoolean(8);
                int idUser = rs.getInt(9);

                book = new Book(id, idUser, title, author, ISBN, language, description, tag, status);
            }
        } catch (Exception ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close.closeResultSet(rs);
            close.closePreparedStatement(ps);
            close.closeConnection(conn);
        }

        return book;
    }

    public List<Book> getBooksByName(String name, int page) throws Exception {
        int from = (page - 1) * pageSize + 1;
        int to = page * pageSize;
        String query = "select * from "
                + "(select *, row_number() over (order by id DESC) as row from Book"
                + " where title LIKE '%" + name + "%') result"
                + " where result.row between " + from + " and " + to;
        return getBooksByStatement(query);
    }

    public List<Book> getBooksByISBN(String ISBN, int page) throws Exception {
        int from = (page - 1) * pageSize + 1;
        int to = page * pageSize;
        String query = "select * from "
                + "(select *, row_number()  over (order by id DESC) as row from Book"
                + " where ISBN LIKE '%" + ISBN + "%') result"
                + " where result.row between " + from + " and " + to;
        return getBooksByStatement(query);
    }

    public List<Book> getBooksByAuthor(String author, int page) throws Exception {
        int from = (page - 1) * pageSize + 1;
        int to = page * pageSize;
        String query = "select * from "
                + "(select *, row_number() over (order by id DESC) as row from Book"
                + " where author LIKE '%" + author + "%') result"
                + " where result.row between " + from + " and " + to;
        return getBooksByStatement(query);
    }

    public List<Book> getBooksByTag(String tag, int page) throws Exception {
        int from = (page - 1) * pageSize + 1;
        int to = page * pageSize;
        String query = "select * from "
                + "(select *, row_number() over (order by id DESC) as row from Book"
                + " where tag LIKE '%" + tag + "%') result"
                + " where result.row between " + from + " and " + to;
        return getBooksByStatement(query);
    }

    public List<Book> getBook(String queryStr, int page) throws Exception {
        int from = (page - 1) * pageSize + 1;
        int to = page * pageSize;
        String query = "select * from"
                + " (select distinct * , ROW_NUMBER() over (order by id DESC) as row from"
                + " (select * from Book where title LIKE '%" + queryStr + "%'"
                + " union select * from Book where ISBN like '%" + queryStr + "%'"
                + " union select * from Book where author LIKE '%" + queryStr + "%'"
                + " union select * from Book where tag LIKE '%" + queryStr + "%') result) final_result"
                + " where final_result.row between " + from + " and " + to;
        return getBooksByStatement(query);
    }

    public int getRowCount(String type, String queryStr) {
        int pages = 0;
        String query = "";

        switch (type) {
            case "lasted":
                query = "select count(*) from Book";
                break;
            case "All":
                query = "select count (distinct id) from"
                        + " (select * from Book where title like '%" + queryStr + "%'"
                        + " union select * from Book where ISBN like '%" + queryStr + "%'"
                        + " union select * from Book where author like '%" + queryStr + "%'"
                        + " union select * from Book where tag like '%" + queryStr + "%') result";
                break;
            case "Title":
                query = "select count (*) from Book where title LIKE '%" + queryStr + "%'";
                break;
            case "Author":
                query = "select count (*) from Book where author LIKE '%" + queryStr + "%'";
                break;
            case "ISBN":
                query = "select count (*) from Book where ISBN LIKE '%" + queryStr + "%'";
                break;
            case "Tag":
                query = "select count (*) from Book where tag LIKE '%" + queryStr + "%'";
                break;
        }

        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            rs = conn.prepareStatement(query).executeQuery();
            if (rs.next()) {
                pages = rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close.closeResultSet(rs);
            close.closeConnection(conn);
        }

        return pages;
    }

    public int getPages(String type, String queryStr) throws Exception {
        int rows = getRowCount(type, queryStr);
        return rows / (pageSize) + ((rows % pageSize) != 0 ? 1 : 0);
    }

}
