package service;

import java.util.List;

public interface BaseService<T> {
    Integer insert(T t);
    T read(T t);
    List<T> readAll();
    Integer update(T t);
    Integer delete(T t);
}
