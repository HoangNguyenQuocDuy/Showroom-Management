/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services.impl;

//import com.hnqd.configs.JwtTokenUtil;
import com.hnqd.configs.JwtTokenUtil;
import com.hnqd.dto.AuthRequest;
import com.hnqd.dto.AuthResponse;
import com.hnqd.dto.UserResponse;
import com.hnqd.services.AuthService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
@ComponentScan(basePackages = {
    "com.hnqd.dto"
})
public class AuthServiceImp implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtUtil;
    @Autowired
    private UserServiceImpl userService;

    @Autowired

    @Override
    public AuthResponse authenticate(AuthRequest request, HttpServletResponse response) {
        try {
            UserResponse userResponse = userService.getUserByUsername(request.getUsername());

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            String jwtAccessToken = jwtUtil.generateToken(userResponse.transferUser());

            response.setHeader("Authentication", "Bearer " + jwtAccessToken);

            return AuthResponse.builder()
                    .accessToken(jwtAccessToken)
                    .username(userResponse.getUsername())
                    .build();
        } catch (Exception e) {
            Logger.getLogger(AuthServiceImp.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

}
