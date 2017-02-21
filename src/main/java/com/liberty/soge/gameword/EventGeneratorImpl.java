package com.liberty.soge.gameword;

import com.liberty.soge.common.JsonHelper;
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
            schedulerFactoryBean.getScheduler().scheduleJob(gameJob(event), trigger);
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
        try {
            Map<String, String> data = new HashMap<>();
            String beanData = JsonHelper.toJsonString(event);
            String className = event.getClass().getName();
            data.put(BEAN_DATA_KEY, beanData);
            data.put(CLASS_NAME_KEY, className);

            return new JobDataMap(data);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new JobDataMap();
        }
    }
}
