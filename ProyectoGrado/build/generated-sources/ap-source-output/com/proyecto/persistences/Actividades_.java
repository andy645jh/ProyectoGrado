package com.proyecto.persistences;

import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.TipoModalidades;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-01-08T21:08:18")
@StaticMetamodel(Actividades.class)
public class Actividades_ { 

    public static volatile SingularAttribute<Actividades, String> _descripcion;
    public static volatile SingularAttribute<Actividades, TipoModalidades> _codtipo;
    public static volatile SingularAttribute<Actividades, Double> _horas;
    public static volatile SingularAttribute<Actividades, String> _responsable;
    public static volatile SingularAttribute<Actividades, Double> _valoracion;
    public static volatile SingularAttribute<Actividades, Integer> _codactividad;
    public static volatile SingularAttribute<Actividades, Docentes> _coddocente;
    public static volatile SingularAttribute<Actividades, String> _nombre;

}