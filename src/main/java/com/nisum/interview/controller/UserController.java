package com.nisum.interview.controller;

import com.nisum.interview.exception.UserNotFoundException;
import com.nisum.interview.model.User;
import com.nisum.interview.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable("id") UUID id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User resource) {
        //Preconditions.checkNotNull(resource);
        return userService.save(resource);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable( "id" ) UUID id, @RequestBody User resource) throws UserNotFoundException {
        //Preconditions.checkNotNull(resource);
        userService.findById(resource.getUserId());
        userService.save(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        userService.deleteById(id);
    }
}
