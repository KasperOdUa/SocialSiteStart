package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/friendRequest")

public class FriendRequestServlet extends HttpServlet {
    UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String getToId = req.getParameter("toId");
        int toId = Integer.parseInt(getToId);
        if (!userManager.isRequestSend(user.getId(), toId)) {
            if (toId != user.getId()) {
                userManager.sendFriendRequest(user.getId(), toId);
                resp.sendRedirect("/userHome");

            } else {
                req.getSession().setAttribute("error", "Sorry, do not send a request");
                resp.sendRedirect("/userHome");
            }

        }else {
            req.getSession().setAttribute("message", "You are already friend");
            resp.sendRedirect("/userHome");
        }
    }
}
