package com.itheima.web.cookie; /**
 * @author Mendy
 * @create 2023-05-30 15:11
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/bServlet")
public class bServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. receive cookie array
        Cookie[] cookies = request.getCookies();

        //2. iterate array
        for (Cookie cookie : cookies) {
            //3. obtain cookie's data
            String name = cookie.getName();
            if("username".equals(name)){
                String value = cookie.getValue();

                //URL decoding
                value = URLDecoder.decode(value,"UTF-8");
                System.out.println(name + ": " + value);
                break;
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
