/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.configs;

import com.auth0.jwt.algorithms.Algorithm;
import java.io.Serializable;
import java.util.Date;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.hnqd.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
/**
 *
 * @author DELL
 */
@Component
@PropertySource("classpath:application.properties")
public class JwtTokenUtil implements Serializable{
    @Autowired
    private Environment env;

    public String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 50*60*1000))
                .sign(Algorithm.HMAC256(env.getProperty("secret.key").getBytes()));
    }
}
