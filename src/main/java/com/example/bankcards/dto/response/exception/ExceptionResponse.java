package com.example.bankcards.dto.response.exception;

import com.example.bankcards.exception.services.ServiceException;
import com.example.bankcards.exception.services.enums.ServiceExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponse {
    private ServiceExceptionCode serviceExceptionCode;
    private String message;

    public ExceptionResponse(ServiceException serviceException) {
        serviceExceptionCode = serviceException.getServiceExceptionCode();
        message = serviceException.getMessage();
    }

    public ExceptionResponse(ServiceExceptionCode serviceExceptionCode) {
        this.serviceExceptionCode = serviceExceptionCode;
        message = serviceExceptionCode.getTitle();
    }
}
