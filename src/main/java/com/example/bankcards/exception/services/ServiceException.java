package com.example.bankcards.exception.services;

import com.example.bankcards.exception.services.enums.ServiceExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class ServiceException extends RuntimeException {
    private final ServiceExceptionCode serviceExceptionCode;
    private final String message;

    public ServiceException(ServiceExceptionCode serviceExceptionCode) {
        this(serviceExceptionCode, serviceExceptionCode.getTitle());
    }
}
