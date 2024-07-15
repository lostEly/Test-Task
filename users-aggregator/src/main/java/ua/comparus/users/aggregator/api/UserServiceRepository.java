package ua.comparus.users.aggregator.api;

import org.springframework.stereotype.Repository;
import ua.comparus.users.aggregator.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserServiceRepository {
    private final Map<String, Connection> dataSources;
    private final String FIND_ALL = "select * from users";

    public UserServiceRepository(Map<String, Connection> dataSources) {
        this.dataSources = dataSources;
    }

    public List<User> findAll() {
        return dataSources.values().stream()
                .map(connection -> {
                    try {
                        return connection.createStatement().executeQuery(FIND_ALL);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }).map(resultSet -> {
                    try {
                        return resultSet.unwrap(User.class);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
    }
}
