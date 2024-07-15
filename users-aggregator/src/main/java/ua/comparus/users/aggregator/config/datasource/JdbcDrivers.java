package ua.comparus.users.aggregator.config.datasource;

public enum JdbcDrivers {
    POSTGRES("org.postgresql.Driver"), MYSQL("com.mysql.cj.jdbc.Driver");

    JdbcDrivers(String jdbcDriver) {
        registerDriver(jdbcDriver);
    }

    void registerDriver(String jdbcDriver) {
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
