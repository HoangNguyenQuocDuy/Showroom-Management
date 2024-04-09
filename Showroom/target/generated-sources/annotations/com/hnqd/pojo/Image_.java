package com.hnqd.pojo;

import com.hnqd.pojo.Vehicle;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-04-09T23:44:18")
@StaticMetamodel(Image.class)
public class Image_ { 

    public static volatile SingularAttribute<Image, Integer> id;
    public static volatile SingularAttribute<Image, Vehicle> vehicleId;
    public static volatile SingularAttribute<Image, String> url;

}