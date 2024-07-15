package ua.comparus.users.aggregator.repository.utils;

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

    public QueryBuilder appendFilters(Map<String, String> filters) {
        if (filters == null || filters.isEmpty()) {
            return this;
        }
        var sj = new StringJoiner(" AND ");
        filters.entrySet().stream().forEach(entry -> sj.add(entry.getKey() + "=" + entry.getValue()));
        query.append(SPACE).append("WHERE ").append(sj);
        return this;
    }

    public String build() {
        return query.toString();
    }
}
