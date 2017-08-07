
package com.proyecto.controllers;

import com.proyecto.utilities.Formulario;
import com.proyecto.facades.HorarioFacade;
import com.proyecto.facades.ConvencionesFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.persistences.Horario;
import com.proyecto.persistences.Convenciones;
import com.proyecto.persistences.Docentes;
import com.proyecto.utilities.Intervalo;
import com.proyecto.utilities.Mensajes;
import com.proyecto.utilities.SessionUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
    private Docentes _currentDocente;
    
    private final String _intervalos[] = {"6-7","7-8","8-9","9-10","10-11","11-12","12-13","13-14","14-15","15-16","16-17","17-18","18-19","19-20","20-21","21-22"};
    private final String _dias[] = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
    
    public HorarioController() {
    }
    
    @PostConstruct
    public void init() {
        _currentDocente = (Docentes) SessionUtils.get("docente");
        _listInterval = new ArrayList<>();       
        System.out.println("Docente: "+ _currentDocente);
        //eventModel = new DefaultScheduleModel();        
        /*List<Horario> listHorario = getListado();
        _objHorario = listHorario.get(0);        
        _arrayInterval = new Intervalo[_intervalos.length];
        System.out.println("TAMAÑO "+listHorario.size());
        for(int i=0;i<_arrayInterval.length;i++)
        {
            _arrayInterval[i] =new Intervalo();
            _arrayInterval[i].setInitData(_intervalos[i],i);
        }
        
        for(Horario obj:listHorario)
        {            
            //eventModel.addEvent(new DefaultScheduleEvent(obj.getNombre(), obj.getHorainicio(), obj.getHorafinal(),obj));
            System.out.println("HOra: "+obj.getHora());
            //cuadrando la lista de horarios        
            _arrayInterval[obj.getHora()].setDia(obj);               
        }
        
        //convertir array a lista
        _listInterval = Arrays.asList(_arrayInterval);*/
        organizarListas();
    }
    
    private void organizarListas()
    {
        List<Horario> listHorario = getListado();
        _objHorario = listHorario.get(0);   
        _arrayInterval = new Intervalo[_intervalos.length];
        System.out.println("TAMAÑO "+listHorario.size());
        for(int i=0;i<_arrayInterval.length;i++)
        {
            _arrayInterval[i] =new Intervalo();
            _arrayInterval[i].setInitData(_intervalos[i],i);
        }
        
        for(Horario obj:listHorario)
        {            
            //eventModel.addEvent(new DefaultScheduleEvent(obj.getNombre(), obj.getHorainicio(), obj.getHorafinal(),obj));
            System.out.println("HOra: "+obj.getHora());
            //cuadrando la lista de horarios        
            _arrayInterval[obj.getHora()].setDia(obj);               
        }
        
        //convertir array a lista
        _listInterval = Arrays.asList(_arrayInterval);
        //RequestContext.getCurrentInstance().update(":formHorario:nuevaLista");
    }
    
    public String getHoraIntervalo(int index)
    {
        return _intervalos[index];
    }    
    
    public Horario getCampo()
    {
        if(_objHorario==null)  _objHorario= new Horario();
        return _objHorario;        
    }
    
    public void abrirCrear(Horario horTemp) {      
        
        System.out.println("Horario Actual: " + _objHorario.getDia());
        System.out.println("Horario Nuevo: " + horTemp.getDia());        
        
        _objHorario = horTemp;
        System.out.println("Horario Reasignado: " + _objHorario.getDia());        
        Convenciones conve;
        
        if(_objHorario.getCodconvencion()==null)
        {
            conve = new Convenciones();            
        }else{
            conve = _objHorario.getCodconvencion();
        }
        
        conve.setCodconvencion(0);      
        _objHorario.setCodconvencion(conve);
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().execute("PF('eventDialog').show();");
    }
    
    public void closeDialog(Horario horaTemp)
    {
        _objHorario = horaTemp;
        System.out.println("HorarioController.closeDialog() -> Se cerro");
        borrar();
        
        organizarListas();
    }
    
    public void agregar(ActionEvent actionEvent)
    {        
        String titulo,detalle;
        Convenciones convencion = _convencionesFacade.buscar(_objHorario.getCodconvencion().getCodconvencion());
        _objHorario.setCodconvencion(convencion);
        
        System.out.println("convencion "+_objHorario.getCodconvencion());
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);
//            _objHorario.setCoddocente(docentesFacade.getCurrentDocente());
            
            _objHorario.setCoddocente(_currentDocente);
            
            System.out.println("docente "+_objHorario.getCoddocente().getNombres());
            
                         
            horarioFacade.crear(_objHorario);   
            _objHorario.setAsignado(true);
            Mensajes.exito(titulo, detalle);
            //eventModel.addEvent(new DefaultScheduleEvent(_objHorario.getNombre(), _objHorario.getHorainicio(), _objHorario.getHorafinal(),_objHorario));
            //evento = new DefaultScheduleEvent();          
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            //message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Horario.class.getName()).log(Level.SEVERE,null,e);           
        }
    }
    
    //devuelve el dia dependiendo del index que le pasen
    public String getDia(int cod)
    {
        return _dias[cod-1];
    }
    
    //devuelve el intervalo de hora dependiendo del index que le pasen
    public String getHora(int cod)
    {
        return _intervalos[cod];
    }
    
    public SelectItem[] combo(String texto)
    {
        return Formulario.addObject(horarioFacade.listado(), texto);        
    }
    
    //obtiene el listado de horarios segun el docente de la sesion
    public List<Horario> getListado()
    {
        String cedula= _currentDocente.getCedula()+"";       
        return horarioFacade.buscarCampo("_coddocente",cedula);
    }
    
    public void borrar()
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);
              

            System.out.print("Borrar ---- " + _objHorario.getNombre());                
            horarioFacade.borrar(_objHorario);       
            Convenciones conve = new Convenciones();
            conve.setCodconvencion(0);
            _objHorario.setNombre("");            
            _objHorario.setCodconvencion(conve);
            _objHorario.setAsignado(false);
        } catch (Exception e) 
        {
            System.out.print("HorarioController.borrar() -> Error al borrar ---- " + e.toString());    
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            Logger.getLogger(Horario.class.getName()).log(Level.SEVERE,null,e);
        }        
    }    
    
    public void close()
    {
        System.out.println("Cerro Dialog");
    }
    
    public void mostrarMensaje()
    {        
        System.out.println("HorarioController.MostrarMensaje");
        if(message!=null) FacesContext.getCurrentInstance().addMessage("mensajes", message);
        message=null;
    }
    
    public void abrirActualizar(Horario objTemp) {
        System.out.println("HorarioController.abrirActualizar() -> horario: "+objTemp);
        _objHorario = objTemp;
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        //RequestContext.getCurrentInstance().openDialog("faces/clases/actualizar", options, null);
        RequestContext.getCurrentInstance().execute("PF('eventDialog').show();");
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
    
    public SelectItem[] comboHoras()
    {
        //List<Convenciones> lista =_ejbFacade.listado(); 
        SelectItem[] listaItems = new SelectItem[_intervalos.length];
        int index=0;
        for (String interv : _intervalos) {
            SelectItem item = new SelectItem(index, interv);            
            listaItems[index]=item;
            index++;
        }
        
        return listaItems;
    }
    
    public SelectItem[] comboDias()
    {
        //List<Convenciones> lista =_ejbFacade.listado(); 
        SelectItem[] listaItems = new SelectItem[_dias.length];
        int index=0;
        for (String nombre : _dias) {
            SelectItem item = new SelectItem(index, nombre);            
            listaItems[index]=item;
            index++;
        }
        
        return listaItems;
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

    public List<Intervalo> getListInterval() {
        return _listInterval;
    }

    public void setListInterval(List<Intervalo> _listInterval) {
        this._listInterval = _listInterval;
    }

    public Horario getObjHorario() {
        return _objHorario;
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
