package com.staxter.userrepository;

import org.springframework.http.ResponseEntity;

/**
 * Created by serge on 6/5/2018.
 */
public interface UserService {
    ResponseEntity<?> createUser(User user, String password);
}
