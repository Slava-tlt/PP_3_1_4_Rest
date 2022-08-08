package ru.kata.spring.boot_security.demo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;



@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final UserService userService;

    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> listUsers() {

        final List<User> allUsers = userService.getAllUsers();
        return allUsers != null && !allUsers.isEmpty()
                ? new ResponseEntity<>(allUsers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/admin/email")
    public ResponseEntity<User> getUserEmail(Principal principal){
        User user = userService.showByEmail(principal.getName());
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    @GetMapping("/admin/{id}")
    public ResponseEntity<User> getUser(@PathVariable(name = "id") long id) {
       final User user = userService.getById(id);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin")
    public ResponseEntity<?> newUser(@RequestBody User user) {

        userService.saveUser(user, user.getRoles());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/admin")
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        final User person =  userService.update(user, user.getRoles());

        return person != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    @DeleteMapping("admin/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {

        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
