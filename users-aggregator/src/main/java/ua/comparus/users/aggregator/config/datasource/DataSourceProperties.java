package ua.comparus.users.aggregator.config.datasource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@ToString
@Component
@ConfigurationProperties
@PropertySource(value = "classpath:data-sources.yaml", factory = YamlPropertySourceFactory.class)
public class DataSourceProperties {

    List<DataSource> dataSources;

    @Setter
    @Getter
    @ToString
    public static class DataSource {
        String name;
        String strategy;
        String url;
        String table;
        String user;
        String password;
        Map<String, String> mapping;

    }
}