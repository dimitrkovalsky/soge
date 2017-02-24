package com.liberty.soge.codegen;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.google.common.base.CaseFormat;
import com.liberty.soge.annotation.Handler;
import com.liberty.soge.common.ResponseFactory;
import com.liberty.soge.register.ActionsTypeProvider;
import com.liberty.soge.types.SogeMessageType;
import com.squareup.javapoet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;


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
    private ActionsTypeProvider actionsTypeProvider;

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
        log.error("Not implemented");
    }
    // TODO: validate for no exists
    public void createAction(String actionName, int messageType) {
        if (!actionName.contains("Action")) {
            actionName = actionName + "Action";
        }

        ClassName actionClassName = ClassName.get(context.getActionPackage(), "Action");
        ClassName genericResponse = ClassName.get("com.liberty.soge.common", "GenericResponse");

        MethodSpec execute = MethodSpec.methodBuilder("execute").addAnnotation(JAVA_OVERRIDE)
                .addModifiers(Modifier.PUBLIC).returns(genericResponse)
                .addStatement("log.info($S)", actionName + " executed")
                .addStatement(" return $T.createResponse(request)", ResponseFactory.class)
                .build();

        TypeSpec typeSpec = TypeSpec.classBuilder(actionName).addAnnotation(LOMBOK_LOGGER)
                .superclass(actionClassName).addModifiers(Modifier.PUBLIC)
                .addMethod(execute)
                .build();

        JavaFile javaFile = JavaFile.builder(context.getActionPackage(), typeSpec).build();
        saver.saveGeneratedCode(javaFile);
        log.info(String.format("Action %s created", actionName));
        createRequestType(messageType, actionName);
    }

    private void createRequestType(int messageType, String actionName) {
        Optional<Class<?>> maybeClass = getMessageTypeClass();
        if (!maybeClass.isPresent()) {
            log.error("Can not find applicable class to insert messageType : " + messageType);
            return;
        }
        Class<?> classToInsert = maybeClass.get();
        log.info(String.format("Found %s class to insert message type handling for : %s", classToInsert.toString(), messageType));
        try {
            CompilationUnit parsed = JavaParser.parse(getFilePath(classToInsert));
            ClassName actionClassName = ClassName.get(classToInsert);
            TypeSpec.Builder builder = TypeSpec.classBuilder(actionClassName).addAnnotation(SOGE_ACTION_TYPES)
                    .addModifiers(Modifier.PUBLIC);


            parsed.getNodesByType(FieldDeclaration.class).forEach(field -> {
                String fieldName = field.getVariables().get(0).getName().getIdentifier();
                String actionHandlerName = field.getAnnotationByClass(Handler.class).get()
                        .getChildNodes().get(1).toString().replace(".class", "");
                int value = Integer.parseInt(field.getVariables().get(0).getInitializer().get().toString());
                FieldSpec fieldSpec = getMessageTypeField(fieldName, actionHandlerName, value);
                builder.addField(fieldSpec);
            });

            String fieldName = getMessageTypeFieldName(actionName);
            FieldSpec newField = getMessageTypeField(fieldName, actionName, messageType);
            builder.addField(newField);

            TypeSpec typeSpec = builder.build();
            JavaFile javaFile = JavaFile.builder(classToInsert.getPackage().getName(), typeSpec).build();
            saver.saveGeneratedCode(javaFile);
            log.info(String.format("Action %s created", actionName));
        } catch (IOException e) {
            log.error("Parsing error: " + e.getMessage(), e);
        }

    }

    // TODO: move to separate component that allow to override name convention
    private String getMessageTypeFieldName(String actionName) {
        actionName = actionName.replace("Action", "");
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, actionName);
    }

    private FieldSpec getMessageTypeField(String fieldName, String handlerClassName, int messageType) {
        ClassName actionClass = ClassName.get(context.getActionPackage(), handlerClassName);

        AnnotationSpec annotationSpec = AnnotationSpec.builder(ClassName.get("com.liberty.soge.annotation", "Handler"))
                .addMember("value", CodeBlock.of("$T.class", actionClass)).build();
        return FieldSpec.builder(TypeName.INT, fieldName, Modifier.STATIC).initializer("$L", messageType)
                .addAnnotation(annotationSpec)
                .build();
    }

    private Optional<Class<?>> getMessageTypeClass() {
        return actionsTypeProvider.getActionTypes().stream()
                .filter(c -> c != SogeMessageType.class)
                .sorted(Comparator.comparing(Class::getName))
                .findFirst();
    }

    private Path getFilePath(Class<?> clazz) {
        String path = context.getSourceFolder() + File.separator +
                clazz.getPackage().getName().replace(".", File.separator) + File.separator + clazz.getSimpleName() + ".java";
        return Paths.get(path);
    }
}
