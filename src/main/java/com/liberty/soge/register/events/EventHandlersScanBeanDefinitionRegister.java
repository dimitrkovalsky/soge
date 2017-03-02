package com.liberty.soge.register.events;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.liberty.soge.annotation.EventHandlersTypeScan;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventHandlersScanBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(importingClassMetadata.getClassName());
        } catch (ClassNotFoundException e) {
            log.error("error during initialization", e);
        }
        EventHandlersTypeScan eventHandlerTypesScan = clazz.getAnnotation(EventHandlersTypeScan.class);
        if(eventHandlerTypesScan != null) {
            String[] packages = eventHandlerTypesScan.packages();
            BeanDefinition bd = new RootBeanDefinition(EventHandlersCandidateClassPathScanningComponentProvider.class);
            bd.getPropertyValues().addPropertyValue("packages", packages);
            registry.registerBeanDefinition("eventHandlersProvider", bd);
        }
    }
    
}
