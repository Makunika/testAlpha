package ru.pshiblo.alpha.repository;

import org.springframework.stereotype.Component;
import ru.pshiblo.alpha.entity.Token;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Репозиторий для токенов
 */
@Component
public class RepositoryToken implements Repository<Token> {

    private final ConcurrentHashMap<String, Token> tokens;

    public RepositoryToken() {
        tokens = new ConcurrentHashMap<>();
    }

    @Override
    public Collection<Token> getAll() {
        return tokens.values();
    }

    /**
     * Найти сущность по uuid
     * @param uuid uuid
     * @return сущность вебсокет-юзер или null, если не найдено
     */
    public Token findTokenByUuid(String uuid) {
        return tokens.getOrDefault(uuid, null);
    }

    @Override
    public void add(Token object) {
        tokens.put(object.getUUID(), object);
    }

    @Override
    public long getCount() {
        return tokens.size();
    }

    @Override
    public void remove(Token object) {
        tokens.remove(object.getUUID());
    }
}
