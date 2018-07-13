/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.connect.ConnectionDB;
import com.entity.Book;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class BookDAO {

    ConnectionDB db = new ConnectionDB();

    public BookDAO() {
        db.initialize();
    }
    
    //Statement must start wiht "Select * from book"
    private Vector<Book> getBooksByStatement(String statement) {
        //Main function is in the try block
        try {
            Vector<Book> bookList = new Vector<>();
            ResultSet rs = db.getResultSet(statement);
            
            while(rs.next()) {
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
            
            return bookList;
        } catch (Exception ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //if any exceptions occur, return null
        return null;
    }
    
    public Book getBookByISBN(String xISBN) {
        //Main function is in try block.
        try {
            Book result;
            Vector<Book> bookList = getBooksByStatement("Select * from Book where ISBN = '"+ xISBN +"'");
            result = bookList.get(0);
            return result;
        } catch (Exception ex) {
//            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //If any exceptions occur, return null.
        return null;
    }
    
    public boolean isBookExisted(String xISBN) {
        boolean result=true;
        if(getBookByISBN(xISBN)==null)
            result=false;
        return result;
    }
    
    public Vector<Book> getBooksByInfo(String info) {
        Set<Book> setBooks = new LinkedHashSet<>();
        Vector<Book> bookList = new Vector<>();
        Vector<Book> searchByName, searchByAuthor, searchByISBN, searchByTag;
        
        searchByISBN = getBooksByStatement("Select * from Book where ISBN='"+ info +"'");
        searchByName = getBooksByStatement("Select * from Book where title LIKE '%"+ info +"%'");
        searchByAuthor = getBooksByStatement("Select * from Book where author LIKE '%"+ info +"%'");
        searchByTag = getBooksByStatement("Select * from Book where tag LIKE '%"+ info +"%'");
        
        //we may get a null list. Just ignore the null.
        try {setBooks.addAll(searchByName);} catch(Exception e) {}
        try {setBooks.addAll(searchByAuthor);} catch(Exception e) {}
        try {setBooks.addAll(searchByISBN);} catch(Exception e) {}
        try {setBooks.addAll(searchByTag);} catch(Exception e) {}
        
        Iterator<Book> ite = setBooks.iterator();
        while(ite.hasNext()) {
            bookList.add(ite.next());
        }
        return bookList;
    }

    public static void main(String[] args) {
        BookDAO x = new BookDAO();
        //test isBookExisted
        System.out.println(x.isBookExisted("n"));//false
        System.out.println(x.isBookExisted("N12345"));//true
        //test searchBook
        System.out.println(x.getBooksByInfo("Robert").size());//1
        System.out.println(x.getBooksByInfo("Rob").size());//2
        System.out.println(x.getBooksByInfo("program").size());//2
        System.out.println(x.getBooksByInfo("TuskDepTrai").size());//0
    }
    
    // code here
    // Test
    // Test 2 times
}
