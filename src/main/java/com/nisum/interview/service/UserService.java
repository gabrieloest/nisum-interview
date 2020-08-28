package com.nisum.interview.service;

import com.nisum.interview.exception.UserNotFoundException;
import com.nisum.interview.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    /**
     * Method that save a user.
     *
     * @author Gabriel Oest
     * @since 28/08/2020
     *
     * @param user
     * @return <code>User</code> object
     */
    User save(User user);

    /**
     * Method that remove a user by an id.
     *
     * @author Gabriel Oest
     * @since 28/08/2020
     *
     * @param userId
     */
    void deleteById(UUID userId);

    /**
     * Method that find a user by an id.
     *
     * @author Gabriel Oest
     * @since 28/08/2020
     *
     * @param id
     * @return <code>Optional<User></code> object
     */
    User findById(UUID id) throws UserNotFoundException;

    /**
     * Method that find one user by an email.
     *
     * @author Gabriel Oest
     * @since 28/08/2020
     *
     * @param email
     * @return <code>List<User></code> object
     */
    User findByEmail(String email);

    /**
     * Method that find all users.
     *
     * @author Gabriel Oest
     * @since 28/08/2020
     *
     * @return <code>List<User></code> object
     */
    List<User> findAll();
}
