/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories;

import com.hnqd.dto.ShowroomResponse;
import com.hnqd.pojo.Showroom;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface ShowroomRepository {

    void addShowroom(Showroom showroom);

    List<ShowroomResponse> getShowrooms();

    void deleteShowroomById(int id);

    ShowroomResponse updateShowroomById(int id, Showroom showroom);

    ShowroomResponse getShowroomById(int id);

}
