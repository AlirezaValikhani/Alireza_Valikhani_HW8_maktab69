package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BaseRepository<T> {
    Integer insert(T t);
    T read(T t);
    List<T> readAll();
    Integer update(T t);
    Integer delete(T t);
    T mapTo(ResultSet resultSet);
    List<T> mapToList(ResultSet resultSet);
}
