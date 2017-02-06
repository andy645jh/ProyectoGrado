package com.proyecto.persistences;

import com.proyecto.persistences.Coordinacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-05T14:36:52")
@StaticMetamodel(Docentes.class)
public class Docentes_ { 

    public static volatile SingularAttribute<Docentes, String> _apellidos;
    public static volatile SingularAttribute<Docentes, String> _formacion;
    public static volatile SingularAttribute<Docentes, String> _codigo;
    public static volatile SingularAttribute<Docentes, String> _correo;
    public static volatile SingularAttribute<Docentes, Coordinacion> _codcoordinacion;
    public static volatile SingularAttribute<Docentes, String> _direccion;
    public static volatile SingularAttribute<Docentes, Integer> _cedula;
    public static volatile SingularAttribute<Docentes, String> _foto;
    public static volatile SingularAttribute<Docentes, String> _nombres;
    public static volatile SingularAttribute<Docentes, String> _telefono;

}