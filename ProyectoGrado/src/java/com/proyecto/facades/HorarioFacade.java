package com.proyecto.facades;

import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Horario;
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
public class HorarioFacade extends AbstractFacade<Horario> {
    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager em;

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return em;
    } 

    public HorarioFacade() {
        super(Horario.class);
    }
    
    public List<Horario> buscarCampo(String columna,String valorBuscar)
    {
        CriteriaBuilder cb= obtenerEntidad().getCriteriaBuilder();
        CriteriaQuery<Horario> cq= cb.createQuery(Horario.class);
        Root<Horario> objActividades = cq.from(Horario.class);
        
        
        if(!valorBuscar.equals("") && !columna.equals(""))
        {            
            if(columna.equals("_coddocente"))
            {
                Join<Horario,Docentes> doc = objActividades.join("_coddocente");
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
            cq.from(Horario.class);                     
        }
        
        Query consulta = em.createQuery(cq);
        return consulta.getResultList();
    }
    
}
