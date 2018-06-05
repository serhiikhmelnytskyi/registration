package com.staxter.controller;

import com.staxter.error.ErrorService;
import com.staxter.userrepository.User;
import com.staxter.userrepository.UserService;
import com.staxter.validation.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Created by serge on 6/4/2018.
 */

@RestController
public class AuthorisationController {

    @Autowired
    private UserService userService;
    @Autowired
    private ErrorService errorService;
    @Autowired
    private UserRegistrationValidator userRegistrationValidator;

    @InitBinder("userRegistration")
    public void initBinderUserRegistration(WebDataBinder binder) {
        binder.addValidators(userRegistrationValidator);
    }

    @RequestMapping(value = "/userservice/register", method = RequestMethod.PUT)
    public ResponseEntity<?> registration(@RequestParam(value = "password", required = true) String password,
                                          @ModelAttribute("userRegistration") @Valid User user, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return userService.createUser(user, password);
        } else {
            return errorService.registrationValidationError(bindingResult);
        }

    }

}
