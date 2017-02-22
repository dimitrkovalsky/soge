package com.liberty.soge.codegen;

import com.liberty.soge.errors.CodeGenerationException;
import com.squareup.javapoet.JavaFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
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

    @Autowired
    private GeneratorContext context;

    public void saveGeneratedCode(JavaFile javaFile) {
        try {
            Path writeDirectory = Paths.get(context.getSourceFolder());
            javaFile.writeTo(writeDirectory);
            log.info("Saved file " + getFilePath(javaFile));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private String getFilePath(JavaFile javaFile) {
        return context.getSourceFolder() + File.separator +
                javaFile.packageName.replace(".", File.separator) + File.separator +
                javaFile.typeSpec.name + ".java";
    }

    private void checkExists(Path path) {
        if (Files.exists(path)) {
            throw new CodeGenerationException("File " + path.getFileName() + " already exists in " + path.toString());
        }
    }

    public boolean modelExists(String modelName) {
        return Files.exists(Paths.get(context.getModelFolder(), modelName + ".java"));
    }
}
