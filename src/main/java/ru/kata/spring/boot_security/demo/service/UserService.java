package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    public List<User> getAllUsers();

    public void saveUser(User user, Set<Role> role);

    public User getById(Long id);

    public void update(User user, Set<Role> role);

    public void delete(Long id);

    public User showByEmail(String email);
}
