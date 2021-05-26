package ru.pshiblo.alpha.repository;

import org.springframework.stereotype.Component;
import ru.pshiblo.alpha.entity.Message;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Репозиторий сообщений
 */
@Component
public class RepositoryMessages implements Repository<Message> {

    private final LinkedList<Message> messages;

    public RepositoryMessages() {
        messages = new LinkedList<>();
    }

    @Override
    public Collection<Message> getAll() {
        return Collections.unmodifiableList(messages);
    }

    /**
     * Вернуть сообщения с размером выборки и оффестом выборки
     * @param size размер выборки
     * @param offset оффсет выборки
     * @return лист сообщений
     */
    public Collection<Message> getAll(int size, int offset) {
        return Collections.unmodifiableList(messages).subList(offset, offset + size);
    }

    @Override
    public void add(Message object) {
        messages.add(object);
    }

    @Override
    public long getCount() {
        return messages.size();
    }

    @Override
    public void remove(Message object) {
        messages.remove(object);
    }
}
