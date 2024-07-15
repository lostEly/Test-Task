package ua.comparus.users.aggregator.integration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import ua.comparus.users.aggregator.config.datasource.DataSourceProperties;

@Configuration
public class DataSourceStartupConfig {

    @Autowired
    DataSourceProperties dataSourceProperties = new DataSourceProperties();

    @PostConstruct
    public void init() {
        dataSourceProperties.getDataSources()
                .forEach(dataSource ->
                {
                    JdbcDatabaseContainer<?> container = (JdbcDatabaseContainer<?>) DatabaseContainerFactory.createDatabaseContainer(dataSource);
                    container.start();
                    dataSource.setUrl(container.getJdbcUrl());
                    JdbcDatabaseDelegate containerDelegate = new JdbcDatabaseDelegate(container, "");
                    ScriptUtils.runInitScript(containerDelegate, "DsScripts/" + dataSource.getName() + "/user-table-init.sql");
                });
    }
}
