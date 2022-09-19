package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    void add(User user);

    List<User> getAll();

    User getUser(int id);

    void update(User user);

    void delete(int id);

    User getUserByLogin(String username);
}

