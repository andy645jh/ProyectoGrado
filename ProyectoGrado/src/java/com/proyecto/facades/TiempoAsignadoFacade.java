package com.proyecto.facades;

import com.proyecto.persistences.TiempoAsignado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class TiempoAsignadoFacade extends AbstractFacade<TiempoAsignado> {
    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager em;

     @Override
    protected EntityManager obtenerEntidad() 
    {
        return em;
    }   

    public TiempoAsignadoFacade() {
        super(TiempoAsignado.class);
    }
    
}
