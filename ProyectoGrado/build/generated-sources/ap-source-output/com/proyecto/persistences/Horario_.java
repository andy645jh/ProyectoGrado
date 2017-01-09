package com.proyecto.persistences;

import com.proyecto.persistences.Convenciones;
import com.proyecto.persistences.Docentes;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-01-08T21:08:18")
@StaticMetamodel(Horario.class)
public class Horario_ { 

    public static volatile SingularAttribute<Horario, String> _dia;
    public static volatile SingularAttribute<Horario, Integer> _codhorario;
    public static volatile SingularAttribute<Horario, Convenciones> _codconvencion;
    public static volatile SingularAttribute<Horario, Date> _horainicio;
    public static volatile SingularAttribute<Horario, Date> _horafinal;
    public static volatile SingularAttribute<Horario, Docentes> _coddocente;
    public static volatile SingularAttribute<Horario, String> _nombre;

}