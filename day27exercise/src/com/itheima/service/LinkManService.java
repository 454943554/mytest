package com.itheima.service;

import com.itheima.bean.LinkMan;
import com.itheima.bean.PageBean;
import com.itheima.dao.LinkManDao;
import com.itheima.web.servlet.LinkManServlet;

import java.util.List;

/**
 * @Description:
 * @Author: yp
 */
public class LinkManService {

    private LinkManDao linkManDao = new LinkManDao();


    /**
     * 分页查询联系人
     *
     * @param curPage
     * @return
     */
    public PageBean<LinkMan> findPage(int curPage) throws Exception {

        //1.调用Dao 封装PageBean
        PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
        //a 封装当前页码 curPage
        pageBean.setCurPage(curPage);

        //b 封装一页展示的数量 curSize【自定义的】
        int curSize = 10;
        pageBean.setCurSize(curSize);

        //c.封装总数量 count【查】
        int count = linkManDao.findCount();
        pageBean.setCount(count);


        //d.封装总页码 sumPage【算】
        int sumPage = 0;
        if (count % curSize == 0) {
            sumPage = count / curSize;
        } else {
            sumPage = count / curSize + 1;
        }
        pageBean.setSumPage(sumPage);

        //e.封装联系人列表 List 【查 limit a,b】
        int b = curSize;
        int a = (curPage - 1) * b;
        List<LinkMan> list = linkManDao.findLimit(a, b);
        pageBean.setList(list);
        return pageBean;
    }


    /**
     * 根据id删除联系人
     *
     * @param id
     */
    public void deleteById(int id) throws Exception {
        linkManDao.deleteById(id);
    }

    /**
     * 查询所有的联系人
     *
     * @return
     */
    public List<LinkMan> findAll() throws Exception {
        //1.调用Dao,获得List
        List<LinkMan> list = linkManDao.findAll();
        //2.返回
        return list;
    }

    /**
     * 新增联系人
     *
     * @param linkMan
     */
    public void add(LinkMan linkMan) throws Exception {
        //1.调用Dao
        linkManDao.save(linkMan);
    }


}
