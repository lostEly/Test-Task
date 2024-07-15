package ua.comparus.users.aggregator.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ModelMapper<T> {
    List<T> transformResultSet(String dbName, ResultSet resultSet) throws SQLException;
}
