/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services;

import com.hnqd.dto.UserResponse;
import com.hnqd.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author DELL
 */
public interface UserService extends UserDetailsService {

    UserResponse getUserByUsername(String username);

    UserResponse getUserById(int username);

    void addUser(User user) throws Exception;

    List<UserResponse> getUsers();

    UserResponse getUserByEmail(String email);
    
    UserResponse updateUser(Map<String, String> params);
}
