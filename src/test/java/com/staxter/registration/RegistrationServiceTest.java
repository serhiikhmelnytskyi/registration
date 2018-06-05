package com.staxter.registration;

import com.staxter.enums.CodeEnumeration;
import com.staxter.error.ErrorResponse;
import com.staxter.userrepository.User;
import com.staxter.userrepository.UserRepository;
import com.staxter.userrepository.UserService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
/**
 * Created by serge  on 6/5/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationServiceTest {

   @Autowired
   private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageSource messageSource;

    @After
    public void cleanResult(){
        userRepository.cleanUserList();
    }
    @Test
    public void tesCreatedUser()  {
        User  user = new User("testFirstName","testLastName","testUserName");
        String password = "password";
        ResponseEntity responseEntityR  = userService .createUser(user,password);
        assertThat(responseEntityR.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        User userR = (User)responseEntityR.getBody();
        assertThat(userR.getFirstName()).isEqualTo("testFirstName");
        assertThat(userR.getLastName()).isEqualTo("testLastName");
        assertThat(userR.getUserName()).isEqualTo("testUserName");
        assertThat(userR.getId()).isNotNull();

    }

    @Test
    public void tesAlreadyExistUser()  {
        User  user = new User("testFirstName","testLastName","testUserName");
        String password = "password";
        userService .createUser(user,password);
        ResponseEntity responseEntityR  = userService .createUser(user,password);
        assertThat(responseEntityR.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        ErrorResponse  errorResponse = (ErrorResponse) responseEntityR.getBody();
        assertThat(errorResponse.getCode()).isEqualTo(CodeEnumeration.USER_ALREADY_EXISTS.name());
        assertThat(errorResponse.getDescription()).isEqualTo(messageSource.getMessage("user.already.exists", null, null));
    }
}
