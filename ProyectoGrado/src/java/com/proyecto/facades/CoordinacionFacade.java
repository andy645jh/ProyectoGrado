package com.proyecto.facades;

import com.proyecto.persistences.Coordinacion;
import com.proyecto.persistences.Facultad;
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
public class CoordinacionFacade extends AbstractFacade<Coordinacion> {
    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager em;

    public CoordinacionFacade() {
        super(Coordinacion.class);
    }
    
     @Override
    protected EntityManager obtenerEntidad() 
    {
        return em;
    } 
    
    public List<Coordinacion> buscarCampo(String columna,String valorBuscar)
    {
        CriteriaBuilder cb= obtenerEntidad().getCriteriaBuilder();
        CriteriaQuery<Coordinacion> cq= cb.createQuery(Coordinacion.class);
        Root<Coordinacion> objDocentes = cq.from(Coordinacion.class);
        
        if(!valorBuscar.equals("") && !columna.equals(""))
        {            
            if(columna.equals("_codfacultad"))
            {
                System.out.println("ENTRO A BUSCAR POR FACULTAD");
                Join<Facultad,Coordinacion> doc = objDocentes.join("_codfacultad");
                Expression<String> valor = doc.get("_codfacultad");
                String cadena = valorBuscar;
                
                //System.out.println("VALOR "+valor+" CADENA "+cadena);
                
                Predicate condicion = cb.equal(valor, cadena);                
                cq.where(condicion);
            }else{           
       
                //System.out.println("Columna: " + columna);
                Expression<String> valorCampo = objDocentes.get(columna);
                String cadena = valorBuscar;
                Predicate condicion = cb.equal(valorCampo, cadena);
                cq.where(condicion);               
            }
            
            
        }else{
            cq.from(Coordinacion.class);                     
        }
        
        Query consulta = em.createQuery(cq);
        return consulta.getResultList();
    }
}
