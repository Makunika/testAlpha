package ru.pshiblo.alpha.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pshiblo.alpha.consts.EndPoints;
import ru.pshiblo.alpha.dto.request.UserDtoRequest;
import ru.pshiblo.alpha.dto.response.AuthResponse;
import ru.pshiblo.alpha.entity.User;
import ru.pshiblo.alpha.entity.WebSocketUser;
import ru.pshiblo.alpha.repository.RepositoryUsers;
import ru.pshiblo.alpha.repository.RepositoryWebSocketUsers;

import java.util.UUID;

@RestController
@RequestMapping(EndPoints.API_USER)
public class UserRestController {

    private final RepositoryUsers repositoryUsers;
    private final RepositoryWebSocketUsers repositoryWebSocketUsers;

    public UserRestController(RepositoryUsers repositoryUsers, RepositoryWebSocketUsers repositoryWebSocketUsers) {
        this.repositoryUsers = repositoryUsers;
        this.repositoryWebSocketUsers = repositoryWebSocketUsers;
    }

    /**
     * Авторизоваться, если такого пользователя нет в репозитории, то он создатся
     * @param userDtoRequest пользователь
     * @return пользователь c uuid вебсокета
     */
    @PostMapping
    public ResponseEntity authUser(@RequestBody UserDtoRequest userDtoRequest) {
        User user = repositoryUsers.findUserByUsername(userDtoRequest.getUsername());
        if (user == null) {
            user = userDtoRequest.toUser();
            repositoryUsers.add(user);
        }

        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (repositoryWebSocketUsers.findWebSocketUserByUuid(uuid) != null);

        WebSocketUser webSocketUser = new WebSocketUser(user, uuid);
        repositoryWebSocketUsers.add(webSocketUser);

        return ResponseEntity.ok(AuthResponse.fromUser(userDtoRequest.toUser(), uuid));
    }
}
