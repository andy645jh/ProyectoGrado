package com.proyecto.persistences;

import com.proyecto.persistences.Actividades;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-05T14:36:52")
@StaticMetamodel(Productos.class)
public class Productos_ { 

    public static volatile SingularAttribute<Productos, Date> _fechaentrega;
    public static volatile SingularAttribute<Productos, Date> _fechacompromiso;
    public static volatile SingularAttribute<Productos, String> _descripcion;
    public static volatile SingularAttribute<Productos, Integer> _codproducto;
    public static volatile SingularAttribute<Productos, String> _comentarios;
    public static volatile SingularAttribute<Productos, Actividades> _codactividad;

}