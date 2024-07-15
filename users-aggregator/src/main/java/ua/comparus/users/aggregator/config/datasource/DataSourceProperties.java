package ua.comparus.users.aggregator.config.datasource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@ToString
@Component
@ConfigurationProperties
@PropertySource(value = "classpath:data-sources.yaml", factory = YamlPropertySourceFactory.class)
public class DataSourceProperties {

    List<DataSource> dataSources;

}