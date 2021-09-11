package com.crmeb.springbootdemo.controller;

import com.crmeb.springbootdemo.model.SystemAdmin;
import com.crmeb.springbootdemo.request.SystemAdminAddRequest;
import com.crmeb.springbootdemo.service.SystemAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: springboot-demo
 * @author: 大粽子
 * @create: 2021-09-11 09:17
 **/
@RestController
@RequestMapping("/admin")
public class SystemAdminController {

    @Autowired
    private SystemAdminService systemAdminService;

    @ApiOperation("管理员新增")
    @Transactional
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Boolean add(@ModelAttribute SystemAdminAddRequest adminAddRequest){
        SystemAdmin systemAdmin = new SystemAdmin();
        BeanUtils.copyProperties(adminAddRequest, systemAdmin);
        boolean afterSave = systemAdminService.save(systemAdmin);
        return afterSave;
    }

    @ApiOperation("管理员列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<SystemAdmin> list(){
        List<SystemAdmin> list = systemAdminService.list();
        return list;
    }


    @ApiOperation("自定义查询")
    @RequestMapping(value = "/selfget",method = RequestMethod.GET)
    public SystemAdmin getSelf(){
        SystemAdmin admin = systemAdminService.getSelfAll();
        return admin;
    }
}
