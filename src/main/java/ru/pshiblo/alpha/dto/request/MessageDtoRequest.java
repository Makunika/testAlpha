package ru.pshiblo.alpha.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.pshiblo.alpha.entity.Message;
import ru.pshiblo.alpha.entity.User;

import java.util.Date;

/**
 * DTO ответа для сообщения
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDtoRequest {

    /**
     * Сообщение пользователя
     */
    @JsonProperty("message")
    private String message;

    /**
     * Юзернейм автора
     */
    @JsonProperty("owner_username")
    private String ownerUsername;

    /**
     * перевести DTO в сообщение
     * @param user пользователь - автор данного сообщения
     * @return сообщение
     */
    public Message toMessage(User user) {
        if (!user.getUsername().equals(ownerUsername)) {
            throw new IllegalArgumentException("this user: " + user.getUsername() + " not equal with in dto: " + ownerUsername);
        }
        return new Message(message, user, new Date());
    }
}
