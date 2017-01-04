package com.proyecto.facades;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


public abstract class AbstractFacade <T>
{
    private final Class<T> _ent;
    protected abstract EntityManager obtenerEntidad();

    public AbstractFacade(Class<T> objEntidad) {
        this._ent = objEntidad;
    }
    
    public void crear(T valor)
    {
        obtenerEntidad().persist(valor);
    }
    
    public void actualizar(T dato)
    {
        obtenerEntidad().merge(dato);
    }
    
    public void borrar(T info)
    {
        obtenerEntidad().remove(obtenerEntidad().merge(info));
    }
    
    public T buscar(Object id)
    {
        return obtenerEntidad().find(_ent, id);
    }
    
    public List<T> listado()
    {
        CriteriaBuilder cb= obtenerEntidad().getCriteriaBuilder();
        CriteriaQuery<T> cq= cb.createQuery(_ent);
        cq.select(cq.from(_ent));
        List<T> arreglo = obtenerEntidad().createQuery(cq).getResultList();
        return arreglo;
    }
}
