package com.liberty.soge.gameword;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author Dmytro_Kovalskyi.
 * @since 20.02.2017.
 */
@Slf4j
public class GameJob extends QuartzJobBean {
  
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("GameJob executed");
    }                 
}
