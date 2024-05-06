package com.hnqd.pojo;

import com.hnqd.pojo.Showroom;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-05-06T03:26:21")
@StaticMetamodel(Vehicle.class)
public class Vehicle_ { 

    public static volatile SingularAttribute<Vehicle, String> image;
    public static volatile SingularAttribute<Vehicle, BigDecimal> price;
    public static volatile SingularAttribute<Vehicle, String> name;
    public static volatile SingularAttribute<Vehicle, Showroom> showroomId;
    public static volatile SingularAttribute<Vehicle, String> description;
    public static volatile SingularAttribute<Vehicle, Integer> id;
    public static volatile SingularAttribute<Vehicle, String> specifications;
    public static volatile SingularAttribute<Vehicle, String> brand;
    public static volatile SingularAttribute<Vehicle, String> status;

}