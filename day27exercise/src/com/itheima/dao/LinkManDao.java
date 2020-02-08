package com.itheima.dao;

import com.itheima.bean.LinkMan;
import com.itheima.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description:
 * @Author: yp
 */
public class LinkManDao {

    private QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    /**
     * 查询所有的联系人
     * @return
     * @throws Exception
     */
    public List<LinkMan> findAll() throws Exception {
        //1.查询所有
        String sql  ="select * from linkman";
        List<LinkMan> list = queryRunner.query(sql, new BeanListHandler<LinkMan>(LinkMan.class));
        return list;
    }

    /**
     * 保存联系人(向数据库里面插入一条记录)
     * @param linkMan
     */
    public void save(LinkMan linkMan) throws Exception {
        String sql  ="insert into linkman values(?,?,?,?,?,?,?)";
        Object[] params = {
                null,
                linkMan.getName(),
                linkMan.getSex(),
                linkMan.getAge(),
                linkMan.getAddress(),
                linkMan.getQq(),
                linkMan.getEmail()
        };
        queryRunner.update(sql,params);
    }

    /**
     * 根据id删除
     * @param id
     */
    public void deleteById(int id) throws Exception {
        String sql  ="delete from linkman where id = ?";
        queryRunner.update(sql,id);
    }

    /**
     * 统计联系人的总数量
     * @return
     */
    public int findCount() throws Exception {
        String sql = "select count(*) from linkman";
        Long count = (Long)queryRunner.query(sql, new ScalarHandler());
        return count.intValue();
    }

    /***
     * 查询一页展示的联系人的List
     * @param a
     * @param b
     * @return
     */
    public List<LinkMan> findLimit(int a, int b) throws Exception {
        String sql  ="select * from linkman LIMIT ?,?";
        List<LinkMan> list = queryRunner.query(sql, new BeanListHandler<LinkMan>(LinkMan.class), a, b);
        return list;
    }
}
