package com.hnqd.pojo;

import com.hnqd.pojo.User;
import com.hnqd.pojo.Vehicle;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-04-09T10:56:02")
@StaticMetamodel(Booking.class)
public class Booking_ { 

    public static volatile SingularAttribute<Booking, Date> createdDate;
    public static volatile SingularAttribute<Booking, User> customerId;
    public static volatile SingularAttribute<Booking, Integer> id;
    public static volatile SingularAttribute<Booking, Date> updatedDate;
    public static volatile SingularAttribute<Booking, Date> time;
    public static volatile SingularAttribute<Booking, Vehicle> vehicleId;
    public static volatile SingularAttribute<Booking, String> content;
    public static volatile SingularAttribute<Booking, User> staffId;
    public static volatile SingularAttribute<Booking, String> status;

}