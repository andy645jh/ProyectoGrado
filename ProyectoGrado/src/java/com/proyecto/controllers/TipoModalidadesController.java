
package com.proyecto.controllers;

import com.proyecto.utilities.Mensajes;
import com.proyecto.facades.TipoModalidadesFacade;
import com.proyecto.persistences.TipoModalidades;
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
public class TipoModalidadesController implements Serializable
{  
    @EJB
    private TipoModalidadesFacade _ejbFacade;
    private TipoModalidades _obj;
    
    private FacesMessage message;
    
    public TipoModalidadesController() { }
    
    public TipoModalidades getCampo()
    {
        if(_obj==null)  _obj= new TipoModalidades();
        return _obj;        
    }
    
    public void abrirCrear() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        
        RequestContext.getCurrentInstance().openDialog("/tipo_modalidad/crear", options, null);
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
            Logger.getLogger(TipoModalidades.class.getName()).log(Level.SEVERE,null,e);
        }
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.closeDialog(null);
    }
    
    public SelectItem[] combo(String texto)
    {
        List<TipoModalidades> lista =_ejbFacade.listado(); 
        SelectItem[] listaItems = new SelectItem[lista.size()];
        int index=0;
        for (TipoModalidades modalidad : lista) {
            SelectItem item = new SelectItem(modalidad.getCodtipo(), modalidad.getNombre());
            
            listaItems[index]=item;
            index++;
        }
        
        return listaItems;
    }
    
    public List<TipoModalidades> getListado()
    {
        return _ejbFacade.listado();
    }
    
    public void borrar(TipoModalidades faceObj)
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarExitoso");
            Mensajes.exito(titulo, detalle);
            _ejbFacade.borrar(faceObj);
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(TipoModalidades.class.getName()).log(Level.SEVERE,null,e);
        }
    }    
    
    public void abrirActualizar(TipoModalidades objtemp) {
        
        _obj = objtemp;
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/tipo_modalidad/actualizar", options, null);
    }
    
    public void mostrarMensaje()
    {        
        if(message!=null) FacesContext.getCurrentInstance().addMessage("mensajes", message);
        message=null;
    }
    
    public void actualizar()
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);
            _ejbFacade.actualizar(_obj);
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            Logger.getLogger(TipoModalidades.class.getName()).log(Level.SEVERE,null,e);
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.closeDialog(null);
    }  
    
    public void resetear()
    {
        _obj = null;
    }
    
    @FacesConverter(forClass = TipoModalidades.class, value = "tipoModalidadesConverter")
    public static class TipoModalidadesControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                if (value == null || value.length() == 0) return null;
                
                Integer id = Integer.parseInt(value);
                TipoModalidadesController controller = (TipoModalidadesController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "tipoModalidadesController");
                return controller._ejbFacade.buscar(id);
            }catch(NumberFormatException e){
                Logger.getLogger(TipoModalidades.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof TipoModalidades){
                TipoModalidades obj = (TipoModalidades) value;
                return String.valueOf(obj.getCodtipo());
            }
            return null;
        }
    }
}
