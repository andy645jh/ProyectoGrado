
package com.proyecto.controllers;

import com.proyecto.facades.FacultadFacade;
import com.proyecto.persistences.Convenciones;
import com.proyecto.persistences.Facultad;
import com.proyecto.utilities.Mensajes;
import com.sun.xml.xsom.impl.scd.Iterators;
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
public class FacultadesControllers implements Serializable{

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
    
    public List<Facultad> getListado()
    {
        return _ejbFacade.listado();
    }
    
    public void mostrarMensaje()
    {        
        if(message!=null) FacesContext.getCurrentInstance().addMessage("mensajes", message);
        message=null;
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
    
    public void borrar(Facultad faceObj)
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
    
    public SelectItem[] combo(String texto)
    {
        List<Facultad> lista =_ejbFacade.listado(); 
        SelectItem[] listaItems = new SelectItem[lista.size()];
        int index=0;
        for (Facultad facultad : lista) {
            SelectItem item = new SelectItem(facultad.getCodfacultad(), facultad.getNombre());
            
            listaItems[index]=item;
            index++;
        }
        
        return listaItems;
    }
    
    @FacesConverter(forClass = Facultad.class, value = "facultadConverter")
    public static class FacultadesControllersConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try {
                if (value == null || value.length() == 0) {
                    return null;
                }

                Integer id = Integer.parseInt(value);
                FacultadesControllers controller = (FacultadesControllers) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "facultadesControllers");
                System.out.println("Docentse NUMERO ENCONTRADO EN EL COMBO " + controller._ejbFacade.buscar(id));
                return controller._ejbFacade.buscar(id);
            } catch (NumberFormatException e) {
                Logger.getLogger(Facultad.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Facultad) {
                Facultad obj = (Facultad) value;
                return String.valueOf(obj.getCodfacultad());
            }
            return null;
        }
    }
}
