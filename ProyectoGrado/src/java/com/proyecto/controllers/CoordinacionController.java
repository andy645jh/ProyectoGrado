package com.proyecto.controllers;

import com.proyecto.facades.CoordinacionFacade;
import com.proyecto.persistences.Coordinacion;
import com.proyecto.utilities.Formulario;
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
public class CoordinacionController {

    @EJB
    private CoordinacionFacade _ejbFacade;
    
    private Coordinacion _obj;
    
    private FacesMessage message;
    
    public CoordinacionController() {
    }
    
    public Coordinacion getCampo()
    {
        if(_obj==null)  _obj= new Coordinacion();
        return _obj;        
    }
    
    public void resetear()
    {
        _obj = null;
    }
    
    public List<Coordinacion> getListado()
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
        RequestContext.getCurrentInstance().openDialog("/coordinacion/crear", options, null);
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
            
            Logger.getLogger(Coordinacion.class.getName()).log(Level.SEVERE,null,e);
            
        }
    }
    public SelectItem[] combo(String texto)
    {
        return Formulario.addObject(_ejbFacade.listado(), texto);
    }
    @FacesConverter(forClass = Coordinacion.class, value = "coordinacionConverter")
    public static class CoordinacionControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                if (value == null || value.length() == 0) return null;
                
                Integer id = Integer.parseInt(value);
                CoordinacionController controller = (CoordinacionController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "coordinacionController");
                return controller._ejbFacade.buscar(id);
            }catch(NumberFormatException e){
                Logger.getLogger(Coordinacion.class.getName()).log(Level.SEVERE, null, e);               
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Coordinacion){
                Coordinacion obj = (Coordinacion) value;
                return String.valueOf(obj.getCodcoordinacion());
            }
            return null;
        }
    }
}
