package com.hnqd.pojo;

import com.hnqd.pojo.Maintenance;
import com.hnqd.pojo.User;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-04-09T23:44:18")
@StaticMetamodel(Invoicemaintenance.class)
public class Invoicemaintenance_ { 

    public static volatile SingularAttribute<Invoicemaintenance, Maintenance> maintenanceId;
    public static volatile SingularAttribute<Invoicemaintenance, Date> createdAt;
    public static volatile SingularAttribute<Invoicemaintenance, BigDecimal> amount;
    public static volatile SingularAttribute<Invoicemaintenance, Integer> id;
    public static volatile SingularAttribute<Invoicemaintenance, User> staffId;
    public static volatile SingularAttribute<Invoicemaintenance, String> status;
    public static volatile SingularAttribute<Invoicemaintenance, Date> updatedAt;

}