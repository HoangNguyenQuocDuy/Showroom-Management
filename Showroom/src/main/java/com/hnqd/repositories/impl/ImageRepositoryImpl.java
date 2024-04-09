/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories.impl;

import com.hnqd.pojo.Image;
import com.hnqd.pojo.Vehicle;
import com.hnqd.repositories.ImageRepository;
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
public class ImageRepositoryImpl implements ImageRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addImage(int vehicleId, String imageUrl) {
        Session session = factory.getObject().getCurrentSession();
        Vehicle vehicle = session.get(Vehicle.class, vehicleId);
        if (vehicle != null) {
            Image image = new Image();
            image.setUrl(imageUrl);
            image.setVehicleId(vehicle);
            session.save(image);
        }
    }

    @Override
    public void addImages(int vehicleId, List<String> imageUrls) {
        Session session = factory.getObject().getCurrentSession();
        Vehicle vehicle = session.get(Vehicle.class, vehicleId);
        if (vehicle != null) {
            for (String imageUrl : imageUrls) {
                Image image = new Image();
                image.setUrl(imageUrl);
                image.setVehicleId(vehicle);
                session.save(image);
            }
        }
    }

    @Override
    public void removeImage(int vehicleId, String imageUrl) {
        Session session = factory.getObject().getCurrentSession();
        Vehicle vehicle = session.get(Vehicle.class, vehicleId);
        if (vehicle != null) {
            Query<Image> query = session.createQuery("FROM Image WHERE url = :url AND vehicleId = :vehicleId", Image.class);
            query.setParameter("url", imageUrl);
            query.setParameter("vehicleId", vehicle);
            Image image = query.uniqueResult();
            if (image != null) {
                session.delete(image);
            }
        }
    }

    @Override
    public void removeImages(int vehicleId, List<String> imageUrls) {
        Session session = factory.getObject().getCurrentSession();
        Vehicle vehicle = session.get(Vehicle.class, vehicleId);
        if (vehicle != null) {
            for (String imageUrl : imageUrls) {
                Query<Image> query = session.createQuery("FROM Image WHERE url = :url AND vehicleId = :vehicleId", Image.class);
                query.setParameter("url", imageUrl);
                query.setParameter("vehicleId", vehicle);
                Image image = query.uniqueResult();
                if (image != null) {
                    session.delete(image);
                }
            }
        }
    }

}
