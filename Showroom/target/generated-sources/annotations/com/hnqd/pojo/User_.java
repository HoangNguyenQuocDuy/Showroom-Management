package com.hnqd.pojo;

import com.hnqd.pojo.Booking;
import com.hnqd.pojo.Invoicebuy;
import com.hnqd.pojo.Invoicemaintenance;
import com.hnqd.pojo.Maintenance;
import com.hnqd.pojo.Rental;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-04-08T19:01:52")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> image;
    public static volatile SetAttribute<User, Rental> rentalSet;
    public static volatile SetAttribute<User, Booking> bookingSet1;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SetAttribute<User, Invoicemaintenance> invoicemaintenanceSet;
    public static volatile SetAttribute<User, Rental> rentalSet1;
    public static volatile SingularAttribute<User, String> roleName;
    public static volatile SetAttribute<User, Booking> bookingSet;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SetAttribute<User, Invoicebuy> invoicebuySet;
    public static volatile SetAttribute<User, Maintenance> maintenanceSet;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SetAttribute<User, Invoicebuy> invoicebuySet1;

}