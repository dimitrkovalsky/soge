package com.liberty.soge.codegen;

import com.squareup.javapoet.ClassName;

/**
 * @author Dmytro_Kovalskyi.
 * @since 22.02.2017.
 */
public interface TypeDescription {
    ClassName MONGO_REPOSITORY_TYPE = ClassName.get("org.springframework.data.mongodb.repository", "MongoRepository");
    ClassName STRING_TYPE = ClassName.get(String.class);
}
