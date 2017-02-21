package com.liberty.soge.codegen;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;

/**
 * @author Dmytro_Kovalskyi.
 * @since 21.02.2017.
 */
public interface AnnotationDescription {
    AnnotationSpec LOMBOK_DATA = AnnotationSpec.builder(ClassName.get("lombok.Data", "Data")).build();
}
