package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    RoleRepository roleRepository;
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String userShow(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "admin";
    }


    @GetMapping(value = "/addUser")
    public String addUserPage(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", userService.getAllRoles());
        return "addUser";
    }


    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user) {
        System.out.println("post addUser start");
        userService.saveUser(user);
        System.out.println("post addUser successfully");
        return "redirect:/admin";
    }


    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        User user = userService.getUserById(id);
        user.setPassword("");
        model.addAttribute("user", user);
        model.addAttribute("allroles", userService.getAllRoles());
        return "editUser";
    }


    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    //добавление базового пользователя без админки (убрать RequestMapping)
//    @GetMapping("/default")
//    public String base() {
//        Set<Role> roleset = new HashSet<>();
//        Role role = new Role(1L, "ROLE_ADMIN");
//        roleRepository.save(role);
//        roleset.add(role);
//        User user = new User(
//                  1L
//                , "admin"
//                , "admin"
//                , "Dmitry"
//                , "Karpenko"
//                , "KidekpraysSoftware@gmail.com"
//                , roleset);
//        userService.saveUser(user);
//        return "/index";
//    }

}
