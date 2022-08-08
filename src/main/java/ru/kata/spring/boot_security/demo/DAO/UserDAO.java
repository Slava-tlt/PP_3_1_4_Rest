package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    void saveUser(User user);

    User getById(Long id);

    void update(User user);

    void delete(Long id);

    User showByEmail(String email);

}
