/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.controllers;

import com.hnqd.dto.AuthRequest;
import com.hnqd.dto.UserRequest;
import com.hnqd.dto.UserResponse;
import com.hnqd.pojo.User;
import com.hnqd.services.AuthService;
import com.hnqd.services.UserService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author DELL
 */
@Controller
@RequestMapping("/api/v1/auth")
@ComponentScan(basePackages = {
    "com.hnqd.dto",
    "com.hnqd.services"
})
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest userRequestDto) {
        try {
            UserResponse userFind = userService.getUserByUsername(userRequestDto.getUsername());
            if (userFind != null) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                        "Username has exited!");
            } else if (userService.getUserByEmail(userRequestDto.getEmail()) != null) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                        "Email has been used!");
            } else {
                User user = new User();
                user.setUsername(userRequestDto.getUsername());
                user.setPassword(userRequestDto.getPassword());
                user.setEmail(userRequestDto.getEmail());
                user.setImage(userRequestDto.getImage());
                user.setRoleName(userRequestDto.getRoleName());

                userService.addUser(user);
                return ResponseEntity.status(HttpStatus.OK).body(
                        "Register successfully!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when register "
                    + e.getMessage()
            );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest, HttpServletResponse response) {

        try {
            UserResponse userFind = userService.getUserByUsername(authRequest.getUsername());
            if (userFind == null) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                        "Username not found!");
            } else if (!passwordEncoder.matches(authRequest.getPassword(), userFind.getPassword())) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                        "Wrong password");
            } else {
                return ResponseEntity.ok(authService.authenticate(authRequest, response));
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Username not found: " + e.getMessage()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when register "
                    + e.getMessage()
            );
        }
    }
}
