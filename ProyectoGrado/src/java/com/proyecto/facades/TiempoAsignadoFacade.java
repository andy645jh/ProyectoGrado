package com.proyecto.facades;

import com.proyecto.persistences.TiempoAsignado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author User
 */
@Stateless
public class TiempoAsignadoFacade extends AbstractFacade<TiempoAsignado> {

    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager em;

    @Override
    protected EntityManager obtenerEntidad() {
        return em;
    }

    public TiempoAsignadoFacade() {
        super(TiempoAsignado.class);
    }

    public List<TiempoAsignado> listadoAgrupado() {
        
        Query query = em.createQuery("SELECT a.coddocente FROM tiempoasignado a");
        return query.getResultList();
    }
}
