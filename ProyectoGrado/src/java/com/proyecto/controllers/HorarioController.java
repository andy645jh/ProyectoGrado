
package com.proyecto.controllers;

import com.proyecto.utilities.Formulario;
import com.proyecto.facades.HorarioFacade;
import com.proyecto.facades.ConvencionesFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.persistences.Horario;
import com.proyecto.persistences.Convenciones;
import com.proyecto.persistences.Docentes;
import com.proyecto.utilities.Intervalo;
import java.io.Serializable;
import java.util.ArrayList;
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
public class HorarioController implements Serializable{
    @EJB
    private DocentesFacade docentesFacade;
    
    @EJB
    private HorarioFacade horarioFacade;
    
    @EJB
    private ConvencionesFacade _convencionesFacade;
    
    private Horario _objHorario;
    private ScheduleModel eventModel;
    private ScheduleEvent evento= new DefaultScheduleEvent();   
   
    private FacesMessage message;
    private int _codigo;
    private List<Intervalo> _listInterval;
    private Intervalo[] _arrayInterval;
    private String _intervalos[] = {"6-7","7-8","8-9","9-10","10-11","11-12","13-14","15-16","16-17","17-18","18-19","19-20","20-21","21-22"};
    
    public HorarioController() {
    }
    
    @PostConstruct
    public void init() {
        _listInterval = new ArrayList<>();       
        
        //eventModel = new DefaultScheduleModel();        
        List<Horario> listHorario = getListado();
        _arrayInterval = new Intervalo[_intervalos.length];
        System.out.println("TAMAÑO "+listHorario.size());
        for(int i=0;i<_arrayInterval.length;i++)
        {
            _arrayInterval[i] =new Intervalo();
            _arrayInterval[i].setHora(_intervalos[i]);
        }
        
        for(Horario obj:listHorario)
        {            
            //eventModel.addEvent(new DefaultScheduleEvent(obj.getNombre(), obj.getHorainicio(), obj.getHorafinal(),obj));
            System.out.println("HOra: "+obj.getHora());
            //cuadrando la lista de horarios        
            _arrayInterval[obj.getHora()].setDia(obj);          
        }
        
        //convertir array a lista
        //_listInterval = new ArrayList<Intervalo>(_arrayInterval);
    }
    
    public String getHoraIntervalo(int index)
    {
        return _intervalos[index];
    }    
    
    public ScheduleModel getEventModel() {
        return eventModel;
    }
    
    public Horario getCampo()
    {
        if(_objHorario==null)  _objHorario= new Horario();
        return _objHorario;        
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
        _objHorario.setCodconvencion(convencion);
        _objHorario.setDia("lunes");
        System.out.println("convencion "+_objHorario.getCodconvencion());
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);
//            _objHorario.setCoddocente(docentesFacade.getCurrentDocente());
            _objHorario.setCoddocente(docentesFacade.buscar(109877));
            
            System.out.println("docente "+_objHorario.getCoddocente().getNombres());
            
            if(evento.getId()==null)
            {               
                horarioFacade.crear(_objHorario);                
            }
            else{                           
                horarioFacade.actualizar(_objHorario);                
                eventModel.deleteEvent(evento);
            }
            
            eventModel.addEvent(new DefaultScheduleEvent(_objHorario.getNombre(), _objHorario.getHorainicio(), _objHorario.getHorafinal(),_objHorario));
            evento = new DefaultScheduleEvent();          
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            Logger.getLogger(Horario.class.getName()).log(Level.SEVERE,null,e);           
        }
    }
    
    public SelectItem[] combo(String texto)
    {
        return Formulario.addObject(horarioFacade.listado(), texto);        
    }
    
    public List<Horario> getListado()
    {
        Docentes doc = docentesFacade.getCurrentDocente();
//        String cedula= doc.getCedula()+"";
        String cedula= "109877";
       
        return horarioFacade.buscarCampo("_coddocente",cedula);
    }
    
    //se ejecuta cuando se selecciona un evento
    public void onEventSelect(SelectEvent selectEvent) 
    {        
        evento = (ScheduleEvent)selectEvent.getObject();           
        _objHorario=(Horario)evento.getData();       
    }
     
    //se ejecuta cuando se selecciona una fecha
    public void onDateSelect(SelectEvent selectEvent)
    {          
        evento = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject()); 
        _objHorario=null;       
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
                
                System.out.print("Borrar ---- " + _objHorario.getNombre());                
                horarioFacade.borrar(_objHorario);            
                eventModel.deleteEvent(evento);
            }           
            
            evento = new DefaultScheduleEvent();
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            Logger.getLogger(Horario.class.getName()).log(Level.SEVERE,null,e);
        }        
    }    
    
    public void mostrarMensaje()
    {        
        System.out.println("HorarioController.MostrarMensaje");
        if(message!=null) FacesContext.getCurrentInstance().addMessage("mensajes", message);
        message=null;
    }
    
    public void abrirActualizar(Horario objTemp) {
        _objHorario = objTemp;
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
            _objHorario.setCoddocente(docentesFacade.getCurrentDocente());
            horarioFacade.actualizar(_objHorario);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            Logger.getLogger(Horario.class.getName()).log(Level.SEVERE,null,e);
        }
    }  
    
    public void resetear()
    {
        _objHorario = null;
    }

    public int getCodigo() {
        return _codigo;
    }

    public void setCodigo(int _codigo) {
        this._codigo = _codigo;
    }
    
    @FacesConverter(forClass = Horario.class, value = "horarioConverter")
    public static class HorarioControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                if (value == null || value.length() == 0) return null;
                
                Integer id = Integer.parseInt(value);
                HorarioController controller = (HorarioController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "horarioController");
                return controller.horarioFacade.buscar(id);
            }catch(NumberFormatException e){
                Logger.getLogger(Horario.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Horario){
                Horario obj = (Horario) value;
                return String.valueOf(obj.getCodhorario());
            }
            return null;
        }
    }  
}
