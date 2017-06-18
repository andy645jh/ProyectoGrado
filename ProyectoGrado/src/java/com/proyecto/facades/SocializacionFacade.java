package com.proyecto.facades;

import com.proyecto.persistences.ActividadMisional;
import com.proyecto.persistences.ActividadObligatoria;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Socializacion;
import com.proyecto.persistences.Socializacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author User
 */
@Stateless
public class SocializacionFacade extends AbstractFacade<Socializacion> {
    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager em;

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return em;
    } 

    public SocializacionFacade() {
        super(Socializacion.class);
    }
    
    public List<Socializacion> buscarCampo(String columna,String valorBuscar)
    {
        CriteriaBuilder cb= obtenerEntidad().getCriteriaBuilder();
        CriteriaQuery<Socializacion> cq= cb.createQuery(Socializacion.class);
        Root<Socializacion> objInfo= cq.from(Socializacion.class);
        
        if(!valorBuscar.equals("") && !columna.equals(""))
        {            
            if(columna.equals("coddocente"))
            {
                Join<Socializacion,Docentes> doc = objInfo.join("coddocente");
                Expression<String> valor = doc.get("_cedula");
                String cadena = valorBuscar;
                Predicate condicion = cb.equal(valor, cadena);                
                cq.where(condicion);
            }else{           
       
                System.out.println("Columna: " + columna);
                Expression<String> valorCampo = objInfo.get(columna);
                String cadena = valorBuscar;
                Predicate condicion = cb.equal(valorCampo, cadena);
                cq.where(condicion);               
            }
            
            
        }else{
            cq.from(Socializacion.class);                     
        }
        
        Query consulta = em.createQuery(cq);
        return consulta.getResultList();
    }
    
}
