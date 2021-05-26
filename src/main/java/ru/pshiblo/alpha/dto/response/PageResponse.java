package ru.pshiblo.alpha.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * Ответ в виде страницы
 * @param <T> - класс, который будет в массиве
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageResponse<T> {

    /**
     * Массив объектов
     */
    @JsonProperty("list")
    private List<T> list;

    /**
     * всего элементов
     */
    @JsonProperty("all_count")
    private Long allCount;

    /**
     * Следующий оффсет
     */
    @JsonProperty("next_offset")
    private Long nextOffset;

    /**
     * Следующий размер
     */
    @JsonProperty("next_size")
    private Long nextSize;

    /**
     * Возможно ли отдавать запрос на следующую страницу
     */
    @JsonProperty("can_next")
    private Boolean canNext;

}
