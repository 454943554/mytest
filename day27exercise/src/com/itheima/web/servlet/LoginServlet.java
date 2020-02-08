package com.itheima.web.servlet;

import com.itheima.bean.User;
import com.itheima.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: yp
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //0.处理乱码
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");

            //1.获得用户名和密码
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            //2.使用DBUtils根据用户名和密码查询数据库 封装成User对象
            QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
            String sql = "SELECT * FROM user WHERE username = ? AND password  =?";
            User user = queryRunner.query(sql, new BeanHandler<User>(User.class), username, password);
            //3.判断是否登录成功(判断User是否为null)
            if (user != null){
                //3.1 user!= null 响应登录成功
                //****************记住用户名*********************
                //判断用户是否勾选了记住用户名
                String rem = request.getParameter("rem");
                if(rem != null && "ok".equals(rem)){
                    //勾选了, 把用户名存到Cookie
                    Cookie cookie = new Cookie("username", username);
                    cookie.setMaxAge(60*60*24*7);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }else {
                    //没有勾选 删除cookie
                    Cookie cookie = new Cookie("username", "");
                    cookie.setMaxAge(0);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
                //***********************************************

                //把user保存到session里面
                request.getSession().setAttribute("user",user);
                //重定向到首页
                response.sendRedirect(request.getContextPath()+"/index.jsp");


            }else{
                //3.2 user== null 响应登录失败
                //response.getWriter().print("登录失败!");
                //向request存msg
                request.setAttribute("msg","登录失败!");
                //转发到login.jsp
                request.getRequestDispatcher("login.jsp").forward(request,response);

            }
        } catch (Exception e) {
            e.printStackTrace();
            //response.getWriter().print("登录失败!");
            //向request存msg
            request.setAttribute("msg","登录失败!");
            //转发到login.jsp
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
