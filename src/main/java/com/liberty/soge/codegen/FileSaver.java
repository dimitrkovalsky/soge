package com.liberty.soge.codegen;

import com.liberty.soge.errors.CodeGenerationException;
import com.squareup.javapoet.JavaFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Dmytro_Kovalskyi.
 * @since 21.02.2017.
 */
@Slf4j
@Component
public class FileSaver {
    //TODO: add validation for file exists
    @Autowired
    private GeneratorContext context;

    public void saveModel(JavaFile javaFile) {
        String fileName = javaFile.typeSpec.name;
        Path path = Paths.get(fileName, context.getModelFolder());
        checkExists(path);
        try {
            javaFile.writeTo(path);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void checkExists(Path path) {
        if (Files.exists(path)) {
            throw new CodeGenerationException("File " + path.getFileName() + " already exists in " + path.toString());
        }
    }
}
