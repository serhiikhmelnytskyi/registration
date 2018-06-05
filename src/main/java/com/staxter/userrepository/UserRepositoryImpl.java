package com.staxter.userrepository;

import com.staxter.exceptions.authorisation.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by serge on 6/5/2018.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    private static List<User> userList = new ArrayList<User>(300);
    @Autowired
    private MessageSource messageSource;

    @Override
    public Optional<User> createUser(User user) throws UserAlreadyExistsException {

        if (userList.size() == 0) {
            userList.add(user);
            return Optional.of(user);
        } else {
            Optional<User> result = userList.stream().filter(userE -> userE.equals(user)).findFirst();
            if (result.isPresent()) {
                throw new UserAlreadyExistsException(messageSource.getMessage("user.already.exists", null, null));
            }
        }
        return Optional.empty();
    }

    public  void cleanUserList(){
        userList.clear();
    }
}
