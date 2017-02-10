package com.liberty.soge.postprocessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.liberty.soge.annotation.ActionTypes;

import lombok.extern.slf4j.Slf4j;

//FIXME this post processor detect classes only 
@Slf4j
public class ActionTypesPostProcessor  implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().isAnnotationPresent(ActionTypes.class)) {
            log.info("annotated with ActionTypes class was found " + bean.getClass());
        } else {
            log.info("" + bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
