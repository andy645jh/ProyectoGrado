
package com.proyecto.facades;

import com.proyecto.persistences.TipoModalidades;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class TipoModalidadesFacade extends AbstractFacade<TipoModalidades>
{
   @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager _em;
    
    public TipoModalidadesFacade()
    {
        super(TipoModalidades.class);
    }

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return _em;
    }   
}
