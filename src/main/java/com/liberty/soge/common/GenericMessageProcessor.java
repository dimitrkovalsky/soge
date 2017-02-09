package com.liberty.soge.common;

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
public class GenericMessageProcessor {
    private String json;

    public GenericMessageProcessor(String json) {
        this.json = json;
    }

    public Action process() {
        Action action;
        if (!validateBase())
            return new MalformedAction();
        GenericMessage genericRequest;
        try {
            genericRequest = getRequestObject();
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

    private boolean validateBase() {
        return !(json == null || json.length() == 0);
    }

    private GenericMessage getRequestObject() {
        log.debug("REQUEST>>>>>>>>> " + json);
        ObjectMapper mapper = JsonHelper.getObjectMapper();

        try {
            return mapper.readValue(json, GenericMessage.class);
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

    private Action getCommandObject(GenericMessage requestObject) {
        Action outCommand = CommandRegistry.getCommand(requestObject.getMessageType());
        if (outCommand == null)
            outCommand = new UnknownCommand();

        outCommand.setRequest(requestObject);

        return outCommand;
    }
}
