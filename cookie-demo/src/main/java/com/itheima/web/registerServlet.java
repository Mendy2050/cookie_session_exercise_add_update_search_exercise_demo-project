package com.itheima.web; /**
 * @author Mendy
 * @create 2023-05-31 19:21
 */

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
    private UserService service = new UserService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. acquire username and password
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //2. encapsulate the data into a user object
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);


        //2.1 get checkCode input by the user
        String checkCode = request.getParameter("checkCode");

        //2.2 get checkCode generated by the program
        HttpSession session = request.getSession();
        String checkCodeGen = (String)session.getAttribute("checkCodeGen");
        System.out.println("Generated checkCode: " + checkCodeGen); // print the generated checkCode

        // Make sure that checkCodeGen and checkCode are not null before comparing them
        if(!checkCodeGen.equalsIgnoreCase(checkCode)){
            // not allow to register
            // give a notice
            request.setAttribute("register_msg","验证码错误");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }



        //3. call service method for registration
        boolean flag = service.register(user);

        //4. check if the registration was succeeded
        if(flag){
            //registration succeeded/. jump to login page

            // save to request scope
            request.setAttribute("register_msg","注册成功请登录");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else{
            //registration failed, jump to register page

            // save to request scope
            request.setAttribute("register_msg","用户名已存在");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}