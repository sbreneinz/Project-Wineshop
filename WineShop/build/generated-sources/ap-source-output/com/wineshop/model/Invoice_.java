package com.wineshop.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-20T18:08:20")
@StaticMetamodel(Invoice.class)
public class Invoice_ { 

    public static volatile SingularAttribute<Invoice, String> operationDate;
    public static volatile SingularAttribute<Invoice, Integer> invoiceId;
    public static volatile SingularAttribute<Invoice, Integer> employeeId;
    public static volatile SingularAttribute<Invoice, Double> totalSale;

}