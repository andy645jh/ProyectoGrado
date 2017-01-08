
package com.proyecto.controllers;

import com.proyecto.facades.FacultadFacade;
import com.proyecto.persistences.Convenciones;
import com.proyecto.persistences.Facultad;
import com.sun.xml.xsom.impl.scd.Iterators;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;


@ManagedBean
@SessionScoped
public class FacultadesControllers {

   @EJB
    private FacultadFacade _ejbFacade;
    private Facultad _obj;
    private FacesMessage message;
    
    public FacultadesControllers() {
    }
    
    public Facultad getCampo()
    {
        if(_obj==null)  _obj= new Facultad();
        return _obj;        
    }
    
    public void resetear()
    {
        _obj = null;
    }
    
    public void abrirCrear() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/facultades/crear", options, null);
    }
    
    public void agregar()
    {
        String titulo,detalle;
        System.out.println("VA A GUARDAR "+_obj.toString());
        try {         
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
             message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);             
            _ejbFacade.crear(_obj);
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            Logger.getLogger(Facultad.class.getName()).log(Level.SEVERE,null,e);
            
           
        }
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.closeDialog(null);
    }
    
    public void abrirActualizar(Facultad objtemp) {
        
        _obj = objtemp;
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/facultades/actualizar", options, null);
    }
    
    public void actualizar()
    {
        String titulo,detalle;
        System.out.println("VA A actualizar "+_obj.toString());
        try {
            
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
            _ejbFacade.actualizar(_obj);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle); 
            
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            Logger.getLogger(Convenciones.class.getName()).log(Level.SEVERE,null,e);
           
        }
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.closeDialog(null);
    }  
}
