package ru.pshiblo.alpha.repository;

import java.util.Collection;
import java.util.List;

/**
 * Интерфейс для репозиториев
 * @param <T> класс в качестве объекта для хранения
 */
public interface Repository<T> {

    /**
     * Получить все значения
     * @return лист объектов
     */
    Collection<T> getAll();

    /**
     * Добавить новый объект
     * @param object объект
     */
    void add(T object);

    /**
     * Вернуть размер репозиторий
     * @return размер репозитория
     */
    long getCount();
}
