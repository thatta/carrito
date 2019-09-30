package com.mycompany.entity;

import com.mycompany.entity.Producto;
import com.mycompany.entity.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-29T18:56:09")
@StaticMetamodel(Venta_producto.class)
public class Venta_producto_ { 

    public static volatile SingularAttribute<Venta_producto, Venta> venta;
    public static volatile SingularAttribute<Venta_producto, Integer> total;
    public static volatile SingularAttribute<Venta_producto, Integer> id;
    public static volatile SingularAttribute<Venta_producto, Producto> producto;
    public static volatile SingularAttribute<Venta_producto, Integer> cantidad;

}