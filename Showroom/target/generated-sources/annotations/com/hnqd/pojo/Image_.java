package com.hnqd.pojo;

import com.hnqd.pojo.Vehicle;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-05-06T18:39:36")
@StaticMetamodel(Image.class)
public class Image_ { 

    public static volatile SingularAttribute<Image, Integer> id;
    public static volatile SingularAttribute<Image, Vehicle> vehicleId;
    public static volatile SingularAttribute<Image, String> url;

}