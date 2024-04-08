/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.dto;

import com.hnqd.pojo.Showroom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author DELL
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowroomResponse {
    private int id;
    private String name;
    private String location;
    
    public Showroom transferToShowroom() {
        Showroom showroom = new Showroom();
        showroom.setId(id);
        showroom.setName(name);
        showroom.setLocation(location);     
        return showroom;
    }
}
