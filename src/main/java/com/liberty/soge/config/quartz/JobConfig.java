package com.liberty.soge.config.quartz;

import com.liberty.soge.schedule.PingJob;
import org.joda.time.DateTime;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.PostConstruct;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author Dmytro_Kovalskyi.
 * @since 27.01.2017.
 */
@Configuration
public class JobConfig {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @PostConstruct
    private void initialize() throws Exception {
        schedulerFactoryBean.getScheduler().addJob(pingJob(), true, true);
        if (!schedulerFactoryBean.getScheduler().checkExists(new TriggerKey("ping_trigger", "generic"))) {
            schedulerFactoryBean.getScheduler().scheduleJob(pingTrigger());
        }
    }

    private static JobDetail pingJob() {
        JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setKey(new JobKey("ping", "generic"));
        jobDetail.setJobClass(PingJob.class);
        jobDetail.setDurability(true);
        return jobDetail;
    }

    private static Trigger pingTrigger() {
        return newTrigger()
                .forJob(pingJob())
                .withIdentity("ping_trigger", "generic")
                .withPriority(50)
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(1))
                .startAt(DateTime.now().plusSeconds(2).toDate())
                .build();
    }

}
