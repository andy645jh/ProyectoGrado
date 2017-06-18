package com.proyecto.facades;

import com.proyecto.persistences.ActividadMisional;
import com.proyecto.persistences.ActividadObligatoria;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Experiencia;
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
public class ExperienciaFacade extends AbstractFacade<Experiencia> {
    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager em;

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return em;
    } 

    public ExperienciaFacade() {
        super(Experiencia.class);
    }
    
    public List<Experiencia> buscarCampo(String columna,String valorBuscar)
    {
        CriteriaBuilder cb= obtenerEntidad().getCriteriaBuilder();
        CriteriaQuery<Experiencia> cq= cb.createQuery(Experiencia.class);
        Root<Experiencia> objInfo= cq.from(Experiencia.class);
        
        if(!valorBuscar.equals("") && !columna.equals(""))
        {            
            if(columna.equals("coddocente"))
            {
                Join<Experiencia,Docentes> doc = objInfo.join("coddocente");
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
            cq.from(Experiencia.class);                     
        }
        
        Query consulta = em.createQuery(cq);
        return consulta.getResultList();
    }
    
}
