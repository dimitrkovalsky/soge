package com.liberty.soge.codegen;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Dmytro_Kovalskyi.
 * @since 21.02.2017.
 */
public class Runner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(GeneratorConfig.class);
        CodeGenerator generator = context.getBean(CodeGenerator.class);
        generator.createModel("TestModel");
    }
}
