package ru.pshiblo.alpha.repository;

import org.springframework.stereotype.Component;
import ru.pshiblo.alpha.entity.Message;
import ru.pshiblo.alpha.entity.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Репозиторий для хранения
 */
@Component
public class Repository {

    private final LinkedList<Message> messages;
    private final ConcurrentHashMap<String, User> users;

    public Repository() {
        messages = new LinkedList<>();
        users = new ConcurrentHashMap<>();
    }

    /**
     * Добавить новое сообщение
     * @param message сообщение
     */
    public void addMessage(Message message) {
        messages.add(message);
    }

    /**
     * Вернуть все сообщения
     * @return лист сообщений
     */
    public List<Message> getAllMessages() {
        return Collections.unmodifiableList(messages);
    }

    /**
     * Вернуть сообщения с размером выборки и оффестом выборки
     * @param size размер выборки
     * @param offset оффсет выборки
     * @return лист сообщений
     */
    public List<Message> getAllMessages(int size, int offset) {
        return Collections.unmodifiableList(messages).subList(offset, offset + size);
    }

    /**
     * Вернуть количество всех сообщений
     * @return количество сообщений
     */
    public long getCountMessages() {
        return messages.size();
    }

    /**
     * Найти пользователя
     * @param username юзернейм пользователя
     * @return пользователя или null, если такого пользователя не существует
     */
    public User findUser(String username) {
        return users.getOrDefault(username, null);
    }

    /**
     * Добавляет нового пользователя
     * @param user пользователь, его не должно быть уже в репозитории
     */
    public void addUser(User user) {
        if (findUser(user.getUsername()) != null) {
            throw new IllegalArgumentException("this user already exist! " + user.getUsername());
        }
        users.put(user.getUsername(), user);
    }


}
