package com.controller;

import com.dao.UserDAO;
import com.entity.User;
import com.service.getDate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
 * @author Chi Nguyen
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class UpdateProfileController extends HttpServlet {
    
    public static final String UPLOAD_DIR = "avatar";

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
        response.sendRedirect("profile.jsp");
//        request.getRequestDispatcher("/profile.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        String command = request.getParameter("command");
        System.out.println(command);
        try {
            UserDAO userdao = new UserDAO();
            User user = (User) session.getAttribute("currentUser");
            User u; // new constructor

            switch (command) {
                // cập nhật thông tin cá nhân người dùng
                case "update-info":
                    u = new User();
                    u.setId(user.getId());
                    u.setFullName(request.getParameter("fullname"));
                    u.setBirthday(new getDate().convertDate(request.getParameter("birthday")));
                    u.setEmail(request.getParameter("email"));
                    u.setAddress(request.getParameter("address"));
                    u.setPhoneNumber(request.getParameter("phonenumber"));
                    u.setLinkFacebook(request.getParameter("linkfacebook"));
                    
                    userdao.updateInfo(u);
                    session.setAttribute("currentUser", userdao.getUserById(user.getId() + ""));
                    
                    request.getRequestDispatcher("/profile.jsp").forward(request, response);
                    break;
                // cập nhật ảnh đại diện
                case "update-avatar":
                    String locationAva = uploadFile(request, user);
                    if (locationAva != null) {
                        if (locationAva.equals("error01")) { // Lỗi ảnh tải lên dung lượng quá 4MB
                            request.setAttribute("error", "Your photos could not be uploaded. Photos must be less than 4 MB.");
                            request.getRequestDispatcher("/profile.jsp").forward(request, response);
                        } else if (locationAva.equals("error02")) { // Lỗi ảnh tải lên không đúng định dạng
                            request.setAttribute("error", "Your photos could not be uploaded. Photos must be saved as JPG, PNG.");
                            request.getRequestDispatcher("/profile.jsp").forward(request, response);
                        } else if (locationAva.equals("empty")) { // Không có ảnh
                            request.setAttribute("error", "No images uploaded. Try again !.");
                            request.getRequestDispatcher("/profile.jsp").forward(request, response);
                        } else { // Cập nhật ảnh đại diện của người dùng
                            u = new User(user.getId(), locationAva);
                            userdao.updateAvatar(u);
                            System.out.println(locationAva);
                            // update current user add new avatar
                            user.setAvatar(File.separator + UPLOAD_DIR + File.separator + locationAva);
                            session.setAttribute("currentUser", user);
                            request.getRequestDispatcher("/profile.jsp").forward(request, response);
                        }
                    }
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(UpdateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // function sẽ lưu file vào server và trả về tên file ảnh đó
    private String uploadFile(HttpServletRequest request, User user) throws IOException, ServletException {
        String fileName = "";
        try {
            Part filePart = request.getPart("avatar");
            fileName = (String) getFileName(filePart);
            if (filePart.getSize() == 0) {
                return "empty"; // Không có file nào được upload sẽ trả về null
            } else if (filePart.getSize() > (1024 * 1024 * 4)) {
                return "error01"; // file tải lên có dung lượng lớn hơn 4MB
            } else if (getTypeImage(fileName) == null) {
                return "error02"; // file tải lên không phải dạng ảnh (có đuôi jpg, png)
            }
            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;

            // Tạo thư mục nếu nó không tồn tại.
            File fileSaveDir = new File(basePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }

            // Xóa file ảnh avatar cũ của người dùng
            System.out.println("Delete old file: ");
            deleteOldAvatar(basePath, user.getUserName());
            
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + user.getUserName() + getTypeImage(fileName));
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                
            } catch (Exception ex) {
                Logger.getLogger(UpdateProfileController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            
        } catch (IOException | ServletException e) {
            System.out.println("Error upload avatar2 !");
            return null; // Lỗi
        }
        return user.getUserName() + getTypeImage(fileName);
    }

    // Xóa toàn bộ file avatar cũ của người dùng
    private void deleteOldAvatar(String basePath, String username) {
        File currentDir = new File(basePath);
        System.out.println(basePath);
        File[] fileList = currentDir.listFiles();
        for (File file : fileList) {
            String filename = file.getName();
            System.out.println(filename.substring(0, filename.length() - 4));
            if (filename.substring(0, filename.length() - 4).equals(username)) {
                file.delete();
            }
        }
    }
    
    private String getFileName(Part part) {
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

    // function tách lấy định dạng ảnh người dùng upload lên
    private String getTypeImage(String input) {
        if (input.endsWith(".jpg")) {
            return ".jpg";
        } else if (input.endsWith(".png")) {
            return ".png";
        } else {
            return null;
        }
    }
    
}