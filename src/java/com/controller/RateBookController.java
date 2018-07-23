/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.ReviewBookDAO;
import com.entity.StarPoint;
import com.entity.RateBook;
import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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
public class RateBookController extends HttpServlet {

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
            String idBook = request.getParameter("idBook");
            HttpSession session = request.getSession();
            ReviewBookDAO dao = new ReviewBookDAO();
            User user = (User) session.getAttribute("currentUser");
            //
            ObjectMapper mapperObj = new ObjectMapper();
            StarPoint star = new StarPoint();
            star.setUserRate(dao.getRate(Integer.parseInt(idBook), user.getId()));
            star.setTotalRate(dao.getRateOfBook(Integer.parseInt(idBook)));
            //
            String starJson = mapperObj.writeValueAsString(star);
            System.out.println(starJson);
            response.getWriter().write(starJson);

        } catch (Exception ex) {
            Logger.getLogger(RateBookController.class.getName()).log(Level.SEVERE, null, ex);
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
            String idBook = request.getParameter("idBook");
            String rate = request.getParameter("rate");
            HttpSession session = request.getSession();
            //
            ReviewBookDAO dao = new ReviewBookDAO();
            User user = (User) session.getAttribute("currentUser");
            // update rate

            RateBook rb = new RateBook();
            rb.setIdBook(Integer.parseInt(idBook));
            rb.setIdUser(user.getId());
            rb.setRate(Integer.parseInt(rate));

            // check
            if (dao.checkRateExist(rb)) { // chua co thi tao moi
                dao.insertRateStar(rb);
                int totalRate = dao.getRateOfBook(Integer.parseInt(idBook)); // update
                response.getWriter().write(totalRate + "");
            } else {
                if (dao.updateRateBook(rb) == true) {
                    int totalRate = dao.getRateOfBook(Integer.parseInt(idBook)); // update
                    response.getWriter().write(totalRate + "");
                } else {
                    response.getWriter().write("false");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(RateBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
