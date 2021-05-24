package ru.pshiblo.alpha.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pshiblo.alpha.consts.EndPoints;
import ru.pshiblo.alpha.dto.request.MessageDtoRequest;
import ru.pshiblo.alpha.dto.response.MessageDtoResponse;
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

    @GetMapping
    public ResponseEntity<List<MessageDtoResponse>> getAllMessages() {
        return ResponseEntity.ok(repository.getAllMessages().stream()
                .map(MessageDtoResponse::fromMessage)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity createMessage(@RequestBody MessageDtoRequest messageDtoRequest) {
        User user = repository.findUser(messageDtoRequest.getOwnerUsername());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        repository.addMessage(messageDtoRequest.toMessage(user));
        return ResponseEntity.ok().build();
    }
}
