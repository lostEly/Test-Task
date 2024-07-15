package ua.comparus.users.aggregator.repository;

import org.springframework.stereotype.Repository;
import ua.comparus.users.aggregator.mapper.UserMapper;
import ua.comparus.users.aggregator.model.User;
import ua.comparus.users.aggregator.repository.utils.RepositoryUtil;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private final RepositoryUtil<User> repositoryUtil;
    private final UserMapper userMapper;

    public UserRepository(RepositoryUtil<User> repositoryUtil, UserMapper userMapper) {
        this.repositoryUtil = repositoryUtil;
        this.userMapper = userMapper;
    }

    public List<User> findAll(Map<String, String> filter) {
        return repositoryUtil.runQuery(filter, userMapper);
    }

    public List<User> findAll() {
        return findAll(null);
    }
}
