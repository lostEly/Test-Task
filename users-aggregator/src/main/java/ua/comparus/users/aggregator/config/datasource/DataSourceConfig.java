package ua.comparus.users.aggregator.config.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean
    public Map<String, Connection> dataSources(DataSourceProperties dataSourceProperties) throws SQLException, ClassNotFoundException {
        Map<String, Connection> connectionsMap = new HashMap<>();

        for (DataSourceProperties.DataSource dataSource : dataSourceProperties.getDataSources()) {
            registerDriver(dataSource.getStrategy());
            Connection connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUser(), dataSource.getPassword());
            connectionsMap.put(dataSource.getName(), connection);
        }
        return connectionsMap;
    }

    private void registerDriver(String strategy) throws ClassNotFoundException {
        switch (strategy.toLowerCase()) {
            case "postgres":
                Class.forName("org.postgresql.Driver");
            case "mysql":
                Class.forName("com.mysql.cj.jdbc.Driver");
            default:
                throw new IllegalArgumentException("Unknown strategy: " + strategy);
        }
    }
}