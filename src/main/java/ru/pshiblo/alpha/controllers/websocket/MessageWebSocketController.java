package ru.pshiblo.alpha.controllers.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.pshiblo.alpha.consts.EndPoints;
import ru.pshiblo.alpha.dto.request.MessageDtoRequest;
import ru.pshiblo.alpha.dto.response.MessageDtoResponse;
import ru.pshiblo.alpha.entity.Message;
import ru.pshiblo.alpha.entity.User;
import ru.pshiblo.alpha.entity.WebSocketUser;
import ru.pshiblo.alpha.repository.RepositoryMessages;
import ru.pshiblo.alpha.repository.RepositoryUsers;
import ru.pshiblo.alpha.repository.RepositoryWebSocketUsers;

@Controller
public class MessageWebSocketController {

    private final RepositoryUsers repositoryUsers;
    private final RepositoryMessages repositoryMessages;
    private final RepositoryWebSocketUsers repositoryWebSocketUsers;

    public MessageWebSocketController(RepositoryUsers repositoryUsers, RepositoryMessages repositoryMessages, RepositoryWebSocketUsers repositoryWebSocketUsers) {
        this.repositoryUsers = repositoryUsers;
        this.repositoryMessages = repositoryMessages;
        this.repositoryWebSocketUsers = repositoryWebSocketUsers;
    }

    /**
     * Создание сообщения и отправка всем подписчикам, что оно создано через web sockets
     * @param messageDtoRequest сообщение
     * @return созданное сообщение
     */
    @MessageMapping(EndPoints.API_MESSAGE)
    @SendTo("/chat/messages")
    public MessageDtoResponse processMessage(@Payload MessageDtoRequest messageDtoRequest) {

        WebSocketUser webSocketUserByUuid = repositoryWebSocketUsers.findWebSocketUserByUuid(messageDtoRequest.getWsUUID());
        if (webSocketUserByUuid == null) {
            throw new IllegalArgumentException("ws_uuid not valid");
        }
        User user = webSocketUserByUuid.getUser();
        Message message = messageDtoRequest.toMessage(user);
        repositoryMessages.add(message);
        return MessageDtoResponse.fromMessage(message);
    }
}
