package com.liberty.soge.config;

import org.springframework.context.annotation.Configuration;

import com.liberty.soge.annotation.ActionTypesScan;

@Configuration
@ActionTypesScan(packages = { "com.liberty.soge.types" })
public class ActionTypesConfig {}
