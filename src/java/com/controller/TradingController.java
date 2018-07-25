/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.TradingDAO;
import com.entity.Trading;
import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Atheros
 */
public class TradingController extends HttpServlet {

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
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentUser");
            TradingDAO t = new TradingDAO();

            boolean check = false;
            String method = request.getParameter("method");

            System.out.println(method);
            if (method != null) {
                int idTrading = Integer.valueOf(request.getParameter("idTrading"));
                switch (method) {
                    case "Delete":
                        check = t.deleteTradingBook(idTrading);
                        break;
                    case "Borrow":
                        int idBorrower = Integer.valueOf(request.getParameter("idBorrower"));
                        check = t.requestTrading(idTrading, idBorrower);
                        break;
                    case "Accept":
                        check = t.acceptTrading(idTrading);
                        break;
                    case "Reject":
                        check = t.rejectTrading(idTrading);
                        break;
                    case "Complete":
                        check = t.completeTrading(idTrading);
                        break;
                }
            }
            if (method.equals("Borrow")) {
                response.sendRedirect("trading.jsp");
            } else {
                response.getWriter().write(check + "");
            }

        } catch (Exception ex) {
            Logger.getLogger(TradingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
