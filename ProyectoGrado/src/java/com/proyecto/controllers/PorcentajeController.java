package com.proyecto.controllers;

import com.proyecto.facades.PorcentajeFacade;
import com.proyecto.persistences.Porcentaje;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author User
 */
@ManagedBean
@SessionScoped
public class PorcentajeController implements Serializable{

    @EJB
    private PorcentajeFacade _ejbFacade;
    private Porcentaje _obj;
    private FacesMessage message;
    
    public PorcentajeController() {
    }
    
    public Porcentaje getCampo()
    {
        if(_obj==null)  _obj= new Porcentaje();
        return _obj;        
    }
    
    public List<Porcentaje> getListado()
    {
        return _ejbFacade.listado();
    }
}
