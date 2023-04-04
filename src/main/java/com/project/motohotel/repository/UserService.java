package com.project.motohotel.repository;

import com.project.motohotel.binding.UserAdd;
import com.project.motohotel.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    void addUser(UserAdd user);

    List<User> getAllUsers();

    void deleteUser(String id);

    User findById(String id);

    void editUser(String username, String password, String id);
}
