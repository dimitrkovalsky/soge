package com.liberty.soge.register;

import com.liberty.soge.annotation.ActionTypes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

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
        Class<?> clazz = null;
        try {
			clazz = Class.forName(beanDefinition.getBeanClassName());
			if(clazz.getAnnotation(ActionTypes.class) != null) {
				return true;        
			} 
		} catch (ClassNotFoundException e) {
			log.error("error during initialization", e);
		}
        return false;
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
