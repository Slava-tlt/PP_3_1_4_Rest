package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.DAO.UserDAO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user, Set<Role> role) {
        user.setRoles(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public User getById(Long id) {
        return userDAO.getById(id);
    }

    @Override
    @Transactional
    public User update(User user, Set<Role> role) {
        String password = getById(user.getId()).getPassword();
        if (user.getPassword().isEmpty()){
            user.setPassword(password);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setRoles(role);
        userDAO.update(user);
        return user;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDAO.delete(id);
    }

    @Override
    public User showByEmail(String email) {

        return userDAO.showByEmail(email);
    }
}
