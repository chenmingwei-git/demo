package com.cmw.mybatis.server.impl;

import com.cmw.mybatis.entity.HumanInfo;
import com.cmw.mybatis.mapper.HumanInfoMapper;
import com.cmw.mybatis.server.HumanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: cmw
 * @date: 2021/11/4
 */
@Service("HumanInfoService")
public class HumanInfoServiceImpl implements HumanInfoService {

    @Autowired
    HumanInfoMapper humanInfoMapper;

    @Override
    public List<HumanInfo> findAll() {
        return humanInfoMapper.findAll();
    }
}
