/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
//@WebFilter(urlPatterns = {"/*"})
public class Authenticate implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        return;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestPath = req.getRequestURI();

        if (requestPath.matches(".*(css|jpg|png|gif|js)")) {
            chain.doFilter(request, response);
            return;
        }

        if (requestPath.endsWith("LoginController") || requestPath.endsWith("login.jsp")) {
            HttpSession session = req.getSession();
            if (session.getAttribute("username") != null) {
                ((HttpServletResponse) response).sendRedirect("index.jsp");
            } else {
                chain.doFilter(request, response);
            }
        } else if (requestPath.endsWith("register.jsp")) {
            chain.doFilter(request, response);
        } else if (requestPath.endsWith("LogoutController")) {
            chain.doFilter(request, response);
        } else if (requestPath.endsWith("HomeController")) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = req.getSession();
            if (session.getAttribute("username") != null) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect("./LoginController");
            }
        }
    }

    @Override
    public void destroy() {
        return;
    }
}
