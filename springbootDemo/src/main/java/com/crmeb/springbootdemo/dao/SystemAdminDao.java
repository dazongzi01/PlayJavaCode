package com.crmeb.springbootdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crmeb.springbootdemo.model.SystemAdmin;

/**
 * @program: springboot-demo
 * @author: 大粽子
 * @create: 2021-09-10 18:41
 **/
public interface SystemAdminDao extends BaseMapper<SystemAdmin> {
//    自定义查询
    SystemAdmin getAllTest();
}
