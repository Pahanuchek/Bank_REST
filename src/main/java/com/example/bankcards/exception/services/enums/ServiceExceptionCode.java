package com.example.bankcards.exception.services.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ServiceExceptionCode {
    USER_EXIST("Пользователь с данным логином уже существует!"),
    USER_NOT_FOUND("Пользователь отсутствует"),
    INVALID_TOKEN("Некорректный токен"),
    INVALID_ROLE("Некорректная роль"),
    USER_NOT_AUTH("Пользователь не залогинен"),
    INVALID_USER_DATE("Неверные данные пользователя"),
    UNAUTHORIZED("Требуется аутентификация"),
    FORBIDDEN("Доступ запрещен"),
    INVALID_DATE("Срок действия карты не может быть меньше текущего ");

    private final String title;

}
