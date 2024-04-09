/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories.impl;

import com.hnqd.dto.ShowroomResponse;
import com.hnqd.dto.VehicleResponse;
import com.hnqd.pojo.Image;
import com.hnqd.pojo.Showroom;
import com.hnqd.pojo.Vehicle;
import com.hnqd.repositories.VehicleRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Repository
@Transactional
@PropertySource("classpath:application.properties")
public class VehicleRepositoryImpl implements VehicleRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Override
    public void addVehicle(Vehicle vehicle) {
        Session s = factory.getObject().getCurrentSession();

        s.save(vehicle);
    }

    @Override
    public List<VehicleResponse> getVehicles(Map<String, String> params) {
        Session s = factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Vehicle> q = b.createQuery(Vehicle.class);
        Root r = q.from(Vehicle.class);
        q.select(r);

        List<Predicate> predicates = new ArrayList<>();

        String kw = params.get("kw");

        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(r.get("name"), String.format("%%%s%%", kw)));
        }

        String fromPrice = params.get("fromPrice");
        if (fromPrice != null && !fromPrice.isEmpty()) {
            predicates.add(b.greaterThanOrEqualTo(r.get("price"), Double.parseDouble(fromPrice)));
        }

        String toPrice = params.get("toPrice");
        if (toPrice != null && !toPrice.isEmpty()) {
            predicates.add(b.lessThanOrEqualTo(r.get("price"), Double.parseDouble(toPrice)));
        }

        String showroomId = params.get("showroomId");
        if (showroomId != null && !showroomId.isEmpty()) {
            predicates.add(b.equal(r.get("showroomId"), Integer.parseInt(showroomId)));
        }

        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get("id")));

        Query query = s.createQuery(q);

        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("vehicles.pageSize").toString());
            int start = (Integer.parseInt(p) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }

        List<Vehicle> vehicles = query.getResultList();
        List<VehicleResponse> result = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            result.add(mapToVehicleResponse(vehicle));
        }

        return result;
    }

    @Override
    public void deleteVehicleById(int id) {
        Session s = factory.getObject().getCurrentSession();
        s.createQuery("DELETE FROM Vehicle WHERE id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public VehicleResponse updateVehicleById(Map<String, String> params) {
        Session s = factory.getObject().getCurrentSession();
        Vehicle existingVehicle = s.get(Vehicle.class, Integer.parseInt(params.get("vehicleId")));

        if (existingVehicle != null) {
            String name = params.get("name");
            if (name != null && !name.isEmpty()) {
                existingVehicle.setName(name);
            }

            String priceString = params.get("price");
            if (priceString != null && !priceString.isEmpty()) {
                BigDecimal price = new BigDecimal(priceString);
                existingVehicle.setPrice(price);
            }

            String specifications = params.get("specifications");
            if (specifications != null && !specifications.isEmpty()) {
                existingVehicle.setSpecifications(specifications);
            }

            String brand = params.get("brand");
            if (brand != null && !brand.isEmpty()) {
                existingVehicle.setBrand(brand);
            }

            String status = params.get("status");
            if (status != null && !status.isEmpty()) {
                existingVehicle.setStatus(status);
            }

            s.update(existingVehicle);
            return mapToVehicleResponse(existingVehicle);
        }

        return null;
    }

    @Override
    public VehicleResponse getVehicleById(int id) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Vehicle WHERE id=:id")
                .setParameter("id", id);

        return mapToVehicleResponse((Vehicle) q.getSingleResult());
    }

    @Override
    public VehicleResponse updateImagesVehicle(int id, Set<Image> images) {
        Session s = factory.getObject().getCurrentSession();
        Vehicle existingVehicle = s.get(Vehicle.class, id);

        if (existingVehicle != null) {
            existingVehicle.getImageSet().addAll(images);

            s.update(existingVehicle);
            return mapToVehicleResponse(existingVehicle);
        }

        return null;
    }

    private VehicleResponse mapToVehicleResponse(Vehicle vehicle) {
        VehicleResponse vehicleRes = new VehicleResponse();
        vehicleRes.setId(vehicle.getId());
        vehicleRes.setName(vehicle.getName());
        vehicleRes.setPrice(vehicle.getPrice());
        vehicleRes.setSpecifications(vehicle.getSpecifications());
        vehicleRes.setDescription(vehicle.getDescription());
        vehicleRes.setBrand(vehicle.getBrand());
        vehicleRes.setStatus(vehicle.getStatus());
        vehicleRes.setShowroom(mapShowroomResponse(vehicle.getShowroomId()));

        return vehicleRes;
    }

    private ShowroomResponse mapShowroomResponse(Showroom showroom) {
        ShowroomResponse showroomRes = new ShowroomResponse();
        showroomRes.setId(showroom.getId());
        showroomRes.setName(showroom.getName());
        showroomRes.setLocation(showroom.getLocation());

        return showroomRes;
    }

}
