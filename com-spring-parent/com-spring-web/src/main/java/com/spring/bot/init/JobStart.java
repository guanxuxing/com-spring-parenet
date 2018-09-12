package com.spring.bot.init;

import com.spring.bot.job.ClearFileJob;
import com.spring.bot.job.JobManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * Created by Administrator on 2018-08-31.
 */
@Component
public class JobStart implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        JobManager.addJob("clear.file.job", ClearFileJob.class, "*/5 * * * * ?");
        System.out.println("-----job启动成功" + new Date() + "------");
    }
}
