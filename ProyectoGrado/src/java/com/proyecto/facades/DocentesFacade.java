
package com.proyecto.facades;

import com.proyecto.persistences.Docentes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class DocentesFacade extends AbstractFacade<Docentes>{

    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager _em;
    private Docentes _currentDocente;
    
    public DocentesFacade()
    {
        super(Docentes.class);
    }

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return _em;
    }   

    public Docentes getCurrentDocente() {
        return _currentDocente;
    }

    public void setCurrentDocente(Docentes _currentDocente) {
        this._currentDocente = _currentDocente;
    }
}
