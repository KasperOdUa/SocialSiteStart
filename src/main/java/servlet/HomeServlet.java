package servlet;

import manager.MessageManager;
import manager.UserManager;
import model.Message;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/userHome")
public class HomeServlet extends HttpServlet {

    private UserManager userManager = new UserManager();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.getSession().setAttribute("user", user);

        if (user != null) {
            req.getSession().setAttribute("user", user);
            req.setAttribute("users", userManager.getAllUsers());
            req.setAttribute("friends", userManager.getAllFriends(user.getId()));
            req.setAttribute("requests", userManager.getAllRequests(user.getId()));
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        }
    }
}
