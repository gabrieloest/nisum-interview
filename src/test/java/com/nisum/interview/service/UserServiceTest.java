package com.nisum.interview.service;

import com.nisum.interview.exception.InvalidEmailFormatException;
import com.nisum.interview.exception.InvalidPasswordFormatException;
import com.nisum.interview.exception.UserNotFoundException;
import com.nisum.interview.model.User;
import com.nisum.interview.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Test
    public void testSave() {

        BDDMockito.given(repository.save(Mockito.any(User.class)))
                .willReturn(getMockUser());
        User response = service.save(new User("Name", "email@email.com", "Password1234"));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        assertNotNull(response);
        assertEquals("Name", response.getName());
        assertEquals("email@email.com", response.getEmail());
        assertEquals(true, response.isActive());
        assertTrue(bCryptPasswordEncoder.matches("Password1234", response.getPassword()));

    }

    @Test
    public void testSaveInvalidEmail() {
        assertThrows(InvalidEmailFormatException.class, () -> service.save(new User("Name", "email@email", "Password1234")));
    }

    @Test
    public void testSaveInvalidPassword() {
        assertThrows(InvalidPasswordFormatException.class, () -> service.save(new User("Name", "email@email.com", "password")));
    }

    @Test
    public void testFindById() {
        BDDMockito.given(repository.findById(Mockito.any(UUID.class)))
                .willReturn(Optional.of(getMockUser()));
        User response = service.findById(UUID.fromString("16ab92c7-6895-4f9d-b7d5-34da11ad255b"));
        assertEquals("16ab92c7-6895-4f9d-b7d5-34da11ad255b", response.getUserId().toString());
        assertEquals("Name", response.getName());
    }

    @Test
    public void testFindByIdUserNotFound() {
        BDDMockito.given(repository.findById(Mockito.any(UUID.class)))
                .willReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> service.findById(UUID.fromString("16ab92c7-6895-4f9d-b7d5-34da11ad255b")));
    }

    private User getMockUser() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User("Name", "email@email.com", bCryptPasswordEncoder.encode("Password1234"));
        user.setUserId(UUID.fromString("16ab92c7-6895-4f9d-b7d5-34da11ad255b"));
        return user;
    }
}
