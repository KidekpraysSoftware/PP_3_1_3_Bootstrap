package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;
import java.util.Set;

public interface UserService {
    User getUserById(Long id);

   User getUserByMail(String mail);
    List<User> getAllUsers();

    void saveUser(User User);
    void edit(User User);

    void deleteUserById(Long id);




}
