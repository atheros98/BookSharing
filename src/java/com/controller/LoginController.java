/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.UserDAO;
import com.entity.User;
import com.service.getDate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class LoginController extends HttpServlet {

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
        request.getRequestDispatcher("/login.jsp").forward(request, response);
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
            String command = request.getParameter("command");
            UserDAO userDao = new UserDAO();
            switch (command) {
                // Đăng kí tài khoản
                case "register":
                    String username = request.getParameter("username");
                    String email = request.getParameter("email");
                    String check = userDao.checkRegistered(username, email);
                    if (check == null) { // Nếu username và email người dùng đăng kí hợp lệ
                        User user = new User();
                        user.setUserName(username);
                        user.setEmail(email);
                        user.setFullName(request.getParameter("fullname"));
                        user.setPassword(request.getParameter("password"));
                        System.out.println(request.getParameter("bod"));
                        user.setBirthday(new getDate().convertDate(request.getParameter("bod")));
                        userDao.createUser(user);
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    } else if (check.equals("error01")) { // TH username người dùng đã tồn tại
                        request.setAttribute("error", "Sorry, This username already has registered ! ");
                        request.getRequestDispatcher("/registered.jsp").forward(request, response);
                    } else if (check.equals("error02")) { // TH email người dùng đã tồn tại
                        request.setAttribute("error", "Sorry, This email already has registered ! ");
                        request.getRequestDispatcher("/registered.jsp").forward(request, response);
                    }
                    break;
                // Đăng nhập
                case "login":
                    User user = userDao.checkLogin(request.getParameter("username"), request.getParameter("password"));
                    if (user == null) { // TH lỗi đăng nhập
                        request.setAttribute("error", "Username or Password is incorrect");
                        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                        rd.forward(request, response);
                    } else { // TH đăng nhập thành công
                        session.setAttribute("currentUser", user);
                        session.setAttribute("username", user.getUserName());
                        response.sendRedirect("index.jsp");
                    }
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
