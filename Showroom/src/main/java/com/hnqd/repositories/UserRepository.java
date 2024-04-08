/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories;

import com.hnqd.dto.UserResponse;
import com.hnqd.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface UserRepository {

    UserResponse getUserByUsername(String username);

    UserResponse getUserById(int username);

    void addUser(User userRequest);

    UserResponse getUserByEmail(String email);

    List<UserResponse> getUsers();
    
    UserResponse updateUser(Map<String, String> params);

}
