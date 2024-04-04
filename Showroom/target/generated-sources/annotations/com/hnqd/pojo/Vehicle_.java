package com.hnqd.pojo;

import com.hnqd.pojo.Booking;
import com.hnqd.pojo.Invoicebuy;
import com.hnqd.pojo.Maintenance;
import com.hnqd.pojo.Rental;
import com.hnqd.pojo.Showroom;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-04-04T21:10:17")
@StaticMetamodel(Vehicle.class)
public class Vehicle_ { 

    public static volatile SingularAttribute<Vehicle, String> image;
    public static volatile SetAttribute<Vehicle, Rental> rentalSet;
    public static volatile SingularAttribute<Vehicle, String> description;
    public static volatile SingularAttribute<Vehicle, String> specifications;
    public static volatile SingularAttribute<Vehicle, BigDecimal> price;
    public static volatile SingularAttribute<Vehicle, String> name;
    public static volatile SetAttribute<Vehicle, Booking> bookingSet;
    public static volatile SingularAttribute<Vehicle, Showroom> showroomId;
    public static volatile SingularAttribute<Vehicle, Integer> id;
    public static volatile SingularAttribute<Vehicle, String> brand;
    public static volatile SetAttribute<Vehicle, Invoicebuy> invoicebuySet;
    public static volatile SetAttribute<Vehicle, Maintenance> maintenanceSet;
    public static volatile SingularAttribute<Vehicle, String> status;

}