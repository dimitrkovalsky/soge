package com.liberty.soge.codegen;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

import static org.apache.commons.lang3.ClassUtils.PACKAGE_SEPARATOR;

/**
 * @author Dmytro_Kovalskyi.
 * @since 21.02.2017.
 */
@Component   //TODO: move to interface
public class GeneratorContext {
    private static final String MODEL_PACKAGE = "model";
    private static final String REPOSITORY_PACKAGE = "repository";
    private static final String SERVICE_PACKAGE = "service";
    private static final String ACTION_PACKAGE = "action";

    private String sourceFolder;
    private String basePackage;

    private String basePackageFolder;

    public GeneratorContext(String projectFolder, String basePackage) {
        this.sourceFolder = projectFolder + "src/main/java".replace("/", File.separator);
        this.basePackage = basePackage;
    }

    @PostConstruct
    private void init() {
        basePackageFolder = sourceFolder + File.separator + packageToPath(basePackage);
    }

    public String getModelFolder() {
        return basePackageFolder + File.separator + MODEL_PACKAGE;
    }

    public String getModelPackage() {
        return basePackage + PACKAGE_SEPARATOR + MODEL_PACKAGE;
    }

    public String getRepositoryPackage() {
        return basePackage + PACKAGE_SEPARATOR + REPOSITORY_PACKAGE;
    }

    public String getServicePackage() {
        return basePackage + PACKAGE_SEPARATOR + SERVICE_PACKAGE;
    }

    public String getActionPackage() {
        return basePackage + PACKAGE_SEPARATOR + ACTION_PACKAGE;
    }

    public String getRepositoryFolder() {
        return basePackageFolder + File.separator + REPOSITORY_PACKAGE;
    }

    public String getServiceFolder() {
        return basePackageFolder + File.separator + SERVICE_PACKAGE;
    }

    public String getActionFolder() {
        return basePackageFolder + File.separator + SERVICE_PACKAGE;
    }

    private String packageToPath(String packageName) {
        return packageName.replace(PACKAGE_SEPARATOR, File.separator);
    }

    public String getSourceFolder() {
        return sourceFolder;
    }
}
