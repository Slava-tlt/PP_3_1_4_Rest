package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.ArrayList;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllUsers(Model model,
                               Principal principal,
                               @ModelAttribute("user") User user) {

        model.addAttribute("roleList", roleService.getAllRoles());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userByEmail", userService.showByEmail(principal.getName()));
        return "admin";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user,
                         @RequestParam("listRoles") ArrayList<Long> roles) {

        String email = user.getEmail();
        for(User person : userService.getAllUsers()){
            if(person.getEmail().equals(email)){
                return "isUser";
            }
        }

        userService.saveUser(user, roleService.findRoles(roles));
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam("listRoles") ArrayList<Long> roles) {

        userService.update(user, roleService.findRoles(roles));
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
