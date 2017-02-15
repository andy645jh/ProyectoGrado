
package com.proyecto.controllers;

import com.proyecto.facades.TiempoAsignadoFacade;
import com.proyecto.persistences.TiempoAsignado;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Usuario
 */
@Named(value = "tiempoAsignadoController")
@SessionScoped
public class TiempoAsignadoController implements Serializable {

    private TiempoAsignado _obj;
    @EJB
    private TiempoAsignadoFacade _ejbFacade;
    private FacesMessage message;
    
    public TiempoAsignadoController() {
    }

    public TiempoAsignado getObj() {
        
        if(_obj==null){
            _obj= new TiempoAsignado();
        }
        return _obj;
    }

    public void setObj(TiempoAsignado _obj) {
        this._obj = _obj;
    }
    
    public List<TiempoAsignado> getListado()
    {
        return _ejbFacade.listado();
    }
    
}
