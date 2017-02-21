package com.liberty.soge.codegen;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author Dmytro_Kovalskyi.
 * @since 21.02.2017.
 */
@Component
public class CodeGenerator implements AnnotationDescription {

    @Autowired
    private GeneratorContext context;

    @Autowired
    private FileSaver saver;
    
    public void createModel(String modelName) {
        TypeSpec typeSpec = TypeSpec.classBuilder(modelName).addAnnotation(LOMBOK_DATA).build();
        JavaFile javaFile = JavaFile.builder(context.getModelPackage(), typeSpec).build();
        saver.saveModel(javaFile);
    }
}
