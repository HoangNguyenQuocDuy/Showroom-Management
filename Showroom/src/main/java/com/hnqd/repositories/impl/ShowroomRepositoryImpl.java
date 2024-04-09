/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories.impl;

import com.hnqd.dto.ShowroomResponse;
import com.hnqd.pojo.Showroom;
import com.hnqd.repositories.ShowroomRepository;
import java.util.ArrayList;
import java.util.List;
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
public class ShowroomRepositoryImpl implements ShowroomRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addShowroom(Showroom showroom) {
        Session s = factory.getObject().getCurrentSession();

        s.save(showroom);
    }

    @Override
    public List<ShowroomResponse> getShowrooms() {
        Session s = factory.getObject().getCurrentSession();

        List<Showroom> list = s.createQuery("FROM Showroom", Showroom.class).list();

        List<ShowroomResponse> showroomRes = new ArrayList<>();

        for (Showroom showroom : list) {
            showroomRes.add(mapShowroomResponse(showroom));
        }

        return showroomRes;
    }

    @Override
    public void deleteShowroomById(int showroomId) {
        Session s = factory.getObject().getCurrentSession();
        s.createQuery("DELETE Showroom WHERE id=:showroomId")
                .setParameter("showroomId", showroomId)
                .executeUpdate();
    }

    @Override
    public ShowroomResponse updateShowroomById(int showroomId, Showroom showroom) {
        Session s = factory.getObject().getCurrentSession();
        Showroom existingShowroom = s.get(Showroom.class, showroomId);

        if (existingShowroom != null) {
            String location = showroom.getLocation();
            if (location != null && !location.isEmpty()) {
                existingShowroom.setLocation(location);
            } 
            
            String name = showroom.getName();
            if (name != null && !name.isEmpty()) {
                existingShowroom.setName(name);
            } 

            s.update(existingShowroom);
        }

        return mapShowroomResponse(existingShowroom);
    }

    @Override
    public ShowroomResponse getShowroomById(int id) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Showroom WHERE id=:id")
                .setParameter("id", id);

        return mapShowroomResponse((Showroom) q.getSingleResult());
    }

    private ShowroomResponse mapShowroomResponse(Showroom showroom) {
        ShowroomResponse showroomRes = new ShowroomResponse();
        showroomRes.setId(showroom.getId());
        showroomRes.setName(showroom.getName());
        showroomRes.setLocation(showroom.getLocation());

        return showroomRes;
    }
}
