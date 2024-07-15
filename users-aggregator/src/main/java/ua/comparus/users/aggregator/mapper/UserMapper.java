package ua.comparus.users.aggregator.mapper;

import org.springframework.stereotype.Component;
import ua.comparus.users.aggregator.config.datasource.DataSource;
import ua.comparus.users.aggregator.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UserMapper extends ModelMapper<User> {

    public UserMapper(Map<String, DataSource> dataSources) {
        super(dataSources);
    }

    public List<User> transformResultSetToModel(String dbName, ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            Map<String, String> dataSource = dataSources.get(dbName).getMapping();
            user.setId(resultSet.getLong(dataSource.get("id")));
            user.setName(resultSet.getString(dataSource.get("name")));
            user.setUsername(resultSet.getString(dataSource.get("username")));
            user.setSurname(resultSet.getString(dataSource.get("surname")));
            users.add(user);
        }
        return users;
    }
}
