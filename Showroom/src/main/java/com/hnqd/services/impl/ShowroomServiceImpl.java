/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services.impl;

import com.hnqd.dto.ShowroomResponse;
import com.hnqd.pojo.Showroom;
import com.hnqd.repositories.ShowroomRepository;
import com.hnqd.services.ShowroomService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class ShowroomServiceImpl implements ShowroomService{
    @Autowired
    private ShowroomRepository showroomRepo;

    @Override
    public void addShowroom(Showroom showroom) {
        showroomRepo.addShowroom(showroom);
    }

    @Override
    public List<ShowroomResponse> getShowrooms() {
        return showroomRepo.getShowrooms();
    }

    @Override
    public void deleteShowroomById(int id) {
        showroomRepo.deleteShowroomById(id);
    }

    @Override
    public ShowroomResponse updateShowroomById(int id, Showroom showroom) {
        return showroomRepo.updateShowroomById(id, showroom);
    }

    @Override
    public ShowroomResponse getShowroomById(int i) {
        return showroomRepo.getShowroomById(i);
    }
    
}
