package ua.comparus.users.aggregator.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.comparus.users.aggregator.config.datasource.DataSourceConfig;
import ua.comparus.users.aggregator.model.User;

import java.util.List;

@Service
public class UserService implements UsersApiDelegate {

    private final UserServiceRepository userServiceRepository;

    public UserService(UserServiceRepository userServiceRepository) {
        this.userServiceRepository = userServiceRepository;
    }

    @Override
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(userServiceRepository.findAll());
    }
}
