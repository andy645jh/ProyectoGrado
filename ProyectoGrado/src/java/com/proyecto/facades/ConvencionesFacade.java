
package com.proyecto.facades;

import com.proyecto.persistences.Convenciones;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class ConvencionesFacade extends AbstractFacade<Convenciones>{

    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager _em;
    
    public ConvencionesFacade()
    {
        super(Convenciones.class);
    }

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return _em;
    }   
}
