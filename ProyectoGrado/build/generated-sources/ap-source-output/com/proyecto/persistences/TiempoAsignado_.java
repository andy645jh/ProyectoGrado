package com.proyecto.persistences;

import com.proyecto.persistences.ActividadObligatoria;
import com.proyecto.persistences.Docentes;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-01-08T21:08:18")
@StaticMetamodel(TiempoAsignado.class)
public class TiempoAsignado_ { 

    public static volatile SingularAttribute<TiempoAsignado, Integer> _codtiempoasignado;
    public static volatile SingularAttribute<TiempoAsignado, Date> _horas;
    public static volatile SingularAttribute<TiempoAsignado, Docentes> _coddocente;
    public static volatile SingularAttribute<TiempoAsignado, ActividadObligatoria> _codobliatoria;

}