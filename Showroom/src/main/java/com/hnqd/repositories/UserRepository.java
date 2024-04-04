/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories;

import com.hnqd.dto.UserResponse;
import com.hnqd.pojo.User;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface UserRepository {

    UserResponse getUserByUsername(String username);

    void addUser(User userRequest);

    UserResponse getUserByEmail(String email);
    
    List<UserResponse> getUsers();

}
