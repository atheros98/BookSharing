/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.ReviewBookDAO;
import com.entity.ReviewBook;
import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class ReviewBookController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        try {
            String idBook = request.getParameter("idBook");
            String top = request.getParameter("top");
            ReviewBookDAO dao = new ReviewBookDAO();
            ObjectMapper mapperObj = new ObjectMapper();
            ArrayList<ReviewBook> arr = dao.getReviewBookByTop(top, idBook);
            System.out.println(arr);
            // convert book to json
            String arrJson = mapperObj.writeValueAsString(arr);
            System.out.println(arrJson);
            response.getWriter().write(arrJson);
        } catch (Exception ex) {
            Logger.getLogger(ReviewBookController.class.getName()).log(Level.SEVERE, null, ex);
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
            HttpSession session = request.getSession();
            ReviewBookDAO dao = new ReviewBookDAO();
            String idBook = request.getParameter("idBook");
            String content = request.getParameter("content");
            ReviewBook r = new ReviewBook();
            User user = (User) session.getAttribute("currentUser");
            r.setIdBook(Integer.parseInt(idBook));
            r.setIdUser(user.getId());
            r.setContent(content);
            r.setCreateDate(new Date());
            if (dao.createReviewBook(r) == true) {
                response.getWriter().write("true");
            }
        } catch (Exception ex) {
            Logger.getLogger(ReviewBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
