package com.cmw.xxljob.timer;

import com.cmw.mybatis.entity.HumanInfo;
import com.cmw.mybatis.mapper.HumanInfoMapper;
import com.cmw.tools.AppilicationUtil;
import lombok.extern.log4j.Log4j2;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import java.util.List;

/**
 * @description: 第一个定时任务
 * @author: cmw
 * @date: 2021/12/7
 */
@Log4j2
public class FirstJobTimer {

    public HumanInfoMapper humanInfoMapper = (HumanInfoMapper) AppilicationUtil.getBean(HumanInfoMapper.class);
    public void doJob(JobDataMap dataMap, JobExecutionContext context){
        String name = dataMap.getString("name");

        log.info("输入的参数数据为,{}",name);

        List<HumanInfo> all = humanInfoMapper.findAll();

        log.info(all.get(0).toString());

    }

}
