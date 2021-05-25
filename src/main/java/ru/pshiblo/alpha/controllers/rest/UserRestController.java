package ru.pshiblo.alpha.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pshiblo.alpha.consts.EndPoints;
import ru.pshiblo.alpha.dto.request.UserDtoRequest;
import ru.pshiblo.alpha.dto.response.UserDtoResponse;
import ru.pshiblo.alpha.entity.User;
import ru.pshiblo.alpha.repository.Repository;

@RestController
@RequestMapping(EndPoints.API_USER)
public class UserRestController {

    private final Repository repository;

    public UserRestController(Repository repository) {
        this.repository = repository;
    }

    /**
     * Авторизоваться, если такого пользователя нет в репозитории, то он создатся
     * @param userDtoRequest пользователь
     * @return пользователь
     */
    @PostMapping
    public ResponseEntity authUser(@RequestBody UserDtoRequest userDtoRequest) {
        User user = repository.findUser(userDtoRequest.getUsername());
        if (user == null) {
            repository.addUser(userDtoRequest.toUser());
        }

        return ResponseEntity.ok(UserDtoResponse.fromUser(userDtoRequest.toUser()));
    }
}
