package ru.pshiblo.alpha.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pshiblo.alpha.consts.EndPoints;
import ru.pshiblo.alpha.dto.request.AuthRequest;
import ru.pshiblo.alpha.dto.request.ExitRequest;
import ru.pshiblo.alpha.dto.response.AuthResponse;
import ru.pshiblo.alpha.entity.User;
import ru.pshiblo.alpha.entity.Token;
import ru.pshiblo.alpha.repository.RepositoryUsers;
import ru.pshiblo.alpha.repository.RepositoryToken;

import java.util.UUID;

@RestController
@RequestMapping(EndPoints.API_AUTH)
public class AuthRestController {

    private final RepositoryUsers repositoryUsers;
    private final RepositoryToken repositoryToken;

    public AuthRestController(RepositoryUsers repositoryUsers, RepositoryToken repositoryToken) {
        this.repositoryUsers = repositoryUsers;
        this.repositoryToken = repositoryToken;
    }

    /**
     * Авторизоваться, если такого пользователя нет в репозитории, то он создатся
     * @param authRequest пользователь
     * @return пользователь c uuid вебсокета
     */
    @PostMapping
    public ResponseEntity authUser(@RequestBody AuthRequest authRequest) {
        User user = repositoryUsers.findUserByUsername(authRequest.getUsername());
        if (user == null) {
            user = authRequest.toUser();
            repositoryUsers.add(user);
        }

        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (repositoryToken.findTokenByUuid(uuid) != null);

        Token token = new Token(user, uuid);
        repositoryToken.add(token);

        return ResponseEntity.ok(AuthResponse.fromToken(token));
    }

    @DeleteMapping
    public ResponseEntity exitUser(@RequestBody ExitRequest exitRequest) {
        Token token = repositoryToken.findTokenByUuid(exitRequest.getToken());
        if (token == null) {
            throw new IllegalArgumentException("token not valid!");
        }
        repositoryToken.remove(token);
        return ResponseEntity.ok().build();
    }
}
