package ua.comparus.users.aggregator.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "data-sources")
@PropertySource(value = "classpath:data-sources.yaml", factory = YamlPropertySourceFactory.class)
public class DataSourceProperties {

    List<DataSource> dataSources;

    public List<DataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<DataSource> dataSources) {
        this.dataSources = dataSources;
    }

    static class DataSource {
        String name;
        String strategy;
        String url;
        String table;
        String user;
        String password;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStrategy() {
                return strategy;
            }

            public void setStrategy(String strategy) {
                this.strategy = strategy;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTable() {
                return table;
            }

            public void setTable(String table) {
                this.table = table;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            @Override
        public String toString() {
            return "DataSource{" +
                    "name='" + name + '\'' +
                    ", strategy='" + strategy + '\'' +
                    ", url='" + url + '\'' +
                    ", table='" + table + '\'' +
                    ", user='" + user + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DataSourceProperties{" +
                "dataSources=" + dataSources +
                '}';
    }
}