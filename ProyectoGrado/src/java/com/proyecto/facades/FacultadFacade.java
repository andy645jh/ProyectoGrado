package com.proyecto.facades;

import com.proyecto.persistences.Facultad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class FacultadFacade extends AbstractFacade<Facultad> {
    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager em;

    public FacultadFacade() {
        super(Facultad.class);
    }
    
     @Override
    protected EntityManager obtenerEntidad() 
    {
        return em;
    }   
}
