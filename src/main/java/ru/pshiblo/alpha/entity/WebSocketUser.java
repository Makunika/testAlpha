package ru.pshiblo.alpha.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Сущность вебсокет-юзер
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WebSocketUser {

    /**
     * Пользователь
     */
    private User user;

    /**
     * UUID вебсокета
     */
    private String UUID;
}
