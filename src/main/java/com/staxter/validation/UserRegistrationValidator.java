package com.staxter.validation;

import com.staxter.enums.CodeEnumeration;
import com.staxter.userrepository.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserRegistrationValidator implements Validator {
    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }


    @Override
    public void validate(Object target, Errors errors) {
        User form = (User) target;
        validateFirstName(errors, form);

    }

    /**
     * validation first name
     * @param errors
     * @param form
     */
    private void validateFirstName(Errors errors, User form) {
        if (form.getFirstName() == null) {
            errors.rejectValue("firstName", CodeEnumeration.FIRST_NAME_NULL.name());
        } else if (form.getFirstName().length() > 50) {
            errors.rejectValue("firstName", CodeEnumeration.FIRST_NAME_LESS_LENGTH.name());
        }
    }
    //todo need add validation for last name, user name

}




