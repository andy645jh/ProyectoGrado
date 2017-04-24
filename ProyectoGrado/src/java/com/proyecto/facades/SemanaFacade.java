
package com.proyecto.facades;

import com.proyecto.persistences.Semana;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class SemanaFacade extends AbstractFacade<Semana>{

    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager _em;
  
    
    public SemanaFacade()
    {
        super(Semana.class);
    }

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return _em;
    }   

}
