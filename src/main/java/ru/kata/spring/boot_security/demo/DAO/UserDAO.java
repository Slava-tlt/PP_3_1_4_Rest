package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {

    public List<User> getAllUsers();

    void saveUser(User user);

    public User getById(Long id);

    public void update(User user);

    public void delete(Long id);

    public User showByEmail(String email);

}
