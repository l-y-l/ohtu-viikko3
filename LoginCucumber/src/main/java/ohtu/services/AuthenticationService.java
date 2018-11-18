package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        //Username taken
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        //Username too short or contains invalid characters
        if (username.length() < 3 || Pattern.compile("[^a-z]+").matcher(username).find()) {
            return true;
        }
        //Password too short or only consists of letters
        if (password.length() < 8 || !Pattern.compile("[^a-z]+").matcher(password).find()) {
            return true;
        }
        return false;
    }
}
