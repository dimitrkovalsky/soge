package com.liberty.soge.codegen;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;

/**
 * @author Dmytro_Kovalskyi.
 * @since 21.02.2017.
 */
public interface AnnotationDescription {
    AnnotationSpec LOMBOK_DATA = AnnotationSpec.builder(ClassName.get("lombok", "Data")).build();
    AnnotationSpec SPRING_REPOSITORY = AnnotationSpec.builder(ClassName.get("org.springframework.stereotype", "Repository")).build();
    AnnotationSpec SPRING_COMPONENT = AnnotationSpec.builder(ClassName.get("org.springframework.stereotype", "Component")).build();
    AnnotationSpec SPRING_SERVICE = AnnotationSpec.builder(ClassName.get("org.springframework.stereotype", "Service")).build();
}
