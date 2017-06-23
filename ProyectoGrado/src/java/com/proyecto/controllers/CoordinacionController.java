package com.proyecto.controllers;

import com.proyecto.facades.CoordinacionFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.facades.FacultadFacade;
import com.proyecto.persistences.Convenciones;
import com.proyecto.persistences.Coordinacion;
import com.proyecto.persistences.Facultad;
import com.proyecto.utilities.Formulario;
import com.proyecto.utilities.Mensajes;
import com.proyecto.utilities.SessionUtils;
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
import javax.inject.Inject;
import org.primefaces.context.RequestContext;


@ManagedBean
@SessionScoped
public class CoordinacionController implements Serializable{
    
    @EJB
    private FacultadFacade _facultadFacade;

    @EJB
    private CoordinacionFacade _ejbFacade;  
    
    @EJB
    private DocentesFacade _facadeDocentes; 
         
    private Coordinacion _obj;    
    private FacesMessage message;
    private int codFacultad;
    
    public CoordinacionController() {
        
    }
    
    public Coordinacion getCampo()
    {
        if(_obj==null)  _obj= new Coordinacion();
        return _obj;        
    }
    
    public String obtenerCod()
    {        
        return ((Coordinacion) SessionUtils.get("coordinacion")).toString();
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
    
    public void procesar()
    {        
        String titulo, detalle;       
        _obj = (Coordinacion) SessionUtils.get("coordinacion");
        System.out.println("LO Q HAY EN COORDI " + _obj);        
       
        try {
            
            //actualizar
            System.out.println("ACTUALIZAR");
            _obj.setAsignado(true);
            _ejbFacade.actualizar(_obj);
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
                                    
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);
            System.out.print("coordinacion =" + _obj.getCodcoordinacion() + " investigacion="+_obj.getInvestigacion()+" extension=" + _obj.getExtension());
            System.out.println(" comite=" + _obj.getComites() + " ODA=" + _obj.getOda() + " acreditacion=" + _obj.getAcreditacion() + " virtualidad=" + _obj.getVirtualidad());
                                    
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
        } catch (Exception e) {
            
            System.out.println("ERROR");
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);

            Logger.getLogger(Coordinacion.class.getName()).log(Level.SEVERE, null, e);

        }
    }
    
    public void agregar()
    {
        String titulo,detalle;
        Facultad f=_facultadFacade.buscar(codFacultad);        
        _obj.setCodfacultad(f);
        
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
    
    public void borrar(Coordinacion faceObj)
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
            Logger.getLogger(Coordinacion.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    public void abrirActualizar(Coordinacion objtemp) {
        
        
        _obj = objtemp;
        codFacultad=_obj.getCodfacultad().getCodfacultad();
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/coordinacion/actualizar", options, null);
    }
    
    public void actualizar()
    {
        String titulo,detalle;
        System.out.println("VA A actualizar "+_obj.toString());
        Facultad f=_facultadFacade.buscar(codFacultad);        
        _obj.setCodfacultad(f);
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
            Logger.getLogger(Coordinacion.class.getName()).log(Level.SEVERE,null,e);
           
        }
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.closeDialog(null);
    } 
    
    public SelectItem[] combo(String texto)
    {
        List<Coordinacion> lista =_ejbFacade.listado(); 
        SelectItem[] listaItems = new SelectItem[lista.size()];
        int index=0;
        for (Coordinacion coordinacion : lista) {
            SelectItem item = new SelectItem(coordinacion.getCodcoordinacion(), coordinacion.getNombre());
            
            listaItems[index]=item;
            index++;
        }
        
        return listaItems;
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

    public int getCodFacultad() {
        return codFacultad;
    }

    public void setCodFacultad(int codFacultad) {
        this.codFacultad = codFacultad;
    }
    
    
}
