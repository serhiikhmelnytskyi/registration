package com.staxter.registration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.staxter.controller.AuthorisationController;
import com.staxter.userrepository.User;
import com.staxter.userrepository.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by serge on 6/5/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {


    @Mock
    private UserService userService;
    @InjectMocks
    private AuthorisationController controller;
    @Before
    public void setup() {

    }

    @Test
    public void tesCreatedUser() throws Exception {

        User user = new User("232323232ADSAD23","testFirstName", "testLastName", "testUserName", "password");
        Map<String, String> params = new HashMap<String, String>();
        params.put("firstName", "testFirstName");
        params.put("lastName", "testLastName");
        params.put("userName", "testUserNaame");
        params.put("password", "password");
        ObjectMapper mapper = new ObjectMapper();
        User userM = mapper.convertValue(params, User.class);
        ResponseEntity responseEntityR = new ResponseEntity<User>(user, HttpStatus.CREATED);
        when(userService.createUser(userM)).thenReturn(responseEntityR);
        ResponseEntity response = controller.registration(params);
        verify(userService).createUser(userM);

    }
}