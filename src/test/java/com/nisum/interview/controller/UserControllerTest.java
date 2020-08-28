package com.nisum.interview.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nisum.interview.dto.UserDTO;
import com.nisum.interview.model.User;
import com.nisum.interview.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

    private static final String URL = "/users";
    public static final String ID = "16ab92c7-6895-4f9d-b7d5-34da11ad255b";
    public static final String NAME = "John Doe";
    public static final String EMAIL = "john@doe.com";
    public static final String PASSWORD = "$2a$10$yrt4b.iCJYFmVgl60BWVUe3V6otOgeU1xTyG8XoCxt2yA0gpfJ3dG";
    public static final boolean ACTIVE = true;
    public static final String CREATED = "2020-08-28T16:11:39.745";
    public static final String MODIFIED = "2020-08-28T16:11:39.745";
    public static final String LAST_LOGIN = "2020-08-28T16:11:39.745";
    public static final String TOKEN = "427fe75e-2eb2-42e7-8f51-c934eca901ce";

    private HttpHeaders headers;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @BeforeAll
    private void setUp() {
        headers = new HttpHeaders();
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void createTest() throws Exception {
        BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                    .content(getJsonPayload(ID, NAME, EMAIL, PASSWORD, ACTIVE, CREATED, MODIFIED, LAST_LOGIN, TOKEN))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .headers(headers))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(ID))
                .andExpect(jsonPath("$.data.name").value(NAME))
                .andExpect(jsonPath("$.data.email").value(EMAIL))
                .andExpect(jsonPath("$.data.password").value(PASSWORD))
                .andExpect(jsonPath("$.data.created").value(CREATED))
                .andExpect(jsonPath("$.data.modified").value(MODIFIED))
                .andExpect(jsonPath("$.data.lastLogin").value(LAST_LOGIN))
                .andExpect(jsonPath("$.data.token").value(TOKEN));
    }

    @Test
    void createInvalidEmailTest() throws Exception {
        BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .content(getJsonPayload(ID, NAME, "email@email", PASSWORD, ACTIVE, CREATED, MODIFIED, LAST_LOGIN, TOKEN))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("The email must have the following format 'aaaaaaa@domain.cl'")));
    }

    @Test
    void createInvalidPasswordTest() throws Exception {
        BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .content(getJsonPayload(ID, NAME, EMAIL, "password", ACTIVE, CREATED, MODIFIED, LAST_LOGIN, TOKEN))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("The password must contain lowercase letters, at least one uppercase letter and at least two digits.")));
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private User getMockUser() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User("Name", "email@email.com", bCryptPasswordEncoder.encode("Password1234"));
        user.setUserId(UUID.fromString(ID));
        return user;
    }

    private String getJsonPayload(String id, String name, String email, String password, boolean active, String created, String modified, String lastLogin, String token) throws JsonProcessingException, JsonProcessingException {

        UserDTO dto = new UserDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setEmail(email);
        dto.setPassword(password);
        dto.setActive(active);
        dto.setCreated(created);
        dto.setModified(modified);
        dto.setLastLogin(lastLogin);
        dto.setToken(token);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(dto);
    }
}