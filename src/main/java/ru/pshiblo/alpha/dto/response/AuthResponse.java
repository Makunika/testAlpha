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
public class AuthResponse {

    /**
     * Юзернейм пользователя
     */
    @JsonProperty("username")
    private String username;

    @JsonProperty("ws_uuid")
    private String UuidWs;

    /**
     * Создает DTO из пользователя
     * @param user пользователь
     * @param uuidWs - uuid веб сокета
     * @return DTO для пользователя
     */
    public static AuthResponse fromUser(User user, String uuidWs) {
        return new AuthResponse(user.getUsername(), uuidWs);
    }
}
