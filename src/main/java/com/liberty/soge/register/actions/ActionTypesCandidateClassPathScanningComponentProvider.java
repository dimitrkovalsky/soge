package com.liberty.soge.register.actions;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

import com.liberty.soge.action.Action;
import com.liberty.soge.annotation.ActionTypes;
import com.liberty.soge.annotation.BindActionToId;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class ActionTypesCandidateClassPathScanningComponentProvider extends ClassPathScanningCandidateComponentProvider implements ActionTypesProvider {
    
    private String[] packages;
    
    private Set<BeanDefinition> beanDefinitions;
    
    private Set<Class<?>> actionTypes;
    
    private Set<Class<? extends Action>> actions = new HashSet<Class<? extends Action>>();
    
    public ActionTypesCandidateClassPathScanningComponentProvider() {
        super(true);
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        log.info("candidate = " + beanDefinition);
        Class<?> clazz = null;
        try {
            clazz = Class.forName(beanDefinition.getBeanClassName());
            if(validate(clazz)) {
                return true;
            }
        } catch (ClassNotFoundException e) {
            log.error("error during initialization", e);
        }
        return false;
    }
    
    private boolean validate(Class<?> clazz) {
        if(clazz.getAnnotation(ActionTypes.class) == null) {
            return false;
        }
        
        for (Field f : clazz.getDeclaredFields()) {
            BindActionToId[] bindedActions = f.getAnnotationsByType(BindActionToId.class);
            for (BindActionToId a : bindedActions) {
                Class<? extends Action> actionClass = a.value();
                if (!actions.add(actionClass)) {
                    throw new RuntimeException("duplicate action detected action = " 
                + actionClass 
                + " in action type = " + clazz);
                }
            }
        }
        return true;
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
