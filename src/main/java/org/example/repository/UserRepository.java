package org.example.repository;

import org.example.model.User;

import java.util.*;

public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public Collection<User> getAllUsers() {
        return users;
    }

    public Optional<User> findUserByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        for (User user : users) {
            if (user.getLogin().equals(login)||user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
    public void addUser(User user) {
        this.users.add(user);
    }

}
