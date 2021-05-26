package ru.pshiblo.alpha.repository;

import org.springframework.stereotype.Component;
import ru.pshiblo.alpha.entity.User;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Репозиторий пользователей
 */
@Component
public class RepositoryUsers implements Repository<User> {

    private final ConcurrentHashMap<String, User> users;

    public RepositoryUsers() {
        users = new ConcurrentHashMap<>();
    }

    @Override
    public Collection<User> getAll() {
        return Collections.unmodifiableCollection(users.values());
    }

    /**
     * Найти пользователя по юзернейму
     * @param username юзернейм пользователя
     * @return пользователя или null, если такого пользователя не существует
     */
    public User findUserByUsername(String username) {
        return users.getOrDefault(username, null);
    }

    @Override
    public void add(User user) {
        if (findUserByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("this user already exist! " + user.getUsername());
        }
        users.put(user.getUsername(), user);
    }

    @Override
    public long getCount() {
        return users.size();
    }

    @Override
    public void remove(User object) {
        users.remove(object.getUsername());
    }
}
