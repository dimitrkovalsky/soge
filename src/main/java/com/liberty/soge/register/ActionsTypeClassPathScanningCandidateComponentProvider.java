package com.liberty.soge.register;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ActionsTypeClassPathScanningCandidateComponentProvider extends ClassPathScanningCandidateComponentProvider {
    
    private String[] packages;
    
    private Set<BeanDefinition> beanDefinitions;
    
    public ActionsTypeClassPathScanningCandidateComponentProvider() {
        super(true);
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        log.info("candidate = " + beanDefinition);
        return true;
    }
    
    @PostConstruct
    public void init() {
        
        beanDefinitions = new HashSet<BeanDefinition>();
        
        for(String p : packages) {
            beanDefinitions.addAll(findCandidateComponents(p));
        }
        log.info("detected action types" + beanDefinitions);
    }
    
    public String[] getPackages() {
        return packages;
    }

    public void setPackages(String[] packages) {
        this.packages = packages;
    }

    public Set<BeanDefinition> getBeanDefinitions() {
        return beanDefinitions;
    }
    
}
