package com.liberty.soge.config;

import org.springframework.context.annotation.Configuration;

import com.liberty.soge.annotation.EventHandlersTypeScan;

@Configuration
@EventHandlersTypeScan(packages = {"com.liberty.soge.gameword"})
public class EventHandlersConfig {}
