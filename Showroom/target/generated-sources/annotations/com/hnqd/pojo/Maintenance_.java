package com.hnqd.pojo;

import com.hnqd.pojo.Showroom;
import com.hnqd.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-05-06T18:39:36")
@StaticMetamodel(Maintenance.class)
public class Maintenance_ { 

    public static volatile SingularAttribute<Maintenance, Date> createdDate;
    public static volatile SingularAttribute<Maintenance, Showroom> showroomId;
    public static volatile SingularAttribute<Maintenance, User> customerId;
    public static volatile SingularAttribute<Maintenance, Integer> id;
    public static volatile SingularAttribute<Maintenance, Date> updatedDate;
    public static volatile SingularAttribute<Maintenance, Date> time;
    public static volatile SingularAttribute<Maintenance, String> content;
    public static volatile SingularAttribute<Maintenance, User> staffId;
    public static volatile SingularAttribute<Maintenance, String> status;

}