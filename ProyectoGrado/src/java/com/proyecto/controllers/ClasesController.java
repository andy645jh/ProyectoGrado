
package com.proyecto.controllers;

import com.proyecto.utilities.Formulario;
import com.proyecto.facades.ClasesFacade;
import com.proyecto.facades.ConvencionesFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.persistences.Clases;
import com.proyecto.persistences.Convenciones;
import com.proyecto.persistences.Docentes;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;


@ManagedBean
@SessionScoped
public class ClasesController implements Serializable{
    @EJB
    private DocentesFacade docentesFacade;
    
    @EJB
    private ClasesFacade clasesFacade;
    
    @EJB
    private ConvencionesFacade _convencionesFacade;
    
    private Clases _objClase;
    private ScheduleModel eventModel;
    private ScheduleEvent evento= new DefaultScheduleEvent();   
   
    private FacesMessage message;
    private int _codigo;
    
    public ClasesController() {
    }
    
    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();        
                
        for(Clases obj:getListado())
        {            
            eventModel.addEvent(new DefaultScheduleEvent(obj.getNombre(), obj.getCodhorainicio(), obj.getCodhorafinal(),obj));
        }
    }
    
    public ScheduleModel getEventModel() {
        return eventModel;
    }
    
    public Clases getCampo()
    {
        if(_objClase==null)  _objClase= new Clases();
        return _objClase;        
    }
    
    public void abrirCrear() {       
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("faces/clases/crear", options, null);
    }
    
    public void agregar(ActionEvent actionEvent)
    {        
        String titulo,detalle;
        Convenciones convencion = _convencionesFacade.buscar(_codigo);
        _objClase.setCodconvencion(convencion);
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);
            _objClase.setCoddocente(docentesFacade.getCurrentDocente());
            
            if(evento.getId()==null)
            {               
                clasesFacade.crear(_objClase);                
            }
            else{                           
                clasesFacade.actualizar(_objClase);                
                eventModel.deleteEvent(evento);
            }
            
            eventModel.addEvent(new DefaultScheduleEvent(_objClase.getNombre(), _objClase.getCodhorainicio(), _objClase.getCodhorafinal(),_objClase));
            evento = new DefaultScheduleEvent();          
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            Logger.getLogger(Clases.class.getName()).log(Level.SEVERE,null,e);           
        }
    }
    
    public SelectItem[] combo(String texto)
    {
        return Formulario.addObject(clasesFacade.listado(), texto);        
    }
    
    public List<Clases> getListado()
    {
        Docentes doc = docentesFacade.getCurrentDocente();
        String cedula= doc.getCedula()+"";
       
        return clasesFacade.buscarCampo("_coddocente",cedula);
    }
    
    //se ejecuta cuando se selecciona un evento
    public void onEventSelect(SelectEvent selectEvent) 
    {        
        evento = (ScheduleEvent)selectEvent.getObject();        
        _objClase=(Clases)evento.getData();       
    }
     
    //se ejecuta cuando se selecciona una fecha
    public void onDateSelect(SelectEvent selectEvent)
    {        
        evento = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject()); 
        _objClase=null;       
    }
     
    public void onEventMove(ScheduleEntryMoveEvent event)
    {
        System.out.print("onEventMove: " + event);
    }
     
    public void onEventResize(ScheduleEntryResizeEvent event) 
    {
        System.out.print("onEventResize: " + event);
    }
    
    public void borrar(ActionEvent actionEvent)
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);
            
            
            if(evento.getId()!=null) {
                
                System.out.print("Borrar ---- " + _objClase.getNombre());                
                clasesFacade.borrar(_objClase);            
                eventModel.deleteEvent(evento);
            }           
            
            evento = new DefaultScheduleEvent();
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            Logger.getLogger(Clases.class.getName()).log(Level.SEVERE,null,e);
        }        
    }    
    
    public void mostrarMensaje()
    {        
        System.out.println("ClasesController.MostrarMensaje");
        if(message!=null) FacesContext.getCurrentInstance().addMessage("mensajes", message);
        message=null;
    }
    
    public void abrirActualizar(Clases objTemp) {
        _objClase = objTemp;
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("faces/clases/actualizar", options, null);
    }
    
    public void actualizar()
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);
            System.out.println("CLASES: " + docentesFacade.getCurrentDocente());
            _objClase.setCoddocente(docentesFacade.getCurrentDocente());
            clasesFacade.actualizar(_objClase);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            Logger.getLogger(Clases.class.getName()).log(Level.SEVERE,null,e);
        }
    }  
    
    public void resetear()
    {
        _objClase = null;
    }

    public int getCodigo() {
        return _codigo;
    }

    public void setCodigo(int _codigo) {
        this._codigo = _codigo;
    }
    
    @FacesConverter(forClass = Clases.class, value = "clasesConverter")
    public static class ClasesControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                if (value == null || value.length() == 0) return null;
                
                Integer id = Integer.parseInt(value);
                ClasesController controller = (ClasesController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "clasesController");
                return controller.clasesFacade.buscar(id);
            }catch(NumberFormatException e){
                Logger.getLogger(Clases.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Clases){
                Clases obj = (Clases) value;
                return String.valueOf(obj.getCodclase());
            }
            return null;
        }
    }  
}
