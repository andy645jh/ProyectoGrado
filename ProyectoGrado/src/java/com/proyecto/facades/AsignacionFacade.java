package com.proyecto.facades;

import com.proyecto.persistences.Actividades;
import com.proyecto.persistences.Asignacion;
import com.proyecto.persistences.Coordinacion;
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

    public List<Asignacion> buscarA(String columna, String valorBuscar) {

        CriteriaBuilder cb= obtenerEntidad().getCriteriaBuilder();
        CriteriaQuery<Asignacion> cq= cb.createQuery(Asignacion.class);
        Root<Asignacion> objDocentes = cq.from(Asignacion.class);
        
        if(!valorBuscar.equals("") && !columna.equals(""))
        {            
            if(columna.equals("_codcoordinacion"))
            {
                Join<Coordinacion,Asignacion> doc = objDocentes.join("_codcoordinacion");
                Expression<String> valor = doc.get("_codcoordinacion");
                String cadena = valorBuscar;
                
                //System.out.println("AsignacionFacade.buscarA -> VALOR "+valor+" CADENA "+cadena);
                
                Predicate condicion = cb.equal(valor, cadena);                
                cq.where(condicion);
            }else if(columna.equals("_coddocente"))
            {
                Join<Docentes,Asignacion> doc = objDocentes.join("_coddocente");
                Expression<String> valor = doc.get("_cedula");
                String cadena = valorBuscar;
                
                //System.out.println("AsignacionFacade.buscarA -> VALOR "+valor+" CADENA "+cadena);
                
                Predicate condicion = cb.equal(valor, cadena);                
                cq.where(condicion);
            }else{           
       
                //System.out.println("AsignacionFacade.buscarA -> Columna: " + columna);
                Expression<String> valorCampo = objDocentes.get(columna);
                String cadena = valorBuscar;
                Predicate condicion = cb.equal(valorCampo, cadena);
                cq.where(condicion);               
            }
            
            
        }else{
            cq.from(Asignacion.class);                     
        }
        
        Query consulta = em.createQuery(cq);
        return consulta.getResultList();
    }
    
    public Asignacion buscarDocente(String columna,String valorBuscar)
    {
        CriteriaBuilder cb= obtenerEntidad().getCriteriaBuilder();
        CriteriaQuery<Asignacion> cq= cb.createQuery(Asignacion.class);
        Root<Asignacion> objAsignacion = cq.from(Asignacion.class);
        
        if(!valorBuscar.equals("") && !columna.equals(""))
        {                        
            if(columna.equals("_coddocente"))
            {
                System.out.println("AsignacionFacade.buscarDocente");
                Join<Asignacion,Docentes> doc = objAsignacion.join("_coddocente");
                Expression<String> valor = doc.get("_cedula");
                String cadena = valorBuscar;
                Predicate condicion = cb.equal(valor, cadena);                
                cq.where(condicion);
            }else{           
       
                System.out.println("Columna: " + columna);
                Expression<String> valorCampo = objAsignacion.get(columna);
                String cadena = valorBuscar;
                Predicate condicion = cb.equal(valorCampo, cadena);
                cq.where(condicion);               
            }
            
            
        }else{
            System.out.println("AsignacionFacade.buscarDocente vacios");
            cq.from(Asignacion.class);                     
        }
        
        Query consulta = em.createQuery(cq);
        return (Asignacion) consulta.getSingleResult();
    }
}
