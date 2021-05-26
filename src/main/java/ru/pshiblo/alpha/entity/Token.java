package ru.pshiblo.alpha.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Токен авторизации
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Token {

    /**
     * Пользователь
     */
    private User user;

    /**
     * UUID вебсокета
     */
    private String UUID;
}
