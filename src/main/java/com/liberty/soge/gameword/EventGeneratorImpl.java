package com.liberty.soge.gameword;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author Dmytro_Kovalskyi.
 * @since 20.02.2017.
 */
@Slf4j
@Component
public class EventGeneratorImpl implements EventGenerator {

    public static final String SOGE_TRIGGER_GROUP = "soge-triggers";
    public static final String SOGE_EVENTS_GROUP = "soge-events";

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public void generateEvent(GameEvent event, long eventTime) {
        // TODO: check if not present
        SimpleTrigger trigger = (SimpleTrigger) newTrigger()
                .withIdentity(event.getEventId() + "-trigger", SOGE_TRIGGER_GROUP)
                .startAt(new Date(eventTime))
                .build();
        try {
            schedulerFactoryBean.getScheduler().scheduleJob( gameJob(event), trigger);
        } catch (SchedulerException e) {
            log.error("generateEvent ", e);
        }
    }

    private JobDetail gameJob(GameEvent event) {
        JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setKey(new JobKey(event.getEventId(), SOGE_EVENTS_GROUP));
        jobDetail.setJobDataMap(getDataMap(event));
        jobDetail.setJobClass(GameJob.class);
        jobDetail.setDurability(true);
        return jobDetail;
    }

    private JobDataMap getDataMap(GameEvent event) {
        
        Map<String, String> data = new HashMap<>();
        data.put("userId", event.userId);
        return new JobDataMap(data);
    }
}
