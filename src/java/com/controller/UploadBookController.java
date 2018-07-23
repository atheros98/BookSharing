/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.BookDAO;
import com.dao.ImageBookDAO;
import com.dao.TradingDAO;
import com.entity.Book;
import com.entity.ImageBook;
import com.entity.Trading;
import com.entity.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Administrator
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class UploadBookController extends HttpServlet {

    public static final String UPLOAD_DIR = "uploadBook";

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
        request.getRequestDispatcher("upload_book.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        int idTrading = 0;

        try {
            BookDAO bookDAO = new BookDAO();
            TradingDAO tradingDAO = new TradingDAO();

            if (request.getParameter("idBook").equals("empty")) { // create new book & new trading
                // create new book
                Book book = new Book();
                book.setTitle(request.getParameter("title-book"));
                book.setAuthor(request.getParameter("author"));
                book.setiSBN(request.getParameter("isbn"));
                book.setLanguage(request.getParameter("language"));
                book.setDescription(request.getParameter("description"));
                book.setTag(request.getParameter("tag"));
                book.setIdUser(user.getId());
                int idBook = bookDAO.insertBook(book);

                // save cover book
                for (int i = 1; i <= 5; i++) {
                    System.out.println(uploadCoverBook(request, "cover" + i, idBook));
                }

                // create trading
                Trading trading = new Trading();
                trading.setIdOwner(user.getId());
                trading.setIdBook(idBook);

                idTrading = tradingDAO.insertTrading(trading);

            } else { // create new trading
                Trading trading = new Trading();
                trading.setIdOwner(user.getId());
                trading.setIdBook(Integer.parseInt(request.getParameter("idBook")));

                idTrading = tradingDAO.insertTrading(trading);
            }

        } catch (Exception ex) {
            Logger.getLogger(UploadBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("idTrading", idTrading);
        request.getRequestDispatcher("/DetailsBookController").forward(request, response);
    }

    private String uploadCoverBook(HttpServletRequest request, String namePart, int idBook) {
        String fileName = "", checkFile = "", applicationPath = "", basePath = "";
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            Part filePart = request.getPart(namePart);

            checkFile = (String) extractFileName(filePart);
            applicationPath = request.getServletContext().getRealPath("");
            basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
            System.out.println(basePath);

            // Tạo thư mục nếu nó không tồn tại.
            File fileSaveDir = new File(basePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }

            ImageBookDAO dao = new ImageBookDAO();
            ImageBook imgBook = new ImageBook();
            imgBook.setIdBook(idBook);

            if (checkFile != null & checkFile.length() > 0) {
                fileName = idBook + "_" + namePart + getTypeImage(checkFile);
                try {
                    File outputFilePath = new File(basePath + fileName);
                    inputStream = filePart.getInputStream();
                    outputStream = new FileOutputStream(outputFilePath);
                    int read = 0;
                    final byte[] bytes = new byte[1024];
                    while ((read = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                    imgBook.setUrlImage(fileName);
                    dao.insert(imgBook);

                } catch (IOException e) {
                    e.printStackTrace();
                    fileName = "";
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            } else {
                imgBook.setUrlImage("CoverBook.jpg");
                dao.insert(imgBook);
            }

        } catch (Exception e) {
            fileName = "";
        }
        return fileName;
    }

    private String extractFileName(Part part) {
        // form-data; name="file"; filename="C:\file1.zip"
        // form-data; name="file"; filename="C:\Note\file2.zip"
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                // C:\file1.zip
                // C:\Note\file2.zip
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                // file1.zip
                // file2.zip
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }

    private String getTypeImage(String input) {
        if (input.endsWith(".jpg") || input.endsWith(".jpeg")) {
            return ".jpg";
        } else {
            return ".png";
        }
    }

}
