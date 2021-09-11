package com.crmeb.springbootdemo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crmeb.springbootdemo.dao.SystemAdminDao;
import com.crmeb.springbootdemo.model.SystemAdmin;
import com.crmeb.springbootdemo.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @program: springboot-demo
 * @author: 大粽子
 * @create: 2021-09-10 18:44
 **/
@Service
public class SystemAdminServiceImpl extends ServiceImpl<SystemAdminDao, SystemAdmin> implements SystemAdminService {

    @Resource
    private SystemAdminDao systemAdminDao;


    /**
     * 自定义查询sql
     *
     * @return 返回实体类
     */
    @Override
    public SystemAdmin getSelfAll() {
        return systemAdminDao.getAllTest();
    }
}
