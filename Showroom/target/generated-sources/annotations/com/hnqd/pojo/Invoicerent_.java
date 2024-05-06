package com.hnqd.pojo;

import com.hnqd.pojo.Rental;
import com.hnqd.pojo.User;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-05-06T18:39:36")
@StaticMetamodel(Invoicerent.class)
public class Invoicerent_ { 

    public static volatile SingularAttribute<Invoicerent, Date> createdAt;
    public static volatile SingularAttribute<Invoicerent, BigDecimal> amount;
    public static volatile SingularAttribute<Invoicerent, Integer> id;
    public static volatile SingularAttribute<Invoicerent, User> staffId;
    public static volatile SingularAttribute<Invoicerent, String> status;
    public static volatile SingularAttribute<Invoicerent, Date> updatedAt;
    public static volatile SingularAttribute<Invoicerent, Rental> rentalId;

}