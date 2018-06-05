package com.staxter.userrepository;

import com.staxter.exceptions.authorisation.UserAlreadyExistsException;

import java.util.Optional;

/**
 * Created by serge on 6/4/2018.
 */
public interface UserRepository {
    Optional<User> createUser(User user) throws UserAlreadyExistsException;
    void cleanUserList();
}