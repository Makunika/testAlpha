package ru.pshiblo.alpha.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.pshiblo.alpha.entity.Message;
import ru.pshiblo.alpha.entity.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DTO ответа для сообщения
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDtoResponse {

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
     * Дата создания сообщения
     */
    @JsonProperty("date")
    private String date;

    /**
     * Создать DTO из сообщения
     * @param message сообщение
     * @return DTO для сообщения
     */
    public static MessageDtoResponse fromMessage(Message message) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return new MessageDtoResponse(message.getMessage(), message.getOwner().getUsername(), dateFormat.format(message.getDate()));
    }
}
