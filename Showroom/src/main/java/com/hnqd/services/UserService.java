/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services;

import com.hnqd.dto.UserResponse;
import com.hnqd.pojo.User;
import com.hnqd.repositories.UserRepository;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author DELL
 */
public interface UserService extends UserDetailsService{
    UserResponse getUserByUsername(String username);
    void addUser(User user) throws Exception;
    List<UserResponse> getUsers();
    UserResponse getUserByEmail(String email);
}
