package com.proyecto.facades;

import com.proyecto.persistences.PorcentajeAsig;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class PorcentajeAsigFacade extends AbstractFacade<PorcentajeAsig> {
    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager em;

     @Override
    protected EntityManager obtenerEntidad() 
    {
        return em;
    }  

    public PorcentajeAsigFacade() {
        super(PorcentajeAsig.class);
    }
    
}
