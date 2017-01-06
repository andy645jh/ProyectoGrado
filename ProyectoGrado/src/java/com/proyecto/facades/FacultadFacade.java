/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.facades;

import com.proyecto.persistences.Facultad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class FacultadFacade extends AbstractFacade<Facultad> {
    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager em;

    public FacultadFacade() {
        super(Facultad.class);
    }
    
     @Override
    protected EntityManager obtenerEntidad() 
    {
        return em;
    }   
}
