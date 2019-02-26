package servlet;

import manager.UserManager;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileExistsException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    UserManager userManager = new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String name = req.getParameter("name");
//        String surname = req.getParameter("surname");
//        String email = req.getParameter("email");
//        String password = req.getParameter("password");
//
//        User user = new User();
//        user.setName(name);
//        user.setSurname(surname);
//        user.setEmail(email);
//        user.setPassword(password);
//
//
//        userManager.addUser(user);
//        req.setAttribute("massage", "You have registered successfully! You can login now.");
//        req.getRequestDispatcher("login.jsp").forward(req, resp);
//
//            if (ServletFileUpload.isMultipartContent(req)) {
//                DiskFileItemFactory factory = new DiskFileItemFactory();
//                factory.setSizeThreshold(1024 * 1024);
//                factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//
//                ServletFileUpload upload = new ServletFileUpload(factory);
//                upload.setFileSizeMax(1024 * 1024 * 50);
//                upload.setSizeMax(1024 * 1024 * 5 * 50);
//                String uploadPath = "D:\\David\\SocialSiteStart\\uploadImages\\";
//                File uploadDir = new File(uploadPath);
//                if (!uploadDir.exists()) {
//                    uploadDir.mkdir();
//                }
//                try {
//                    User user1 = new User();
//                    List<FileItem> formItems = upload.parseRequest(req);
//                    if (formItems != null && formItems.size() > 0) {
//                        for (FileItem item : formItems) {
//                            if (!item.isFormField()) {
//                                String fileName = System.currentTimeMillis() + "_" + new File(item.getName()).getName();
//                                String filePath = uploadPath + File.separator + fileName;
//                                File storeFile = new File(filePath);
//                                item.write(storeFile);
//                                user1.setPicUrl(fileName);
//                            } else {
//                                if (item.getFieldName().equals("name")) {
//                                    user1.setName(item.getString());
//                                } else if (item.getFieldName().equals("surname")) {
//                                    user1.setSurname(item.getString());
//                                } else if (item.getFieldName().equals("email")) {
//                                    user1.setEmail(item.getString());
//                                } else if (item.getFieldName().equals("password")) {
//                                    user1.setPassword(item.getString());
//                                }
//                            }
//                        }
//                        userManager.addUser(user1);
//                        resp.sendRedirect("/login");
//                    }
//                } catch (FileExistsException e) {
//                    e.printStackTrace();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            resp.sendRedirect("/login.jsp");
        } else {

            ServletFileUpload.isMultipartContent(req);
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024 * 1024);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(1024 * 1024 * 50);
            upload.setSizeMax(1024 * 1024 * 5 * 50);
            String uploadPath = "D:\\git\\SocialSiteStart\\uploadImages";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try {
                User user1 = new User();
                List<FileItem> formItems = upload.parseRequest(req);
                if (formItems != null && formItems.size() > 0) {
                    for (FileItem item : formItems) {
                        if (!item.isFormField()) {
                            String fileName = System.currentTimeMillis() + "_" + new File(item.getName()).getName();
                            String filePath = uploadPath + File.separator + fileName;
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                            user1.setPicUrl(fileName);
                        } else {
                            if (item.getFieldName().equals("name")) {
                                user1.setName(item.getString());
                            } else if (item.getFieldName().equals("surname")) {
                                user1.setSurname(item.getString());
                            } else if (item.getFieldName().equals("email")) {
                                user1.setEmail(item.getString());
                            } else if (item.getFieldName().equals("password")) {
                                user1.setPassword(item.getString());
                            }
                        }
                    }
                    userManager.addUser(user1);
                    resp.sendRedirect("/login.jsp");
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        }
    }
