package ua.comparus.users.aggregator.repository;

import org.springframework.stereotype.Repository;
import ua.comparus.users.aggregator.mapper.ModelMapper;
import ua.comparus.users.aggregator.model.User;
import ua.comparus.users.aggregator.repository.utils.RepositoryUtil;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private final RepositoryUtil<User> repositoryUtil;
    private final ModelMapper<User> userMapper;

    public UserRepository(RepositoryUtil<User> repositoryUtil, ModelMapper<User> userMapper) {
        this.repositoryUtil = repositoryUtil;
        this.userMapper = userMapper;
    }

    public List<User> findAll(Map<String, String> filters) {
        return repositoryUtil.runQuery(filters, userMapper);
    }
}
