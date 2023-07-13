package com.example.bo.base.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class ValidationUtil {
    public static void returnValidError(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errorString = new StringBuilder();
            for(ObjectError error : bindingResult.getAllErrors()) {
                errorString.append("%s%s".formatted(error.getDefaultMessage(), "\n"));
            }
            throw new AccessDeniedException(errorString.toString());
        }
    }
}
