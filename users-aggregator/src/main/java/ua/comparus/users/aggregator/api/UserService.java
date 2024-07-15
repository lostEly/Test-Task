package ua.comparus.users.aggregator.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.comparus.users.aggregator.config.datasource.DataSourceConfig;
import ua.comparus.users.aggregator.model.User;

import java.util.List;

@Service
public class UserService implements UsersApiDelegate {

    private UserServiceRepository userServiceRepository;

    @Override
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(userServiceRepository.findAll());
    }
}
