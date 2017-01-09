package com.proyecto.persistences;

import com.proyecto.persistences.Facultad;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-01-08T21:08:18")
@StaticMetamodel(Coordinacion.class)
public class Coordinacion_ { 

    public static volatile SingularAttribute<Coordinacion, Integer> _codcoordinacion;
    public static volatile SingularAttribute<Coordinacion, Facultad> _codfacultad;
    public static volatile SingularAttribute<Coordinacion, String> _nombre;

}