package ru.pshiblo.alpha.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pshiblo.alpha.consts.EndPoints;
import ru.pshiblo.alpha.dto.request.MessageDtoRequest;
import ru.pshiblo.alpha.dto.response.MessageDtoResponse;
import ru.pshiblo.alpha.dto.response.PageResponse;
import ru.pshiblo.alpha.entity.User;
import ru.pshiblo.alpha.repository.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(EndPoints.API_MESSAGE)
public class MessageRestController {

    private final Repository repository;

    public MessageRestController(Repository repository) {
        this.repository = repository;
    }


    /**
     * Получить все сообщений
     * @param size размер выборки, не обязательно
     * @param offset размер оффсета для выборки, не обязательно
     * @return ответ с массивом
     */
    @GetMapping
    public ResponseEntity getAllMessages(@RequestParam(required = false) Integer size,
                                                                   @RequestParam(defaultValue = "0") Integer offset) {
        if (size == null) {
            return ResponseEntity.ok(repository.getAllMessages().stream()
                    .map(MessageDtoResponse::fromMessage)
                    .collect(Collectors.toList()));
        } else {
            List<MessageDtoResponse> messageDtoResponses = repository.getAllMessages(size, offset).stream()
                    .map(MessageDtoResponse::fromMessage)
                    .collect(Collectors.toList());

            long countMessages = repository.getCountMessages();
            long nextOffset = countMessages < offset + size ? -1 : offset + size;
            boolean canNext = nextOffset != -1;
            long nextSize = countMessages < nextOffset + size ? nextOffset + size - countMessages : size;

            PageResponse<MessageDtoResponse> pageResponse = new PageResponse<>(messageDtoResponses,
                    countMessages,
                    nextOffset,
                    nextSize,
                    canNext);

            return ResponseEntity.ok(pageResponse);
        }

    }
}
