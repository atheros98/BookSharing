/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.BookDAO;
import com.entity.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Atheros
 */
public class SearchBookController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String query = request.getParameter("query");
            System.out.print(query);
            if (query == null || query.trim().isEmpty()) {
                response.sendRedirect("./HomeController");
                return;
            }
            String filter = request.getParameter("filter");
            String pageStr = request.getParameter("pageID");
            if (pageStr == null || pageStr.trim().isEmpty()) {
                pageStr = "1";
            }
            int page = 1;
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {

            }
            BookDAO b = new BookDAO();
            int numberPages = 0;
            List<Book> books = new ArrayList<>();
            if (filter == null || filter.trim().isEmpty()) {
                filter = "All";
            }
            numberPages = b.getPages(filter, query);
            if (page > numberPages) {
                page = numberPages;
            }
            switch (filter) {
                case "All":
                    books = b.getBook(query, page);
                    break;
                case "Title":
                    books = b.getBooksByName(query, page);
                    break;
                case "Author":
                    books = b.getBooksByAuthor(query, page);
                    break;
                case "ISBN":
                    books = b.getBooksByISBN(query, page);
                    break;
                case "Tag":
                    books = b.getBooksByTag(query, page);
                    break;
            }
//            System.out.println("DMCMCSADIAHDIAD"+ query);
            request.setAttribute("books", books);
            request.setAttribute("pages", numberPages);
            RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
