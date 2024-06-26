/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.controllers;

import com.hnqd.dto.UserResponse;
import com.hnqd.services.CloudinaryService;
import com.hnqd.services.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/{username}")
    public ResponseEntity<?> retrieve(@PathVariable String username) {
        try {
            UserResponse user = userService.getUserByUsername(username);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "User not found"
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    userService.getUserByUsername(username)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when get posts by username " + e.getMessage()
            );
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> list() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    userService.getUsers()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when get posts by userId"
            );
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> uploadAvatar(
            @RequestBody Map<String, String> params) {
        try {
            int userId = Integer.parseInt(params.get("userId"));
            UserResponse userFind = userService.getUserById(userId);
            if (userFind == null) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                        "Username not found!");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        userService.updateUser(params));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when register "
                    + e.getMessage()
            );
        }
    }
}
