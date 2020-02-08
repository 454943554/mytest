package com.itheima.bean;

import java.util.List;

/**
 * @Description: 分页实体类
 * @Author: yp
 */
public class PageBeanOri {

    private List<LinkMan> list; //联系人列表【查】
    private int curPage; //当前页码
    private int sumPage; //总页码
    private int count; //总数量【查】
    private int curSize; //一页展示的数量

    public List<LinkMan> getList() {
        return list;
    }

    public void setList(List<LinkMan> list) {
        this.list = list;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getSumPage() {
        return sumPage;
    }

    public void setSumPage(int sumPage) {
        this.sumPage = sumPage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurSize() {
        return curSize;
    }

    public void setCurSize(int curSize) {
        this.curSize = curSize;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "list=" + list +
                ", curPage=" + curPage +
                ", sumPage=" + sumPage +
                ", count=" + count +
                ", curSize=" + curSize +
                '}';
    }
}
