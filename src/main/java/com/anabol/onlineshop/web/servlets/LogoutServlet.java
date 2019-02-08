package com.anabol.onlineshop.web.servlets;

import com.anabol.onlineshop.web.auth.TokenUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LogoutServlet extends HttpServlet {
    List<String> tokens;

    public LogoutServlet(List<String> tokens) {
        this.tokens = tokens;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Cookie logoutCookie = TokenUtils.logout(request, tokens);
        response.addCookie(logoutCookie);

        response.sendRedirect("/login");
    }
}
