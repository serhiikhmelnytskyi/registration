package com.staxter.userrepository;

import com.staxter.enums.CodeEnumeration;
import com.staxter.error.ErrorResponse;
import com.staxter.error.ErrorService;
import com.staxter.exceptions.authorisation.UserAlreadyExistsException;
import com.staxter.validation.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

/**
 * Created by serge on 6/4/2018.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ErrorService errorService;

    /**
     * create user
     *
     * @param user
     * @return
     */
    @Override
    public ResponseEntity<?> createUser(User user) {
        Optional<ResponseEntity<?>>  errorResponse =  errorService.registrationValidationError(user);
        if(errorResponse.isPresent()){
            return errorResponse.get();
        }
        try {
            this.hashedPassword(user);
            this.generateUserId(user);
            userRepository.createUser(user);
        } catch (UserAlreadyExistsException ex) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(CodeEnumeration.USER_ALREADY_EXISTS.name(),
                    ex.getMessage()), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    /**
     * hashed  user password

     */
    public void hashedPassword(User user) {
        if (user == null ) {
            return;
        }
        user.setHashedPassword(passwordEncoder.encode(user.getPlainTextPassword()));
    }

    /**
     * generate id user
     *
     * @param user
     */
    public void generateUserId(User user) {
        if (user == null) {
            return;
        }
        user.setId(String.valueOf(Calendar.getInstance().getTimeInMillis() / 1000L));
    }

}
