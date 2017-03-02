package com.liberty.soge.register.actions;

import com.liberty.soge.annotation.ActionTypesScan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

@Slf4j
public class ActionTypesScanBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(importingClassMetadata.getClassName());
        } catch (ClassNotFoundException e) {
            log.error("error during initialization", e);
        }
        ActionTypesScan actionTypesScan = clazz.getAnnotation(ActionTypesScan.class);
        if(actionTypesScan != null) {
            String[] packages = actionTypesScan.packages();
            BeanDefinition bd = new RootBeanDefinition(ActionTypesCandidateClassPathScanningComponentProvider.class);
            bd.getPropertyValues().addPropertyValue("packages", packages);
            registry.registerBeanDefinition("actionsTypesProvider", bd);
        }
    }

}
