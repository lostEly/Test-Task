package ua.comparus.users.aggregator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import ua.comparus.users.aggregator.config.datasource.DataSourceProperties;
import javax.validation.Valid;

@SpringBootApplication
@EnableConfigurationProperties
public class UsersAggregatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersAggregatorApplication.class, args);
    }

}

@Component
class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private DataSourceProperties dataSourceProperties;
    private static final Logger LOG =
            LoggerFactory.getLogger(AppStartupRunner.class);

    public static int counter;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(dataSourceProperties);
    }
}