package com.liberty.soge.rest;

import com.liberty.soge.codegen.CodeGenerator;
import com.liberty.soge.rest.request.GenerateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dmytro_Kovalskyi.
 * @since 17.02.2017.
 */
@RestController
@RequestMapping("/generator")
public class GeneratorController {

    @Autowired
    private CodeGenerator codeGenerator;

    @RequestMapping(path = "/generate/model", method = RequestMethod.POST)
    public void generateModel(@RequestBody GenerateRequest request) {
        codeGenerator.createModel(request.getClassName());
    }

    @RequestMapping(path = "/generate/repository", method = RequestMethod.POST)
    public void generateRepository(@RequestBody GenerateRequest request) {
        codeGenerator.createRepository(request.getClassName());
    }

    @RequestMapping(path = "/generate/service", method = RequestMethod.POST)
    public void generateService(@RequestBody GenerateRequest request) {
        codeGenerator.createService(request.getClassName());
    }

    @RequestMapping(path = "/generate/action", method = RequestMethod.POST)
    public void generateAction(@RequestBody GenerateRequest request) {
        codeGenerator.createAction(request.getClassName());
    }
}
