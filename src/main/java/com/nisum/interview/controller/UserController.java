package com.nisum.interview.controller;

import com.nisum.interview.dto.UserDTO;
import com.nisum.interview.exception.EmailAlreadyInUseException;
import com.nisum.interview.exception.InvalidEmailFormatException;
import com.nisum.interview.exception.InvalidPasswordFormatException;
import com.nisum.interview.exception.UserNotFoundException;
import com.nisum.interview.model.User;
import com.nisum.interview.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User findById(@PathVariable("id") UUID id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@Valid @RequestBody UserDTO resource) throws ParseException {
        User userByEmail = userService.findByEmail(resource.getEmail());

        if(Objects.nonNull(userByEmail)){
            throw new EmailAlreadyInUseException(resource.getEmail());
        }

        User savedUser = userService.save(convertToEntity(resource));
        return convertToDto(savedUser);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@PathVariable( "id" ) String id, @Valid @RequestBody UserDTO resource) throws UserNotFoundException, ParseException {
        resource.setId(id);
        User user = convertToEntity(resource);
        User userByEmail = userService.findByEmail(resource.getEmail());

        if(Objects.nonNull(userByEmail) && !Objects.equals(userByEmail.getUserId(), user.getUserId())){
            throw new EmailAlreadyInUseException(resource.getEmail());
        }

        return convertToDto(userService.save(user));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        userService.deleteById(id);
    }

    private UserDTO convertToDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) throws ParseException {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDTO, User.class);

        if (userDTO.getId() != null) {
            User oldUser = userService.findById(UUID.fromString(userDTO.getId()));
            user.setUserId(oldUser.getUserId());
            user.setCreated(oldUser.getCreated());
            user.setLastLogin(oldUser.getLastLogin());
        }
        return user;
    }
}
