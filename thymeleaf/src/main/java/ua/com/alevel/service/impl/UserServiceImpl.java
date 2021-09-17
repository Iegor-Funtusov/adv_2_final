package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.entity.User;
import ua.com.alevel.repository.UserRepository;
import ua.com.alevel.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
        }
    }

    @Override
    public void update(User user) {
        if (userRepository.existsById(user.getId())) {
            userRepository.save(user);
        }
    }

    @Override
    public void delete(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
