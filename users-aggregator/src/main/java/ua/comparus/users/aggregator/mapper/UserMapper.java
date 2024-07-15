package ua.comparus.users.aggregator.mapper;

import org.springframework.stereotype.Component;
import ua.comparus.users.aggregator.config.datasource.DataSourceProperties;
import ua.comparus.users.aggregator.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UserMapper implements ModelMapper<User> {
    DataSourceProperties dataSourceProperties;

    public UserMapper(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    public List<User> transformResultSet(String dbName, ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        User user = new User();
        while (resultSet.next()) {
            Map<String, String> dataSource = dataSourceProperties.getDataSources().stream().filter(ds -> ds.getName().equals(dbName)).findFirst().get().getMapping();
            user.setId(resultSet.getLong(dataSource.get("id")));
            user.setName(resultSet.getString(dataSource.get("name")));
            user.setUsername(resultSet.getString(dataSource.get("username")));
            user.setSurname(resultSet.getString(dataSource.get("surname")));
            users.add(user);
        }
        return users;
    }
}
