
package com.proyecto.facades;

import com.proyecto.persistences.Clases;
import com.proyecto.persistences.Docentes;
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


@Stateless
public class ClasesFacade extends AbstractFacade<Clases>{

    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager _em;
    
    public ClasesFacade()
    {
        super(Clases.class);
    }

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return _em;
    }  
    
    public List<Clases> buscarCampo(String columna,String valorBuscar)
    {
        CriteriaBuilder cb= obtenerEntidad().getCriteriaBuilder();
        CriteriaQuery<Clases> cq= cb.createQuery(Clases.class);
        Root<Clases> objActividades = cq.from(Clases.class);
        
        
        if(!valorBuscar.equals("") && !columna.equals(""))
        {            
            if(columna.equals("_coddocente"))
            {
                Join<Clases,Docentes> doc = objActividades.join("_coddocente");
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
            cq.from(Clases.class);                     
        }
        
        Query consulta = _em.createQuery(cq);
        return consulta.getResultList();
    }
}
