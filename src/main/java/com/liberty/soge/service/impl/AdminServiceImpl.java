package com.liberty.soge.service.impl;

import com.liberty.soge.action.Action;
import com.liberty.soge.common.CommandRegistry;
import com.liberty.soge.model.HandlerDescriptor;
import com.liberty.soge.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmytro_Kovalskyi.
 * @since 17.02.2017.
 */
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private CommandRegistry registry;

    @Override
    public List<HandlerDescriptor> getHandlerDescriptors() {
        List<HandlerDescriptor> descriptors = new ArrayList<>();
        registry.getAllCommands().forEach((messageType, handler) -> {
            descriptors.add(new HandlerDescriptor(messageType, handler.getSimpleName(),
                    handler.getCanonicalName(), getInput(handler)));
        });

        return descriptors;
    }

    private String getInput(Class<? extends Action> handler) {
        // TODO: Oleksii please implement using random generators
        return "Test";
    }
}
