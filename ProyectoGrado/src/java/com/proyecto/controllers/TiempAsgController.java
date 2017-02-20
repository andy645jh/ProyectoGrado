
package com.proyecto.controllers;

import com.proyecto.facades.TiempoAsignadoFacade;
import com.proyecto.persistences.TiempoAsignado;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Usuario
 */
@ManagedBean
@SessionScoped

public class TiempAsgController implements Serializable {

    private TiempoAsignado _obj;
    @EJB
    private TiempoAsignadoFacade _ejbFacade;
    private FacesMessage message;
    private int codigito;
    private int codDocente;
    private int codHoraDocencia;
    
    public TiempAsgController() {
    }
    
    public List<TiempoAsignado> getListado()
    {
        return _ejbFacade.listado();
    }
    
    public void abrirCrear() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/tiempodoc/crear", options, null);
//        return "/tiempodoc/crear";
    }
    
    public void agregar()
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);
           
            _ejbFacade.crear(_obj);           
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
            
            Logger.getLogger(TiempoAsignado.class.getName()).log(Level.SEVERE,null,e);
            
        }
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

    public int setCodigito() {
        return codigito;
    }

    public void getCodigito(int codigito) {
        this.codigito = codigito;
    }

    public int getCodDocente() {
        return codDocente;
    }

    public void setCodDocente(int codDocente) {
        this.codDocente = codDocente;
    }

    public int getCodHoraDocencia() {
        return codHoraDocencia;
    }

    public void setCodHoraDocencia(int codHoraDocencia) {
        this.codHoraDocencia = codHoraDocencia;
    }
    
    
    
}
