package com.cmw.mybatis.server;

import com.cmw.mybatis.entity.HumanInfo;

import java.util.List;

public interface HumanInfoService {
    List<HumanInfo> findAll();

    void updateByPK(int id);
}
