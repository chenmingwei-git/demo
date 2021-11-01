package com.cmw.xxljob.handler;

import net.sf.json.JSONObject;
import com.cmw.tools.StringUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.quartz.JobDataMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @description: xxljob自定义任务
 * @author: cmw
 * @date: 2021/11/1
 */
@Component
public class CustomxxlHandler extends XxlJobBaseTimer{

    private static Logger logger = LoggerFactory.getLogger(CustomxxlHandler.class);

    @XxlJob("demoJobHandler")
    public ReturnT<String> quartzCommonHandler() throws Exception {
        logger.info("xxljob执行定时任务开始处理参数");
        try {
            JobDataMap jobParams = getJobParams();
            JSONObject jsonObject = JSONObject.fromObject(jobParams);
            logger.info("xxljob处理后的参数：" + jsonObject.toString());
            String quartzTimerName = jobParams.getString("quartzTimerName");
            if (StringUtil.isEmpty(quartzTimerName)) {
                XxlJobHelper.handleFail("xxljob执行定时任务执行异常,quartzTimerName未填写");
                return ReturnT.FAIL;
            }
            Class<?> aClass = Class.forName("com.qinjia.cpu.timer." + quartzTimerName);
            Method doJob = getMethod(aClass, "doJob");
//            Method mm = aClass.getDeclaredMethod("doJob", jobParams)
            logger.info("xxljob执行定时任务开始，任务类名：" + quartzTimerName);
            doJob.invoke(aClass.newInstance(), new Object[]{jobParams, null});
            logger.info("xxljob执行定时任务结束，任务类名：" + quartzTimerName);
        } catch (InvocationTargetException e) {
            logger.error("xxljob执行定时任执行异常", e.getTargetException());
            XxlJobHelper.handleFail("xxljob执行定时任执行异常," + e.getTargetException());
        } catch (Exception e) {
            logger.error("xxljob执行定时任执行异常", e);
            XxlJobHelper.handleFail("xxljob执行定时任执行异常," + e);
        }
        return ReturnT.SUCCESS;
    }

}
