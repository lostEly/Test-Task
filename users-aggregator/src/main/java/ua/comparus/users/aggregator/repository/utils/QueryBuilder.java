package ua.comparus.users.aggregator.repository.utils;

import ua.comparus.users.aggregator.mapper.ModelMapper;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static java.util.Objects.requireNonNull;

public class QueryBuilder {
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

    public QueryBuilder appendFilters(ModelMapper<?> modelMapper, String dbName, Map<String, String> filters) throws IllegalArgumentException {
        if (filters == null || filters.isEmpty()) {
            return this;
        }
        StringJoiner whereClauseJoiner = new StringJoiner(" AND ");
        try {
            filters.forEach((key, value) -> whereClauseJoiner.add(
                    requireNonNull(modelMapper.getMappedColumnName(dbName, key)) + '=' + wrapBySingleQuote(value)));
            query.append(" WHERE ").append(whereClauseJoiner);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("No filtering column(s) found", e);
        }
        return this;
    }

    public String build() {
        return query.toString();
    }

    private String wrapBySingleQuote(String value) {
        return '\'' + value + '\'';
    }
}
