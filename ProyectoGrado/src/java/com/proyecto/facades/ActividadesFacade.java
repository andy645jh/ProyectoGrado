
package com.proyecto.facades;

import com.proyecto.persistences.Actividades;
import com.proyecto.persistences.Docentes;
import java.util.List;
import javax.ejb.EJB;
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


@Stateless
public class ActividadesFacade extends AbstractFacade<Actividades>{
    
    @EJB
    private DocentesFacade docentesFacade;

    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager _em;
    
    
    public ActividadesFacade()
    {
        super(Actividades.class);
    }

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return _em;
    }   
    
    
    public List<Actividades> buscarCampo(String columna,String valorBuscar)
    {
        CriteriaBuilder cb= obtenerEntidad().getCriteriaBuilder();
        CriteriaQuery<Actividades> cq= cb.createQuery(Actividades.class);
        Root<Actividades> objActividades = cq.from(Actividades.class);
        
        if(!valorBuscar.equals("") && !columna.equals(""))
        {            
            if(columna.equals("_coddocente"))
            {
                Join<Actividades,Docentes> doc = objActividades.join("_coddocente");
                Expression<String> valor = doc.get("_cedula");
                String cadena = valorBuscar;
                Predicate condicion = cb.equal(valor, cadena);                
                cq.where(condicion);
            }else{           
       
                System.out.println("Columna: " + columna);
                Expression<String> valorCampo = objActividades.get(columna);
                String cadena = valorBuscar;
                Predicate condicion = cb.equal(valorCampo, cadena);
                cq.where(condicion);               
            }
            
            
        }else{
            cq.from(Actividades.class);                     
        }
        
        Query consulta = _em.createQuery(cq);
        return consulta.getResultList();
    }
}
