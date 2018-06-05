package com.staxter.validation;

import com.staxter.enums.CodeEnumeration;
import com.staxter.error.ErrorResponse;
import com.staxter.userrepository.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserRegistrationValidator {
        @Autowired MessageSource messageSource;
    /**
     * validation
     * @param user
     */
    public Optional<ResponseEntity<?>> validateUserData(User user) {
        if (user == null) {
            return Optional.empty();
        }
        if (user.getFirstName() == null) {
            return this.getErrorResponseResult(CodeEnumeration.FIRST_NAME_NULL.name(),
                    messageSource.getMessage("first.name.not.null", null, null));

        } else if (user.getFirstName().length() > 50) {
            return this.getErrorResponseResult(CodeEnumeration.FIRST_NAME_LESS_LENGTH.name(),
                    messageSource.getMessage("first.name.less.length", null, null));

        } else if (user.getPlainTextPassword() == null || user.getPlainTextPassword().isEmpty()) {
            return this.getErrorResponseResult(CodeEnumeration.PASSWORD_IS_NOT_EMPTY.name(),
                    messageSource.getMessage("password.not.null", null, null));
        }
        return Optional.empty();
    }

    public  Optional<ResponseEntity<?>>  getErrorResponseResult(String code, String message){
        return Optional.of(new ResponseEntity<ErrorResponse>(new ErrorResponse(code, message), HttpStatus.CONFLICT));
    }

    //todo need add validation for last name, user name

}




