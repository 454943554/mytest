package com.itheima.web.servlet;

import com.itheima.bean.LinkMan;
import com.itheima.bean.PageBean;
import com.itheima.service.LinkManService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: yp
 */
//@WebServlet("/linkMan")

public class LinkManServletOri extends HttpServlet {

    private LinkManService linkManService = new LinkManService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.处理乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //1.获得method请求参数
        String methodStr = request.getParameter("method");

        //2.判断
        if("findAll".equals(methodStr)){
            //查询所有的联系人
            findAll(request,response);
        }else if("add".equals(methodStr)){
            //新增联系人
            add(request,response);
        }else if("delete".equals(methodStr)){
            //删除联系人(根据id删除)
            delete(request,response);
        }else if("findPage".equals(methodStr)){
            //分页查询
            findPage(request,response);
        }


    }

    /**
     * 分页查询联系人
     * @param request
     * @param response
     */
    public void findPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1.获得请求参数 curPage
            int curPage = Integer.parseInt(request.getParameter("curPage"));
            //2.调用业务 获得分页数据PageBean
            PageBean<LinkMan> pageBean =  linkManService.findPage(curPage);
            //3.把PageBean存request,转发list_page.jsp
            request.setAttribute("pageBean",pageBean);
            request.getRequestDispatcher("list_page.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("<h1>服务器异常!</h1>");

        }
    }

    /**
     * 根据id删除联系人
     * @param request
     * @param response
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1.获得请求参数 id
            int id = Integer.parseInt(request.getParameter("id"));
            //2.调用业务根据id删除
            linkManService.deleteById(id);
            //3.查询所有展示
            response.sendRedirect(request.getContextPath()+"/linkMan?method=findAll");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("<h1>服务器异常!</h1>");
        }
    }

    /**
     * 新增联系人
     * @param request
     * @param response
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            //1.获得请求参数,封装成LinkMan对象(BeanUtils: map的key必须和javaBean属性一致)
            Map<String, String[]> map = request.getParameterMap();
            LinkMan linkMan = new LinkMan();
            BeanUtils.populate(linkMan,map);
            //2.调用业务, 进行新增
            linkManService.add(linkMan);
            //3.查询所有展示  方式一:转发  方式二:直接调用findAll() 等价于转发的  方式三:重定向【选择】
            //request.getRequestDispatcher("linkMan?method=findAll").forward(request,response);
            //findAll(request,response);
            response.sendRedirect(request.getContextPath()+"/linkMan?method=findAll");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("<h1>服务器异常!</h1>");
        }
    }

    /**
     * 查询所有的联系人
     * @param request
     * @param response
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1.调用业务 获得List<LinkMan> list
            List<LinkMan> list =  linkManService.findAll();
            //2.把list存到request里面,转发到list.jsp
            request.setAttribute("list",list);
            request.getRequestDispatcher("list.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("<h1>服务器异常!</h1>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
