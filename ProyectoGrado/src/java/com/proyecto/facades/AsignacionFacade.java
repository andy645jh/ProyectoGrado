package com.proyecto.facades;

import com.proyecto.persistences.Asignacion;
import com.proyecto.persistences.TiempoAsignado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author User
 */
@Stateless
public class AsignacionFacade extends AbstractFacade<Asignacion> {
    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager em;


    public AsignacionFacade() {
        super(Asignacion.class);
    }

    @Override
    protected EntityManager obtenerEntidad() {
        return em;
    }
    
    
    
}
