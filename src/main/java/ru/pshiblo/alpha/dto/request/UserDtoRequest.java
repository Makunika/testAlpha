package ru.pshiblo.alpha.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.pshiblo.alpha.entity.User;

/**
 * DTO ответа для пользователя
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDtoRequest {

    /**
     * Юзернейм пользователя
     */
    @JsonProperty("username")
    private String username;

    /**
     * Создать пользователя из DTO
     * @return пользователь
     */
    public User toUser() {
        return new User(username);
    }
}
