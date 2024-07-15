package ua.comparus.users.aggregator.repository.utils;

import org.springframework.stereotype.Component;
import ua.comparus.users.aggregator.config.datasource.DataSourceProperties;
import ua.comparus.users.aggregator.mapper.ModelMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RepositoryUtil<T> {
    private final Map<String, Connection> dataSources;
    DataSourceProperties dataSourceProperties;
    Map<String, QueryBuilder> queries = new HashMap<>();

    public RepositoryUtil(DataSourceProperties dataSourceProperties, Map<String, Connection> dataSources) {
        this.dataSourceProperties = dataSourceProperties;
        this.dataSources = dataSources;
        initializeSelectQueryBuilders();
    }

    public QueryBuilder getSelectQueryBuilderByDbName(String dbName) {
        return queries.get(dbName);
    }

    public List<T> runQuery(Map<String, String> filter, ModelMapper<T> modelMapper) {
        return dataSources.keySet().stream()
                .map(dbName -> {
                    try {
                        Connection connection = dataSources.get(dbName);
                        String query = this.getSelectQueryBuilderByDbName(dbName).appendFilters(modelMapper, dbName, filter).build();
                        var rs = connection.createStatement().executeQuery(query);
                        return modelMapper.transformResultSetToModel(dbName, rs);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }).flatMap(Collection::stream).collect(Collectors.toList());
    }

    private void initializeSelectQueryBuilders() {
        for (DataSourceProperties.DataSource dataSource : dataSourceProperties.getDataSources()) {
            QueryBuilder queryBuilder = QueryBuilder.builder()
                    .appendColumns(dataSource.getMapping().values().stream().toList())
                    .appendTable(dataSource.getTable());
            queries.put(dataSource.getName(), queryBuilder);
        }
    }


}
