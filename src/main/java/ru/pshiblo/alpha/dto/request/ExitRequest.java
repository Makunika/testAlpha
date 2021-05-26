package ru.pshiblo.alpha.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Запрос на выход
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExitRequest {

    @JsonProperty("token")
    private String token;
}
