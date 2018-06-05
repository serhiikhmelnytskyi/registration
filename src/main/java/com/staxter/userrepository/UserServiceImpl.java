package com.staxter.userrepository;

import com.staxter.enums.CodeEnumeration;
import com.staxter.error.ErrorResponse;
import com.staxter.error.ErrorService;
import com.staxter.exceptions.authorisation.UserAlreadyExistsException;
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
     * @param password
     * @return
     */
    @Override
    public ResponseEntity<?> createUser(User user, String password) {
        Optional<ResponseEntity<?>>  optionalError = errorService.registrationPasswordError(password);
        if(optionalError.isPresent()){
            return optionalError.get();
        }
        try {
            this.hashedPassword(user, password);
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
     *
     * @param user
     * @param password
     */
    public void hashedPassword(User user, String password) {
        if (user == null || password == null) {
            return;
        }
        user.setHashedPassword(passwordEncoder.encode(password));
        user.setPlainTextPassword(password);
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
