package com.staxter.error;

import com.staxter.enums.CodeEnumeration;
import com.staxter.validation.PasswordValidator;
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
    private MessageSource messageSource;
    @Autowired
    private PasswordValidator  passwordValidator;
    /**
     * get result registration error
     *
     * @param bindingResult
     * @return
     */
    public ResponseEntity<?> registrationValidationError(BindingResult bindingResult) {
        if (bindingResult == null) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        Optional<String> codeError = getBindingResult(bindingResult);
        return getErrorForFirstName(codeError);
    }

    /**
     * get  registration error for first name
     *
     * @param codeError
     * @return
     */
    public ResponseEntity<?> getErrorForFirstName(Optional<String> codeError) {
        if (!codeError.isPresent()) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        if (codeError.get().equals(CodeEnumeration.FIRST_NAME_NULL.name())) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(CodeEnumeration.FIRST_NAME_NULL.name(),
                    messageSource.getMessage("first.name.not.null", null, null)),
                    HttpStatus.BAD_REQUEST);

        } else if (codeError.get().equals(CodeEnumeration.FIRST_NAME_LESS_LENGTH.name())) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(CodeEnumeration.FIRST_NAME_LESS_LENGTH.name(),
                    messageSource.getMessage("first.name.less.length", null, null)),
                    HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(), HttpStatus.BAD_REQUEST);
    }

    /*
      * @param bindingResult forgot  password
     * @param result
     */
    public Optional<String> getBindingResult(BindingResult bindingResult) {
        if (bindingResult == null) {
            return Optional.empty();
        }
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            if ((error.getDefaultMessage() != null)) {
                return Optional.of(error.getDefaultMessage());
            } else {
                return Optional.of(error.getCode());
            }
        }
        return Optional.empty();
    }

    public  Optional<ResponseEntity<?>> registrationPasswordError(String password){
        Optional<String> codeError = passwordValidator.validatePassword(password);
        if(codeError.isPresent()){
            return Optional.of(new ResponseEntity<ErrorResponse>(new ErrorResponse(CodeEnumeration.PASSWORD_IS_NOT_EMPTY.name(),
                    messageSource.getMessage("password.not.null", null, null)),
                    HttpStatus.BAD_REQUEST));
        }
        return Optional.empty();
    }
}
