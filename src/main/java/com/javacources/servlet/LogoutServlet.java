package com.javacources.servlet;


import com.javacources.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by tanya on 3/9/14.
 */

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession(false);
        session.invalidate();
        httpServletResponse.sendRedirect("/index.jsp");
    }

    @Override
    public void destroy() {
    }
}
