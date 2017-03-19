
package com.proyecto.controllers;

import com.proyecto.facades.ConvencionesFacade;
import com.proyecto.persistences.Convenciones;
import com.proyecto.utilities.Mensajes;
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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;


@ManagedBean
@SessionScoped
public class ConvencionesController implements Serializable{

    @EJB
    private ConvencionesFacade _ejbFacade;
    private Convenciones _obj;
    private FacesMessage message;
    
    public ConvencionesController() {
    }
    
    public Convenciones getCampo()
    {
        if(_obj==null)  _obj= new Convenciones();
        return _obj;        
    }
    
    public void abrirCrear() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/convenciones/crear", options, null);
    }
    
    public void agregar()
    {
        String titulo,detalle;
        
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
            Logger.getLogger(Convenciones.class.getName()).log(Level.SEVERE,null,e);
            
           
        }
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.closeDialog(null);
    }
    
    public SelectItem[] combo(String texto)
    {
        List<Convenciones> lista =_ejbFacade.listado(); 
        SelectItem[] listaItems = new SelectItem[lista.size()];
        int index=0;
        for (Convenciones convencion : lista) {
            SelectItem item = new SelectItem(convencion.getCodconvencion(), convencion.getNombre());
            
            listaItems[index]=item;
            index++;
        }
        
        return listaItems;
    }
    
    public List<Convenciones> getListado()
    {
        return _ejbFacade.listado();
    }
    
    public void borrar(Convenciones faceObj)
    {
        String titulo,detalle;
        
        try {
            _ejbFacade.borrar(faceObj);
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarExitoso");
            Mensajes.exito(titulo, detalle);
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Convenciones.class.getName()).log(Level.SEVERE,null,e);
        }
    }    
    
    public void abrirActualizar(Convenciones objtemp) {
        
        _obj = objtemp;
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/convenciones/actualizar", options, null);
    }
    
    public void actualizar()
    {
        String titulo,detalle;
        
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
    
    public void mostrarMensaje()
    {        
        if(message!=null) FacesContext.getCurrentInstance().addMessage("mensajes", message);
        message=null;
    }
       
    public void resetear()
    {
        _obj = null;
    }
    
    @FacesConverter(forClass = Convenciones.class, value = "convencionesConverter")
    public static class ConvencionesControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                if (value == null || value.length() == 0) return null;
                
                Integer id = Integer.parseInt(value);
                ConvencionesController controller = (ConvencionesController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "convencionesController");
                return controller._ejbFacade.buscar(id);
            }catch(NumberFormatException e){
                Logger.getLogger(Convenciones.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Convenciones){
                Convenciones obj = (Convenciones) value;
                return String.valueOf(obj.getCodconvencion());
            }
            return null;
        }
    }
}
