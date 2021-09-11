package com.crmeb.springbootdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crmeb.springbootdemo.model.SystemAdmin;

/**
 * @program: springboot-demo
 * @author: 大粽子
 * @create: 2021-09-10 18:42
 **/
public interface SystemAdminService extends IService<SystemAdmin> {
    /**
     * 自定义查询sql
     * @return 返回实体类
     */
    SystemAdmin getSelfAll();
}
