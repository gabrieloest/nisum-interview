package com.nisum.interview.service.implementation;

import com.nisum.interview.exception.InvalidEmailFormatException;
import com.nisum.interview.exception.InvalidPasswordFormatException;
import com.nisum.interview.exception.UserNotFoundException;
import com.nisum.interview.model.User;
import com.nisum.interview.repository.UserRepository;
import com.nisum.interview.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Primary
public class BaseUserService implements UserService {

    private final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final String PASSWORD_REGEX = "^(?=.*[0-9]{2,})(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{4,}$";

    private final UserRepository userRepository;

    public BaseUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @see UserService#save(User)
     */
    @Override
    public User save(User user) {
        if(!isValidEmailFormat(user.getEmail())){
            throw new InvalidEmailFormatException(user.getEmail());
        }

        if(!isValidPasswordFormat(user.getPassword())) {
            throw new InvalidPasswordFormatException(user.getPassword());
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
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

    private boolean isValidEmailFormat(String email){
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPasswordFormat(String password){
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
