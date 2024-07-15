package ua.comparus.users.aggregator.config.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class DataSourceConfig {

    @Bean
    public Map<String, Connection> connectionsCache(DataSourceProperties dataSourceProperties) throws SQLException {
        Map<String, Connection> connectionsMap = new HashMap<>();

        for (DataSource dataSource : dataSourceProperties.getDataSources()) {
            Connection connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUser(), dataSource.getPassword());
            connectionsMap.put(dataSource.getName(), connection);
        }
        return connectionsMap;
    }

    @Bean
    public Map<String, DataSource> dataSources(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.getDataSources().stream().collect(Collectors.toMap(DataSource::getName, Function.identity()));
    }
}