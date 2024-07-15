package ua.comparus.users.aggregator.config.datasource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Setter
@Getter
@ToString
public class DataSource {
    String name;
    String strategy;
    String url;
    String table;
    String user;
    String password;
    Map<String, String> mapping;

}