package com.cmw.xxljob.handler;


import com.cmw.tools.StringUtil;
import com.google.common.base.Joiner;
import com.xxl.job.core.context.XxlJobHelper;
import org.quartz.JobDataMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @description: xxljob公共类，用于获取请求参数
 * @author: cmw
 * @date: 2021/11/1
 */

public abstract class XxlJobBaseTimer {

    private static Logger logger = LoggerFactory.getLogger(XxlJobBaseTimer.class);
    /**
     * @return
     * @Description //获取任务参数
     * @Date 2021/11/1
     * @Param
     */
    public JobDataMap getJobParams() {
        String jobParam = XxlJobHelper.getJobParam().replaceAll("\r|\n", "");
        logger.info("xxljob传入的参数为："+jobParam);
        JobDataMap jobDataMap = new JobDataMap();
        if (StringUtil.isNotEmpty(jobParam)) {
            String[] split = jobParam.split(";");
            for (String paramI : split) {
                String[] split1 = paramI.split(",");
                String[] param = new String[split1.length - 1];
                for (int i = 0; i < split1.length - 1; i++) {
                    param[i] = split1[i + 1];
                }
                String join = Joiner.on(",").join(param);
                jobDataMap.put(split1[0].trim(), join);
            }
        }
        return jobDataMap;
    }


    /**
     * 获取目标方法
     *
     * @param proxyObject
     * @param methodStr
     * @return
     */
    public Method getMethod(Class proxyObject, String methodStr) {
        Method[] methods = proxyObject.getMethods();
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(methodStr)) {
                return method;
            }
        }
        return null;
    }
}