package com.hnqd.pojo;

import com.hnqd.pojo.User;
import com.hnqd.pojo.Vehicle;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-04-04T21:10:17")
@StaticMetamodel(Showroom.class)
public class Showroom_ { 

    public static volatile SetAttribute<Showroom, Vehicle> vehicleSet;
    public static volatile SingularAttribute<Showroom, String> name;
    public static volatile SingularAttribute<Showroom, String> location;
    public static volatile SingularAttribute<Showroom, Integer> id;
    public static volatile SetAttribute<Showroom, User> userSet;

}