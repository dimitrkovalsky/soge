package com.liberty.soge.codegen;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Modifier;


/**
 * @author Dmytro_Kovalskyi.
 * @since 21.02.2017.
 */
@Slf4j
@Component
public class CodeGenerator implements AnnotationDescription, TypeDescription {

    @Autowired
    private GeneratorContext context;

    @Autowired
    private FileSaver saver;

    public void createModel(String modelName) {
        TypeSpec typeSpec = TypeSpec.classBuilder(modelName).addAnnotation(LOMBOK_DATA)
                .addModifiers(Modifier.PUBLIC).build();
        JavaFile javaFile = JavaFile.builder(context.getModelPackage(), typeSpec).build();
        saver.saveGeneratedCode(javaFile);
        log.info(String.format("Model %s created", modelName));
    }

    public void createRepository(String modelName) {
        if (modelName.contains("Repository")) {
            modelName = modelName.replace("Repository", "");
        }
        ensureModel(modelName);
        String className = modelName + "Repository";

        ClassName modelClassName = ClassName.get(context.getModelPackage(), modelName);

        ParameterizedTypeName superInterface = ParameterizedTypeName.get(MONGO_REPOSITORY_TYPE, modelClassName, STRING_TYPE);

        TypeSpec typeSpec = TypeSpec.interfaceBuilder(className).addAnnotation(SPRING_REPOSITORY)
                .addSuperinterface(superInterface).addModifiers(Modifier.PUBLIC).build();
        JavaFile javaFile = JavaFile.builder(context.getRepositoryPackage(), typeSpec).build();
        saver.saveGeneratedCode(javaFile);
        log.info(String.format("Repository %s created", modelName));
    }

    private void ensureModel(String modelName) {

        if (saver.modelExists(modelName)) {
            log.info(String.format("Model %s exists...", modelName));
        } else {
            log.info(String.format("Model %s doesn't exist. Generating model...", modelName));
            createModel(modelName);
        }
    }

    public void createService(String className) {

    }

    public void createAction(String className) {

    }
}
