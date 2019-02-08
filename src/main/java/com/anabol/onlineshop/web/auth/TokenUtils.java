package com.anabol.onlineshop.web.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TokenUtils {


    public static Cookie logout(HttpServletRequest request, List<String> tokens) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    tokens.remove(cookie.getValue());
                    cookie.setMaxAge(0);
                    return cookie;
                }
            }
        }
        return null;
    }
}
