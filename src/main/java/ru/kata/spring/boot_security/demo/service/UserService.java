package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getAllUsers();

    void saveUser(User user, Set<Role> role);

    User getById(Long id);

    User update(User user, Set<Role> role);

    void delete(Long id);

    User showByEmail(String email);
}
