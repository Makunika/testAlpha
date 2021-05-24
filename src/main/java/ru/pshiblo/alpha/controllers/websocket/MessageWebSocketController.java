package ru.pshiblo.alpha.controllers.websocket;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.pshiblo.alpha.dto.request.MessageDtoRequest;
import ru.pshiblo.alpha.entity.Message;
import ru.pshiblo.alpha.entity.User;
import ru.pshiblo.alpha.repository.Repository;

@Controller
public class MessageWebSocketController {

    private final Repository repository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public MessageWebSocketController(Repository repository, SimpMessagingTemplate simpMessagingTemplate) {
        this.repository = repository;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/messages")
    public void processMessage(@Payload MessageDtoRequest messageDtoRequest) {
        User user = repository.findUser(messageDtoRequest.getOwnerUsername());
        if (user == null) {
            throw new IllegalArgumentException("user is null because in message userOwner not exist!");
        }
        Message message = messageDtoRequest.toMessage(user);
        repository.addMessage(message);
        simpMessagingTemplate.convertAndSendToUser("main", "/queue",
                message);
    }
}
