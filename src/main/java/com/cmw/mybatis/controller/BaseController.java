package com.cmw.mybatis.controller;

import com.cmw.mybatis.entity.HumanInfo;
import com.cmw.mybatis.server.HumanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: cmw
 * @date: 2021/11/4
 */
@RestController
@RequestMapping("/user")
public class BaseController {
    @Autowired
    HumanInfoService humanInfoService;

    @RequestMapping("/findAll")
    public List<HumanInfo> findAll(){
        return humanInfoService.findAll();
    }
}
