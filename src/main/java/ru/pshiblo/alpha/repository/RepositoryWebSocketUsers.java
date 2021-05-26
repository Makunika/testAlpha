package ru.pshiblo.alpha.repository;

import org.springframework.stereotype.Component;
import ru.pshiblo.alpha.entity.WebSocketUser;

import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Репозиторий для сущности вебсокет-юзер
 */
@Component
public class RepositoryWebSocketUsers implements Repository<WebSocketUser> {

    private final ConcurrentHashMap<String, WebSocketUser> webSocketUsers;

    public RepositoryWebSocketUsers() {
        webSocketUsers = new ConcurrentHashMap<>();
    }

    @Override
    public Collection<WebSocketUser> getAll() {
        return webSocketUsers.values();
    }

    /**
     * Найти сущность по uuid
     * @param uuid uuid вебсокета
     * @return сущность вебсокет-юзер или null, если не найдено
     */
    public WebSocketUser findWebSocketUserByUuid(String uuid) {
        return webSocketUsers.getOrDefault(uuid, null);
    }

    @Override
    public void add(WebSocketUser object) {
        webSocketUsers.put(object.getUUID(), object);
    }

    @Override
    public long getCount() {
        return webSocketUsers.size();
    }
}
