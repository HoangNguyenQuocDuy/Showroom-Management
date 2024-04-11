package com.hnqd.pojo;

import com.hnqd.pojo.Invoicerent;
import com.hnqd.pojo.User;
import com.hnqd.pojo.Vehicle;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-04-11T01:28:43")
@StaticMetamodel(Rental.class)
public class Rental_ { 

    public static volatile SingularAttribute<Rental, Date> createdAt;
    public static volatile SingularAttribute<Rental, User> customerId;
    public static volatile SingularAttribute<Rental, Integer> id;
    public static volatile SingularAttribute<Rental, Date> time;
    public static volatile SingularAttribute<Rental, Vehicle> vehicleId;
    public static volatile SetAttribute<Rental, Invoicerent> invoicerentSet;
    public static volatile SingularAttribute<Rental, User> staffId;
    public static volatile SingularAttribute<Rental, String> status;
    public static volatile SingularAttribute<Rental, Date> updatedAt;

}