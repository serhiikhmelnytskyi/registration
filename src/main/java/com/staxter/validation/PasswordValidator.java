package com.staxter.validation;

import com.staxter.enums.CodeEnumeration;
import com.staxter.userrepository.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

/**
 * Created by serge on 6/5/2018.
 */
@Component
public class PasswordValidator  {

  public  Optional<String>  validatePassword(String password) {
        if (password == null || password.isEmpty()) {
          return   Optional.of(CodeEnumeration.PASSWORD_IS_NOT_EMPTY.name());
        }
        return Optional.empty();
    }
}
