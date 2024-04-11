package com.hnqd.pojo;

import com.hnqd.pojo.Invoicerent;
import com.hnqd.pojo.Rental;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-04-11T22:07:05")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> image;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SetAttribute<User, Rental> rentalSet1;
    public static volatile SetAttribute<User, Rental> rentalSet;
    public static volatile SingularAttribute<User, String> roleName;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SetAttribute<User, Invoicerent> invoicerentSet;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}