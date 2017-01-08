package com.proyecto.facades;

import com.proyecto.persistences.Porcentaje;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class PorcentajeFacade extends AbstractFacade<Porcentaje> {
    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager em;

     @Override
    protected EntityManager obtenerEntidad() 
    {
        return em;
    }  

    public PorcentajeFacade() {
        super(Porcentaje.class);
    }
    
}
