package com.liberty.soge.codegen;

import org.springframework.stereotype.Component;

import java.io.File;

import javax.annotation.PostConstruct;

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

    private String projectFolder;
    private String basePackage;
    private String basePackageFolder;
    
    public GeneratorContext(String projectFolder, String basePackage) {
        this.projectFolder = projectFolder;
        this.basePackage = basePackage;
    }
    
    @PostConstruct
    private void init() {
        basePackageFolder = projectFolder + File.pathSeparator + packageToPath(basePackage);
    }
    
    public String getModelFolder() {
        return basePackageFolder + File.pathSeparator + MODEL_PACKAGE;
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
        return basePackageFolder + File.pathSeparator + REPOSITORY_PACKAGE;
    }

    public String getServiceFolder() {
        return basePackageFolder + File.pathSeparator + SERVICE_PACKAGE;
    }

    public String getActionFolder() {
        return basePackageFolder + File.pathSeparator + SERVICE_PACKAGE;
    }

    private String packageToPath(String packageName) {
        return packageName.replace(PACKAGE_SEPARATOR, File.pathSeparator);
    }


}
