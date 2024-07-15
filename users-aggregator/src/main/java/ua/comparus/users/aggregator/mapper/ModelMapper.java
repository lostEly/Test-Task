package ua.comparus.users.aggregator.mapper;

import ua.comparus.users.aggregator.config.datasource.DataSourceProperties;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class ModelMapper<T> {
    Map<String, DataSourceProperties.DataSource> dataSources;
    Map<String, Map<String, String>> objectMapping;

    public ModelMapper(Map<String, DataSourceProperties.DataSource> dataSources) {
        this.dataSources = dataSources;
        objectMapping = dataSources.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getMapping()));
    }

    public abstract List<T> transformResultSetToModel(String dbName, ResultSet resultSet) throws SQLException;

    public String getMappedColumnName(String dbName, String columnName) {
        return objectMapping.get(dbName).get(columnName);
    }
}
