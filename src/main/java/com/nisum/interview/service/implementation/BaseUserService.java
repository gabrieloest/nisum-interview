package com.nisum.interview.service.implementation;

import com.nisum.interview.exception.UserNotFoundException;
import com.nisum.interview.model.User;
import com.nisum.interview.repository.UserRepository;
import com.nisum.interview.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Primary
public class BaseUserService implements UserService {

    private final UserRepository userRepository;

    public BaseUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @see UserService#save(User)
     */
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * @see UserService#deleteById(UUID)
     */
    @Override
    public void deleteById(UUID userId) {
        userRepository.deleteById(userId);
    }

    /**
     * @see UserService#findById(UUID)
     */
    @Override
    public User findById(UUID id) throws UserNotFoundException {
        String idString = id.toString();
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(idString));
    }

    /**
     * @see UserService#findByEmail(String)
     */
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * @see UserService#findAll()
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
