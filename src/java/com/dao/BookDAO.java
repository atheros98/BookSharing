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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class BookDAO {

    private final ConnectionDB db;
    private final int pageSize = 15;

    public BookDAO() throws Exception {
        db = new ConnectionDB();
    }

    //Statement must start wiht "Select * from book"
    private List<Book> getBooksByStatement(String statement) throws Exception {

        List<Book> bookList = new ArrayList<>();
        Connection conn = db.getConnection();
        PreparedStatement prepareStatement = conn.prepareStatement(statement);
        ResultSet rs = prepareStatement.executeQuery();

        while (rs.next()) {
            int id = rs.getInt(1);
            int idImage = rs.getInt(2);
            String title = rs.getString(3);
            String author = rs.getString(4);
            String ISBN = rs.getString(5);
            String language = rs.getString(6);
            String description = rs.getString(7);
            String tag = rs.getString(8);
            boolean status = rs.getBoolean(9);

            Book tmp = new Book(id, idImage, title, author, ISBN, language, description, tag, status);

            bookList.add(tmp);
        }
        rs.close();
        conn.close();
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
                + " (select *, row_number as row from Book"
                + " over (order by id DESC) result"
                + " where result.row between " + from + " and " + to;
        return getBooksByStatement(query);
    }

    public List<Book> getBooksByName(String name, int page) throws Exception {
        int from = (page - 1) * pageSize + 1;
        int to = page * pageSize;
        String query = "select * from "
                + "(select *, row_number() as row from Book over (order by id DESC)"
                + " where title LIKE '%" + name + "%') result"
                + " where result.row between " + from + " and " + to;
        return getBooksByStatement(query);
    }

    public List<Book> getBooksByISBN(String ISBN, int page) throws Exception {
        int from = (page - 1) * pageSize + 1;
        int to = page * pageSize;
        String query = "select * from "
                + "(select *, row_number() as row from Book over (order by id DESC)"
                + " where title LIKE '%" + ISBN + "%') result"
                + " where result.row between " + from + " and " + to;
        return getBooksByStatement(query);
    }

    public List<Book> getBooksByAuthor(String author, int page) throws Exception {
        int from = (page - 1) * pageSize + 1;
        int to = page * pageSize;
        String query = "select * from "
                + "(select *, row_number() as row from Book over (order by id DESC)"
                + " where title LIKE '%" + author + "%') result"
                + " where result.row between " + from + " and " + to;
        return getBooksByStatement(query);
    }

    public List<Book> getBooksByTag(String tag, int page) throws Exception {
        int from = (page - 1) * pageSize + 1;
        int to = page * pageSize;
        String query = "select * from "
                + "(select *, row_number() as row from Book over (order by id DESC)"
                + " where title LIKE '%" + tag + "%') result"
                + " where result.row between " + from + " and " + to;
        return getBooksByStatement(query);
    }

    public List<Book> getBook(String queryStr, int page) throws Exception {
        int from = (page - 1) * pageSize + 1;
        int to = page * pageSize;
        String query = "select * from "
                + "select * from Book where title LIKE '%" + queryStr + "%'"
                + " union select * from Book where ISBN='" + queryStr + "'"
                + " union select * from Book where author LIKE '%" + queryStr + "%'"
                + " union select * from Book where tag LIKE '%" + queryStr + "%') result"
                + " over (order by id DESC)) final_result"
                + " where final_result.row between " + from + " and " + to;
        return getBooksByStatement(query);
    }

    public int getRowCount(String type, String queryStr) throws Exception {
        String query = "";
        switch (type) {
            case "lasted":
                query = "select count(*) from Book";
                break;
            case "all":
                query = "(select count (distinct *) from ("
                        + "select * from Book where title LIKE '%" + queryStr + "%'"
                        + " union select * from Book where ISBN='" + queryStr + "'"
                        + " union select * from Book where author LIKE '%" + queryStr + "%'"
                        + " union select * from Book where tag LIKE '%" + queryStr + "%') result";
                break;
            case "title":
                query = "select count (*) from Book where title LIKE '%" + queryStr + "%'";
                break;
            case "author":
                query = "select count (*) from Book where author LIKE '%" + queryStr + "%'";
                break;
            case "ISBN":
                query = "select count (*) from Book where ISBN LIKE '%" + queryStr + "%'";
                break;
            case "tag":
                query = "select count (*) from Book where tag LIKE '%" + queryStr + "%'";
                break;
        }

        Connection conn = db.getConnection();
        ResultSet rs = conn.prepareStatement(query).executeQuery();
        int pages = 0;
        if (rs.next()) {
            pages = rs.getInt(1);
        }
        rs.close();
        conn.close();
        return pages;
    }

    public int getPages(String type, String queryStr) throws Exception {
        int rows = getRowCount(type, queryStr);
        return rows / (pageSize) + ((rows % pageSize) != 0 ? 1 : 0);
    }

}
