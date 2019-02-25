package servlet;

import manager.MessageManager;
import model.Message;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/addMessage")
public class AddMessageServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int toId = Integer.parseInt(req.getParameter("toId"));
        String text = req.getParameter("message");
        User user = (User) req.getSession().getAttribute("user");
        Message message = new Message();
        message.setMessage(text);
        message.setFromId(user.getId());
        message.setToId(toId);
        MessageManager messageManager = new MessageManager();
        try {
            messageManager.add(message);
            resp.sendRedirect("/userHome");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
