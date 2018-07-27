package org.currency.convert;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Component
public class ConvertExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(ConstraintViolationException e) {
        return error(
                e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()));
    }

    private Map<String, Object> error(Object message) {
        return Collections.singletonMap("error", message);
    }
}