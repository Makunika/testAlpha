package ru.pshiblo.alpha.entity;

import lombok.*;

import java.util.Date;

/**
 * Сущность сообщение
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {

    /**
     * Сообщение пользователя
     */
    private String message;

    /**
     * Автор сообщения
     */
    private User owner;

    /**
     * Дата создания сообщения
     */
    private Date date;
}
