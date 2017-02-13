package com.liberty.soge.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liberty.soge.action.Action;
import com.liberty.soge.action.errors.InvalidRequestAction;
import com.liberty.soge.action.errors.MalformedAction;
import com.liberty.soge.action.errors.UnknownCommand;
import com.liberty.soge.errors.ValidationException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmytro_Kovalskyi.
 * @since 09.02.2017.
 */
@Slf4j
@Service
public class GenericMessageProcessorImpl implements MessageProcessor {
    
    @Autowired
    private CommandRegistry commandRegistry;
    
    public GenericMessageProcessorImpl() {
    }
    
    public Action process(String json) {
        Action action;
        if (!validateBase(json))
            return new MalformedAction();
        GenericMessage genericRequest;
        try {
            genericRequest = getRequestObject(json);
        } catch (ValidationException e) {
            return new MalformedAction();
        }

        if ((action = getCommandObject(genericRequest)) == null)
            return new MalformedAction();

        if (!validateBody(action)) {
            return new InvalidRequestAction();
        }

        return action;
    }

    private boolean validateBody(Action action) {
        BodyValidator validator = new BodyValidator();
        return validator.validateBody(action);
    }

    private boolean validateBase(String json) {
        return !(json == null || json.length() == 0);
    }

    private GenericMessage getRequestObject(String json) {
        log.debug("REQUEST>>>>>>>>> " + json);
        ObjectMapper mapper = JsonHelper.getObjectMapper();

        try {
            return mapper.readValue(json, GenericMessage.class);
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

    private Action getCommandObject(GenericMessage requestObject) {
        Action outCommand = commandRegistry.getCommand(requestObject.getMessageType());
        if (outCommand == null)
            outCommand = new UnknownCommand();

        outCommand.setRequest(requestObject);

        return outCommand;
    }
}
