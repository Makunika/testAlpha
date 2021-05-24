package ru.pshiblo.alpha.dto.response;

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
public class UserDtoResponse {

    /**
     * Юзернейм пользователя
     */
    @JsonProperty("username")
    private String username;

    /**
     * Создает DTO из пользователя
     * @param user пользователь
     * @return DTO для пользователя
     */
    public static UserDtoResponse fromUser(User user) {
        return new UserDtoResponse(user.getUsername());
    }
}
