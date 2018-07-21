/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.BookDAO;
import com.dao.TradingDAO;
import com.dao.UserDAO;
import com.entity.Book;
import com.entity.Trading;
import com.service.getDate;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class DetailsBookController extends HttpServlet {

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
        String idTrading = request.getParameter("idTrading");
        if (idTrading != null) {
            try {
                TradingDAO tradingdao = new TradingDAO();
                BookDAO bookdao = new BookDAO();
                UserDAO userdao = new UserDAO();
                //
                Trading trading = tradingdao.getTradingById(idTrading);
                Book book = bookdao.getBookByBookID(trading.getIdBook() + "");
                //
                request.setAttribute("userOwner", userdao.getUserById(trading.getIdOwner() + ""));
                request.setAttribute("tradingdate", trading.getCreateDate());
                request.setAttribute("currentDate", new getDate().getCurrentDate());
                request.setAttribute("book", book);
                request.getRequestDispatcher("/details_book.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(DetailsBookController.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    }

}
