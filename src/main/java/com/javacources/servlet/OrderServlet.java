package com.javacources.servlet;

import com.javacources.dao.CarDAO;
import com.javacources.dao.DAOFactory;
import com.javacources.dao.OrderDAO;
import com.javacources.entity.Car;
import com.javacources.entity.Order;
import com.javacources.entity.User;
import com.javacources.service.OrderService;
import com.javacources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by tanya on 3/9/14.
 */
@Controller
public class OrderServlet extends HttpServlet {
    @Autowired
    public OrderService orderService;
    @Autowired
    public UserService userService;

    @Override
    public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null){
            orderService = (OrderService) session.getAttribute("orderService");
            userService = (UserService) session.getAttribute("userService");
            int k = orderService.createOrder(userService.getCurUser(),
                    httpServletRequest.getParameter("brand"),
                    httpServletRequest.getParameter("model"),
                    Integer.valueOf(httpServletRequest.getParameter("year")));

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/order.jsp");
            rd.forward(httpServletRequest, httpServletResponse);
           //httpServletResponse.sendRedirect("/order.jsp");

        }
        else{
            httpServletResponse.sendRedirect("/index.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null){
            orderService = (OrderService) session.getAttribute("orderService");
            userService = (UserService) session.getAttribute("userService");

            List<Order> uOrders= orderService.findAllUserOrders(userService.getCurUser());

            for (int i = 0; i < uOrders.size(); i++){
                if (httpServletRequest.getParameter(String.valueOf(i)) != null){
                    orderService.deleteOrder(uOrders.get(i));
                }
            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/order.jsp");
            rd.forward(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    public void destroy() {
        orderService.close();
    }

}
