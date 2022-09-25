package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    public List<User> listUser();

    public User showIdUser(Long id);

    public void saveUser(User user);

    public void updateUser(User user);

    public void delete(Long id);

    User getUserByLogin(String username);
}

