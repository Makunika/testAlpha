package ru.pshiblo.alpha.repository;

import org.springframework.stereotype.Component;
import ru.pshiblo.alpha.entity.Message;
import ru.pshiblo.alpha.entity.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Репозиторий для хранения
 */
@Component
public class Repository {

    private final LinkedList<Message> messages;
    private final HashMap<String, User> users;

    public Repository() {
        messages = new LinkedList<>();
        users = new HashMap<>();
    }

    /**
     * Добавить новое сообщение
     * @param message сообщение
     */
    public synchronized void addMessage(Message message) {
        messages.add(message);
    }

    /**
     * Вернуть все сообщения
     * @return лист сообщений
     */
    public synchronized List<Message> getAllMessages() {
        return new LinkedList<>(messages);
    }

    /**
     * Найти пользователя
     * @param username юзернейм пользователя
     * @return пользователя или null, если такого пользователя не существует
     */
    public synchronized User findUser(String username) {
        return users.getOrDefault(username, null);
    }

    /**
     * Добавляет нового пользователя
     * @param user пользователь, его не должно быть уже в репозитории
     */
    public synchronized void addUser(User user) {
        if (findUser(user.getUsername()) != null) {
            throw new IllegalArgumentException("this user already exist! " + user.getUsername());
        }
        users.put(user.getUsername(), user);
    }


}
