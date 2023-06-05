package com.itheima.web.cookie; /**
 * @author Mendy
 * @create 2023-05-30 15:11
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/aServlet")
public class aServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = "张三";

        //URL encoding
        value = URLEncoder.encode(value, "UTF-8");
        System.out.println("数据存储" + value);

        //1. create cookie object
        Cookie cookie = new Cookie("username",value);

        //set out Cookie duration
        cookie.setMaxAge(60*60*24*7);

        //2. send out cookie object
        response.addCookie(cookie);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
