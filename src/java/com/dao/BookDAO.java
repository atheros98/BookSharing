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

    public List<Book> getBooksByName(String name) throws Exception{
        String query = "select * from Book where title LIKE '%" + name + "%'";
        return getBooksByStatement(query);
    }
    
    public List<Book> getBooksByISBN(String ISBN) throws Exception{
        String query = "select * from Book where ISBN='" + ISBN + "'";
        return getBooksByStatement(query);
    }
    
    public List<Book> getBooksByAuthor(String author) throws Exception{
        String query = "select * from Book where author LIKE '%" + author + "%'";
        return getBooksByStatement(query);
    }
    
    public List<Book> getBooksByTag(String tag) throws Exception{
        String query = "select * from Book where tag LIKE '%" + tag + "%'";
        return getBooksByStatement(query);
    }
    
    public List<Book> getBook(String queryStr) throws Exception{
        String query = "select distinct * from ("
                + "select * from Book where title LIKE '%" + queryStr + "%'"
                + " union select * from Book where ISBN='" + queryStr + "'"
                + " union select * from Book where author LIKE '%" + queryStr + "%'"
                + " union select * from Book where tag LIKE '%" + queryStr + "%') result";
        return getBooksByStatement(query);
    }
    
    
    
    public List<List<Book>> getBooksByInfo(String info) throws Exception {
        List<List<Book>> bookList = new ArrayList<>();
        List<Book> searchByName, searchByAuthor, searchByISBN, searchByTag;

        searchByISBN = getBooksByStatement("select * from Book where ISBN='" + info + "'");
        searchByName = getBooksByStatement("select * from Book where title LIKE '%" + info + "%'");
        searchByAuthor = getBooksByStatement("select * from Book where author LIKE '%" + info + "%'");
        searchByTag = getBooksByStatement("select * from Book where tag LIKE '%" + info + "%'");

        
        bookList.add(searchByISBN);
        bookList.add(searchByName);
        bookList.add(searchByAuthor);
        bookList.add(searchByTag);
        return bookList;
    }

}
