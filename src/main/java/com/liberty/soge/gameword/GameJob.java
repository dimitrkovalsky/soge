package com.liberty.soge.gameword;

import com.liberty.soge.annotation.EventHandler;
import com.liberty.soge.common.JsonHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmytro_Kovalskyi.
 * @since 20.02.2017.
 */
@Slf4j
@Component
public class GameJob extends QuartzJobBean {

    @Autowired
    private GenericEventHandler eventHandler;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        GameEvent gameEvent = null;
        try {
            JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
            String beanData = (String) dataMap.get(EventGenerator.BEAN_DATA_KEY);
            String className = (String) dataMap.get(EventGenerator.CLASS_NAME_KEY);
            Class<?> clazz = Class.forName(className);
            gameEvent = JsonHelper.toEntity(beanData, clazz);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        if (gameEvent != null)
            handle(gameEvent);

        else
            log.error("Can not find event handlers");
    }

    private void handle(GameEvent gameEvent) {
        try {

            Method[] withAnnotation = MethodUtils.getMethodsWithAnnotation(GenericEventHandler.class, EventHandler.class);
            Map<String, Method> methods = new HashMap<>();
            for (Method method : withAnnotation) {
                String eventClassName = method.getAnnotation(EventHandler.class).value().getName();
                methods.put(eventClassName, method);
            }

            Method handler = methods.get(gameEvent.getClass().getName());
            if (handler == null) {
                log.error("Can not find handler for " + gameEvent.getClass().getName());
                return;
            }
            handler.invoke(eventHandler, gameEvent);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
