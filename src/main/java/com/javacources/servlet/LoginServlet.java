package com.javacources.servlet;

import com.javacources.dao.DAOFactory;
import com.javacources.dao.UserDAO;
import com.javacources.service.OrderService;
import com.javacources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by tanya on 3/9/14.
 */
@Controller
public class LoginServlet extends HttpServlet {
    @Autowired
    public UserService userService;
    @Autowired
    public OrderService orderService;

    @Override
    public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = null;

        String login = httpServletRequest.getParameter("login");
        String password = httpServletRequest.getParameter("password");

        int k = userService.createUser(login, password, "");

        if (k >= 0){
            session = httpServletRequest.getSession(true);
            session.setAttribute("userService", userService);
            session.setAttribute("orderService", orderService);
            httpServletResponse.sendRedirect("/order.jsp");
        }
        else{
            PrintWriter out = httpServletResponse.getWriter();

            out.print("<html><body>");
            out.print("<h1>Please, try to log in once more</h1>");
            out.print("<h2><a href=\"/index.jsp\" \">Back</a></h2>");
            out.print("</body></html>");
        }
    }

    @Override
    public void destroy() {
    }
}
