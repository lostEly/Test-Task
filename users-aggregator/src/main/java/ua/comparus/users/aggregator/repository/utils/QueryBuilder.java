package ua.comparus.users.aggregator.repository.utils;

import ua.comparus.users.aggregator.mapper.ModelMapper;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class QueryBuilder {
    public static final String SPACE = " ";
    private final StringBuilder query = new StringBuilder("select ");

    public static QueryBuilder builder() {
        return new QueryBuilder();
    }

    public QueryBuilder appendColumns(List<String> columns) {
        StringJoiner columnsJoiner = new StringJoiner(",");
        for (String column : columns) {
            columnsJoiner.add(column);
        }
        query.append(columnsJoiner);
        return this;
    }

    public QueryBuilder appendTable(String table) {
        query.append(" FROM ").append(table);
        return this;
    }

    public QueryBuilder appendFilters(ModelMapper<?> modelMapper, String dbName, Map<String, String> filters) {
        if (filters == null || filters.isEmpty()) {
            return this;
        }
        StringJoiner whereClauseJoiner = new StringJoiner(" AND ");
        filters.forEach((key, value) -> whereClauseJoiner.add(modelMapper.getMappedColumnName(dbName, key) + "=" + value));
        query.append(SPACE).append("WHERE ").append(whereClauseJoiner);
        return this;
    }

    public String build() {
        return query.toString();
    }
}
