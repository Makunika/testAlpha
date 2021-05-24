package ru.pshiblo.alpha.entity;

import lombok.*;

/**
 * Сущность пользователя
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    /**
     * Никнейм пользователя
     */
    private String username;
}
