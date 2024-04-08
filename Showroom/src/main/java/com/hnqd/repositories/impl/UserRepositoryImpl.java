/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories.impl;

import com.hnqd.dto.UserResponse;
import com.hnqd.pojo.User;
import com.hnqd.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public UserResponse getUserByUsername(String username) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE username=:username");
        q.setParameter("username", username);

        User user = null;
        UserResponse userResponse = null;
        try {
            user = (User) q.getSingleResult();
            userResponse = mapToUserResponse(user);
            return userResponse;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void addUser(User ur) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(ur);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE email=:username");
        q.setParameter("username", email);

        User user = null;
        UserResponse userResponse = null;
        try {
            user = (User) q.getSingleResult();
            userResponse = mapToUserResponse(user);
            return userResponse;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<UserResponse> getUsers() {
        Session s = factory.getObject().getCurrentSession();

        List<UserResponse> userResponseList = new ArrayList<>();

        for (User user : s.createQuery("FROM User", User.class).list()) {
            UserResponse userResponse = mapToUserResponse(user);
            userResponseList.add(userResponse);
        }

        return userResponseList;
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse userRes = new UserResponse();
        userRes.setUserId(user.getId());
        userRes.setImage(user.getImage());
        userRes.setUsername(user.getUsername());
        userRes.setEmail(user.getEmail());
        userRes.setPassword(user.getPassword());
        userRes.setRoleName(user.getRoleName());

        return userRes;
    }

    @Override
    public UserResponse getUserById(int userId) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE id=:userId");
        q.setParameter("id", userId);

        User user = null;
        UserResponse userResponse = null;
        try {
            user = (User) q.getSingleResult();
            userResponse = mapToUserResponse(user);
            return userResponse;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UserResponse updateUser(Map<String, String> params) {
        Session s = factory.getObject().getCurrentSession();
        User user = s.get(User.class, params.get("userId"));
        
        if (user != null) {
            String image = params.get("image");
            if (image != null && !image.isEmpty()) {
                user.setImage(image);
            }
            
            s.update(user);
            
            return mapToUserResponse(user);
        }
        
        return null;
    }
}
