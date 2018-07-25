/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.TradingDetailDAO;
import com.entity.TradingDetail;
import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
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
public class BorrowingController extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentUser");
            TradingDetailDAO td = new TradingDetailDAO();
            List<TradingDetail> tradings = new ArrayList<>();
            ObjectMapper mapperObj = new ObjectMapper();

            String type = request.getParameter("type");
            if (type == null || type.trim().isEmpty()) {
                type = "Available";
            }

            switch (type) {
                case "Pending":
                    tradings = td.getBorrowerPedingTrading(user.getId());
                    break;
                case "Borrowing":
                    tradings = td.getBorrowingTrading(user.getId());
                    break;
                case "Complete":
                    tradings = td.getCompleteBorrowing(user.getId());
                    break;
            }

            String arrJson = mapperObj.writeValueAsString(tradings);
            System.out.println(arrJson);
            response.getWriter().write(arrJson);
        } catch (Exception ex) {
            Logger.getLogger(BorrowingController.class.getName()).log(Level.SEVERE, null, ex);
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
