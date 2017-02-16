package com.liberty.soge.schedule;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class PingJob extends QuartzJobBean {

//    @Autowired
//    private NotificationService notificationService;
    
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        notificationService.notifyAll(new StringNotification("Client ping"));
        log.info("PingJob, executed");
    }
}
