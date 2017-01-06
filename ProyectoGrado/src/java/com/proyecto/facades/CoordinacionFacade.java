package com.proyecto.facades;

import com.proyecto.persistences.Coordinacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class CoordinacionFacade extends AbstractFacade<Coordinacion> {
    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager em;

    public CoordinacionFacade() {
        super(Coordinacion.class);
    }
    
     @Override
    protected EntityManager obtenerEntidad() 
    {
        return em;
    }   
}
