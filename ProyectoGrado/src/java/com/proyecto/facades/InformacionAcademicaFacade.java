
package com.proyecto.facades;

import com.proyecto.persistences.Actividades;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.InformacionAcademica;
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
public class InformacionAcademicaFacade extends AbstractFacade<InformacionAcademica>{
    
    @EJB
    private DocentesFacade docentesFacade;

    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager _em;
    
    
    public InformacionAcademicaFacade()
    {
        super(InformacionAcademica.class);
    }

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return _em;
    }   
    
    
    public List<InformacionAcademica> buscarCampo(String columna,String valorBuscar)
    {
        CriteriaBuilder cb= obtenerEntidad().getCriteriaBuilder();
        CriteriaQuery<InformacionAcademica> cq= cb.createQuery(InformacionAcademica.class);
        Root<InformacionAcademica> objInfo= cq.from(InformacionAcademica.class);
        
        if(!valorBuscar.equals("") && !columna.equals(""))
        {            
            if(columna.equals("cod_docente"))
            {
                Join<InformacionAcademica,Docentes> doc = objInfo.join("cod_docente");
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
            cq.from(InformacionAcademica.class);                     
        }
        
        Query consulta = _em.createQuery(cq);
        return consulta.getResultList();
    }
}
