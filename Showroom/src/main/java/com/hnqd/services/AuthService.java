/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services;

import com.hnqd.dto.AuthRequest;
import com.hnqd.dto.AuthResponse;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public interface AuthService {
    AuthResponse authenticate(AuthRequest request, HttpServletResponse response);
}
