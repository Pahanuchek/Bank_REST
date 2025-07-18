package com.example.bankcards.exception.global;

import com.example.bankcards.dto.response.exception.ExceptionResponse;
import com.example.bankcards.exception.services.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleServiceException(ServiceException exception) {
        return new ExceptionResponse(exception);
    }

    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public void handleServiceException(Throwable exception) {
        exception.printStackTrace();
    }

}
