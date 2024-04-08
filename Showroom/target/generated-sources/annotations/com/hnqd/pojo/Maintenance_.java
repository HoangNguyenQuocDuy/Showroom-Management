package com.hnqd.pojo;

import com.hnqd.pojo.Invoicemaintenance;
import com.hnqd.pojo.User;
import com.hnqd.pojo.Vehicle;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-04-08T19:01:52")
@StaticMetamodel(Maintenance.class)
public class Maintenance_ { 

    public static volatile SingularAttribute<Maintenance, Date> createdDate;
    public static volatile SetAttribute<Maintenance, Invoicemaintenance> invoicemaintenanceSet;
    public static volatile SingularAttribute<Maintenance, Integer> id;
    public static volatile SingularAttribute<Maintenance, Date> updatedDate;
    public static volatile SingularAttribute<Maintenance, Date> time;
    public static volatile SingularAttribute<Maintenance, Vehicle> vehicleId;
    public static volatile SingularAttribute<Maintenance, User> userId;
    public static volatile SingularAttribute<Maintenance, String> content;
    public static volatile SingularAttribute<Maintenance, String> status;

}