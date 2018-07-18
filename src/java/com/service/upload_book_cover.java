/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author Administrator
 */
public class upload_book_cover {

    private static final long SerialVersionUID = 1L;
    private static final String UPLOAD_DIR = "cover-book";

    // function sẽ lưu file vào server và trả về tên file ảnh đó
    private String uploadFile(HttpServletRequest request, String nameFile, int idImage) throws IOException, ServletException {
        String fileName = "";
        try {
            Part filePart = request.getPart(nameFile);
            fileName = getTypeImage((String) getFileName(filePart));

            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
            // Xóa file ảnh cover book cũ của người dùng
            System.out.println("Delete old file: ");
            deleteOldAvatar(basePath, idImage);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + idImage + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (IOException e) {
                System.out.println("Error upload cover book 1 !");
                return null; // Lỗi Input, OutputStream sẽ trả về null
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }

        } catch (IOException | ServletException e) {
            System.out.println("Error upload cover book 2 !");
            return null; // Lỗi
        }
        return idImage + fileName;
    }

    // Xóa toàn bộ file avatar cũ của người dùng
    private void deleteOldAvatar(String basePath, int idImage) {
        File currentDir = new File(basePath);
        System.out.println(basePath);
        File[] fileList = currentDir.listFiles();
        for (File file : fileList) {
            String filename = file.getName();
            System.out.println(filename.substring(0, filename.length() - 4));
            if (filename.substring(0, filename.length() - 4).equals(idImage)) {
                file.delete();
            }
        }
    }

    // function tách lấy tên file được người dùng upload lên
    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("*****partHeader :" + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    // function tách lấy định dạng ảnh người dùng upload lên
    public String getTypeImage(String input) {
        if (input.toLowerCase().endsWith(".jpg")) {
            return ".jpg";
        } else if (input.toLowerCase().endsWith(".png")) {
            return ".png";
        } else if (input.toLowerCase().endsWith(".jpeg")) {
            return ".png";
        } else {
            return null;
        }
    }
}
