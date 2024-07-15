package ua.comparus.users.aggregator.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.comparus.users.aggregator.api.UsersApiDelegate;
import ua.comparus.users.aggregator.model.User;
import ua.comparus.users.aggregator.repository.UserRepository;

import java.util.List;
import java.util.Map;

@Service
public class UserService implements UsersApiDelegate {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<List<User>> listUsers(Map<String, String> filters) {
        return ResponseEntity.ok(userRepository.findAll(filters));
    }
}
