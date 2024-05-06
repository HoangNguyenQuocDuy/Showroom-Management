package com.hnqd.pojo;

import com.hnqd.pojo.User;
import com.hnqd.pojo.Vehicle;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-05-06T03:26:21")
@StaticMetamodel(Invoicebuy.class)
public class Invoicebuy_ { 

    public static volatile SingularAttribute<Invoicebuy, Date> createdAt;
    public static volatile SingularAttribute<Invoicebuy, BigDecimal> amount;
    public static volatile SingularAttribute<Invoicebuy, User> customerId;
    public static volatile SingularAttribute<Invoicebuy, Integer> id;
    public static volatile SingularAttribute<Invoicebuy, Vehicle> vehicleId;
    public static volatile SingularAttribute<Invoicebuy, User> staffId;
    public static volatile SingularAttribute<Invoicebuy, String> status;
    public static volatile SingularAttribute<Invoicebuy, Date> updatedAt;

}