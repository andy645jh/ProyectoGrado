package com.proyecto.facades;

import com.proyecto.persistences.Permisos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author User
 */
@Stateless
public class PermisosFacade extends AbstractFacade<Permisos> {
    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager em;

    public PermisosFacade() {
        super(Permisos.class);
    }

    @Override
    protected EntityManager obtenerEntidad() {
        return em;
    }
    
    public Permisos buscarCampo(String columna,String valorBuscar)
    {
        CriteriaBuilder cb= obtenerEntidad().getCriteriaBuilder();
        CriteriaQuery<Permisos> cq= cb.createQuery(Permisos.class);
        Root<Permisos> objPermisos = cq.from(Permisos.class);
        
        if(!valorBuscar.equals("") && !columna.equals(""))
        {        
            System.out.println("Columna: " + columna);
            Expression<String> valorCampo = objPermisos.get(columna);
            String cadena = valorBuscar;
            Predicate condicion = cb.equal(valorCampo, cadena);
            cq.where(condicion);   
            
        }else{
            cq.from(Permisos.class);                     
        }
        
        Query consulta = em.createQuery(cq);
        Permisos p = (Permisos) consulta.getSingleResult();
        return p;
    }
}
