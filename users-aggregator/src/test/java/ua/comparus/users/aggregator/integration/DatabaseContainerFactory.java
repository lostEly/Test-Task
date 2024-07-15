package ua.comparus.users.aggregator.integration;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;
import ua.comparus.users.aggregator.config.datasource.DataSourceProperties;

public class DatabaseContainerFactory {

    public static GenericContainer<?> createDatabaseContainer(DataSourceProperties.DataSource dataSource) {
        return switch (dataSource.getStrategy()) {
            case "mysql" -> new MySQLContainer<>(DockerImageName.parse("mysql:latest"))
                    .withDatabaseName("testdb").withUsername(dataSource.getUser())
                    .withPassword(dataSource.getPassword())
                    .withAccessToHost(true);
            case "postgres" -> new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
                    .withDatabaseName("testdb").withUsername(dataSource.getUser())
                    .withPassword(dataSource.getPassword())
                    .withAccessToHost(true);
            default -> throw new IllegalArgumentException("Unsupported strategy: " + dataSource.getStrategy());
        };
    }
}
