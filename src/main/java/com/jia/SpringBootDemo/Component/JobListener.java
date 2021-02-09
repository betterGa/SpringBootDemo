package com.jia.SpringBootDemo.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;


// 监听器
public class JobListener implements JobExecutionListener {

    Logger logger = LoggerFactory.getLogger(JobListener.class);
    long startTime;
    long endTime;

    public JobListener() {
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        logger.info("job process strat......");


    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        endTime = System.currentTimeMillis();
        logger.info("job process end......");
        logger.info("cost time:"+(endTime-startTime)+" ms");
    }
}
