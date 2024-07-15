package ua.comparus.users.aggregator.repository.utils;

import org.springframework.stereotype.Component;
import ua.comparus.users.aggregator.config.datasource.DataSourceProperties;
import ua.comparus.users.aggregator.exception.DatasourceOperationException;
import ua.comparus.users.aggregator.mapper.ModelMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RepositoryUtil<T> {
    private final Map<String, Connection> connectionsCache;
    private final Map<String, DataSourceProperties.DataSource> dataSources;

    public RepositoryUtil(Map<String, Connection> connectionsCache, Map<String, DataSourceProperties.DataSource> dataSources) {
        this.connectionsCache = connectionsCache;
        this.dataSources = dataSources;
    }

    public List<T> runQuery(Map<String, String> filters, ModelMapper<T> modelMapper) {
        return dataSources.entrySet().stream()
                .map(entry -> {
                    try {
                        String dbName = entry.getKey();
                        Connection connection = connectionsCache.get(dbName);
                        String query = getSelectQueryBuilder(entry.getValue()).appendFilters(modelMapper, dbName, filters).build();
                        ResultSet resultSet = connection.createStatement().executeQuery(query);
                        return modelMapper.transformResultSetToModel(dbName, resultSet);
                    } catch (SQLException e) {
                        throw new DatasourceOperationException("Error executing query: " + e.getMessage(), e);
                    }
                }).flatMap(Collection::stream).collect(Collectors.toList());
    }

    private QueryBuilder getSelectQueryBuilder(DataSourceProperties.DataSource dataSource) {
        return QueryBuilder.builder()
                .appendColumns(dataSource.getMapping().values().stream().toList())
                .appendTable(dataSource.getTable());
    }
}
