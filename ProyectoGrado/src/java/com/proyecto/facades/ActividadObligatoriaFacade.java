package com.proyecto.facades;

import com.proyecto.persistences.ActividadObligatoria;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class ActividadObligatoriaFacade extends AbstractFacade<ActividadObligatoria> {
    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager em;

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return em;
    } 

    public ActividadObligatoriaFacade() {
        super(ActividadObligatoria.class);
    }
    
}
