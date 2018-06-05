package com.staxter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.staxter.error.ErrorService;
import com.staxter.userrepository.User;
import com.staxter.userrepository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * Created by serge on 6/4/2018.
 */

@RestController
public class AuthorisationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userservice/register", method = RequestMethod.PUT)
    public ResponseEntity<?> registration(@RequestParam Map<String, String> params) {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.convertValue(params, User.class);
        return userService.createUser(user);

    }

}
