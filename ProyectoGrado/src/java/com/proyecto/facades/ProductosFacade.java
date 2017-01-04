
package com.proyecto.facades;

import com.proyecto.persistences.Actividades;
import com.proyecto.persistences.Productos;
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
public class ProductosFacade extends AbstractFacade<Productos>{

    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager _em;
    
    public ProductosFacade()
    {
        super(Productos.class);
    }

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return _em;
    }  
    
    public List<Productos> buscarCampo(String columna,String valorBuscar)
    {
        CriteriaBuilder cb= obtenerEntidad().getCriteriaBuilder();
        CriteriaQuery<Productos> cq= cb.createQuery(Productos.class);
        Root<Productos> objActividades = cq.from(Productos.class);
        
                
        if(!valorBuscar.equals("") && !columna.equals(""))
        {            
            if(columna.equals("_codactividad"))
            {
                Join<Productos,Actividades> doc = objActividades.join("_codactividad");
                Expression<String> valor = doc.get("_codactividad");
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
            cq.from(Productos.class);                     
        }
        
        Query consulta = _em.createQuery(cq);
        return consulta.getResultList();
    }
}
