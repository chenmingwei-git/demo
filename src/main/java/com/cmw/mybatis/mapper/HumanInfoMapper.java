package com.cmw.mybatis.mapper;

import com.cmw.mybatis.entity.HumanInfo;
import java.util.List;

public interface HumanInfoMapper {
    List<HumanInfo> findAll();

    void updateByPK(int id);

    void updateByPK2(int id);
}
