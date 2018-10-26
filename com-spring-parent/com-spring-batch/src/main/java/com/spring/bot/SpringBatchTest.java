package com.spring.bot;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2018-10-25.
 * 参考链接:
 * @see <a href="https://www.yiibai.com/spring_batch/spring_batch_basic_application.html"></a>
 */
public class SpringBatchTest {

    public static void main(String[] args) throws Exception{
        // HelloWord 测试  [success]
       // helloWord();

        // mysql 查询信息生成txt [success]
     //   mysqlToTxt();

        // mysql 查询信息生成xml [fail]
        mysqlToXml();

    }

    public static void helloWord() throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("job_config.xml");
        SimpleJobLauncher jobLauncher = (SimpleJobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("hwJob");
        JobExecution execution = jobLauncher.run(job, new JobParameters());
        System.out.println("exit status :" + execution.getStatus());
    }

    public static void mysqlToTxt() throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("job_mysqltotxt_config.xml");
        SimpleJobLauncher jobLauncher = (SimpleJobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("hwJob");
        JobExecution execution = jobLauncher.run(job, new JobParameters());
        System.out.println("exit status :" + execution.getStatus());
    }

    public static void mysqlToXml() throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("job_mysqltoxml_config.xml");
        SimpleJobLauncher jobLauncher = (SimpleJobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("hwJob");
        JobExecution execution = jobLauncher.run(job, new JobParameters());
        System.out.println("exit status :" + execution.getStatus());
    }




}
