package com.staxter.error;

import com.staxter.enums.CodeEnumeration;
import com.staxter.userrepository.User;
import com.staxter.validation.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;

/**
 * Created by serge on 6/5/2018.
 */
@Component
public class ErrorService {
    @Autowired
    private UserRegistrationValidator userRegistrationValidator;
    /**
     * get result registration error
     *
     * @param user
     * @return
     */
    public  Optional<ResponseEntity<?>> registrationValidationError( User user ) {
        if(user == null){
            return Optional.empty();
        }
        return userRegistrationValidator.validateUserData(user);
    }

}
