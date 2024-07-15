package ua.comparus.users.aggregator.integration.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.comparus.users.aggregator.model.User;
import ua.comparus.users.aggregator.repository.UserRepository;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryIntegrationTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindAll() {
        List<User> users = userRepository.findAll(null);
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertTrue(users.stream()
                .anyMatch(user -> user.getName().equals("first_name1")));
    }

    @Test
    public void testFindAll_WhenStringFilterApplied() {
        List<User> users = userRepository.findAll(Map.of("name", "name1"));
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertTrue(users.stream()
                .anyMatch(user -> user.getName().equals("name1")));
    }

    @Test
    public void testFindAll_WhenNumericFilterApplied() {
        List<User> users = userRepository.findAll(Map.of("id", "123"));
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertTrue(users.stream()
                .anyMatch(user -> user.getId() == 123));
    }

    @Test
    public void testFindAll_WhenWrongFilterApplied() {
        assertThrows(IllegalArgumentException.class, ()-> userRepository.findAll(Map.of("someColumn", "test")));
    }

}
