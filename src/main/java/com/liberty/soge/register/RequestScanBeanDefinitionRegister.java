package com.liberty.soge.register;

import com.liberty.soge.annotation.EnableRequestScan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

@Slf4j
public class RequestScanBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(importingClassMetadata.getClassName());
        } catch (ClassNotFoundException e) {
            log.error("error during initialization", e);
        }
        EnableRequestScan requestScan = clazz.getAnnotation(EnableRequestScan.class);
                if(requestScan != null) {
                    String[] packages = requestScan.packages();
                    BeanDefinition bd = new RootBeanDefinition(ActionsTypeClassPathScanningCandidateComponentProvider.class);
                    bd.getPropertyValues().addPropertyValue("packages", packages);
                    registry.registerBeanDefinition("actionsTypeScanningProvider", bd);
                }
    }

}
