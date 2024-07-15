package ua.comparus.users.aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class UsersAggregatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersAggregatorApplication.class, args);
    }

}
