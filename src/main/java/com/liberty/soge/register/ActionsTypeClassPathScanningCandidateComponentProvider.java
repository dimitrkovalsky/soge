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
public class ActionsTypeClassPathScanningCandidateComponentProvider extends ClassPathScanningCandidateComponentProvider implements ActionsTypeProvider {
    
    private String[] packages;
    
    private Set<BeanDefinition> beanDefinitions;
    
    private Set<Class<?>> actionTypes;
    
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
    public void init() throws ClassNotFoundException {

        beanDefinitions = new HashSet<BeanDefinition>();

        for (String p : packages) {
            beanDefinitions.addAll(findCandidateComponents(p));
        }
        
        actionTypes = new HashSet<Class<?>>();
        
        for (BeanDefinition bd : beanDefinitions) {
            actionTypes.add(Class.forName(bd.getBeanClassName()));
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

    @Override
    public Set<Class<?>> getActionTypes() {
        return actionTypes;
    }
    
}
