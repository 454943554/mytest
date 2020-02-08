package com.itheima.web.filter;

import com.itheima.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: yp
 */
@WebFilter("/*")
public class PrivilegeFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //0.判断是否是不需要登录就可以访问的资源,直接放行(login.jsp,index.jsp, regist.jsp, 登录,注册...)
        String requestURI = request.getRequestURI();  // eg: /day27exercise/index.jsp
        if(requestURI.contains("index.jsp") || requestURI.contains("login.jsp") || requestURI.contains("register.jsp") || requestURI.contains("login")){
            chain.doFilter(request,response);
            return;
        }


        //1.判断是否登录了(判断session里面user是否为null)
        User user = (User) request.getSession().getAttribute("user");
        if (user != null){
            //2.登录了, 放行
            chain.doFilter(request,response);
        }else{
            //3.没有登录, 跳转到登录页面
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
    }

    public void destroy() {
    }



    public void init(FilterConfig config) throws ServletException {

    }

}
