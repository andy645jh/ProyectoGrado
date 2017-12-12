package com.proyecto.controllers;

import com.proyecto.facades.ActividadesFacade;
import com.proyecto.utilities.Formulario;
import com.proyecto.facades.HorarioFacade;
import com.proyecto.facades.ConvencionesFacade;
import com.proyecto.persistences.Actividades;
import com.proyecto.persistences.Horario;
import com.proyecto.persistences.Convenciones;
import com.proyecto.persistences.Docentes;
import com.proyecto.utilities.Intervalo;
import com.proyecto.utilities.Mensajes;
import com.proyecto.utilities.SessionUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class HorarioController implements Serializable {

    @EJB
    private HorarioFacade horarioFacade;

    @EJB
    private ActividadesFacade _actividadesFacade;
    
    @EJB
    private ConvencionesFacade _convencionesFacade;

    private Horario _objHorario;
    private FacesMessage message;
    private int _codigo;
    private List<Intervalo> _listInterval;
    private Intervalo[] _arrayInterval;
    private Docentes _currentDocente;
    private int _codigoAct;
    private double _totalHoras = 0.0;
  
    
    private enum Action {
        CREAR, ACTUALIZAR, NINGUNA
    };
    
    private Action _actualAction = null;
    public static final String _intervalos[] = {"6:00 a.m.  a 6:45 a.m.", "6:45 a.m. a 7:30 a.m.", "7:30 a.m. a 8:15 a.m", "8:15 a.m. a 9:00 a.m.", "9:00 a.m. a 9:45 a.m.", "9:45 a.m. a 10:30 a.m.", "10:30 a.m. a 11:15 a.m.", "11:15 a.m. a 12:00 a.m.", "12:00 p.m a 12:45 p.m.", "12:45 p.m. a 1:30 p.m.", "1:30 p.m. a 2:15 p.m.", "2:15 p.m. a 3:00 p.m.", "3:00 p.m. a 3:45 p.m.", "3:45 p.m. a 4:30 p.m.", "4:30 p.m. a 5:15 p.m.", "5:15 p.m. a 6:00 p.m.", "6:30 p.m. a 7:15 p.m.", "7:15 p.m. a 8:00 p.m.", "8:15 p.m. a 9:00 p.m.", "9:00 p.m. a 9:45 p.m."};
    public static final String _dias[] = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};

    public HorarioController() {
    }

    @PostConstruct
    public void init() {
        _currentDocente = (Docentes) SessionUtils.get("docente");
        _listInterval = new ArrayList<>();
        
        _actualAction = Action.NINGUNA;     
        organizarListas();
    }

    private void organizarListas() {
        List<Horario> listHorario = getListado();

        if (listHorario.size() > 0) {
            _objHorario = listHorario.get(0);
        } else {
            getCampo();
        }

        _arrayInterval = new Intervalo[_intervalos.length];

        for (int i = 0; i < _arrayInterval.length; i++) {
            _arrayInterval[i] = new Intervalo();
            _arrayInterval[i].setInitData(_intervalos[i], i);
        }

        for (Horario obj : listHorario) {           
            //cuadrando la lista de horarios        
            _arrayInterval[obj.getHora()].setDia(obj);
            
            //calculando horas
            /*if(obj.getCodActividad()!=null)
            {
                _totalHoras += obj.getCodActividad().getHoras();
            }*/
        }

        //convertir array a lista
        _listInterval = Arrays.asList(_arrayInterval);        
    }

    public String getHoraIntervalo(int index) {
        return _intervalos[index];
    }

    public Horario getCampo() {
        if (_objHorario == null) {
            _objHorario = new Horario();
        }
        return _objHorario;
    } 

    public void abrirCrear(Horario horTemp) {
        _actualAction = Action.CREAR;
        System.out.println("Horario Actual: " + _objHorario.getDia());
        System.out.println("Horario Nuevo: " + horTemp.getDia());

        _objHorario = horTemp;
        System.out.println("Horario Reasignado: " + _objHorario.getDia());
        Convenciones conve;

        if (_objHorario.getCodconvencion() == null) {
            conve = new Convenciones();
        } else {
            conve = _objHorario.getCodconvencion();
        }

        conve.setCodconvencion(0);
        _objHorario.setCodconvencion(conve);
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().execute("PF('eventDialog').show();");
    }

    public void closeDialog(Horario horaTemp) {
        _objHorario = horaTemp;
        System.out.println("HorarioController.closeDialog() -> Se cerro");
        borrar();
        organizarListas();
    }

    public void agregar() {
        String titulo, detalle;
        Convenciones convencion = _convencionesFacade.buscar(_codigo);
        _objHorario.setCodconvencion(convencion);
        Actividades actividad = _actividadesFacade.buscar(_codigoAct);
        _objHorario.setCodActividad(actividad);
        
        System.out.println("convencion " + _objHorario.getCodconvencion());

        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);
            _objHorario.setCoddocente(_currentDocente);

            System.out.println("docente " + _objHorario.getCoddocente().getNombres());

            horarioFacade.crear(_objHorario);
            _objHorario.setAsignado(true);
            Mensajes.exito(titulo, detalle);     
            /*RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);*/
        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            //message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Horario.class.getName()).log(Level.SEVERE, null, e);
        }
        //_objHorario = null;
        _actualAction = Action.NINGUNA;
    }

    //devuelve el dia dependiendo del index que le pasen
    public String getDia(int cod) {
        return _dias[cod - 1];
    }

    //devuelve el intervalo de hora dependiendo del index que le pasen
    public String getHora(int cod) {
        return _intervalos[cod];
    }

    public SelectItem[] combo(String texto) {
        return Formulario.addObject(horarioFacade.listado(), texto);
    }

    //obtiene el listado de horarios segun el docente de la sesion
    public List<Horario> getListado() {
        String cedula = _currentDocente.getCedula() + "";
        return horarioFacade.buscarCampo("_coddocente", cedula);
    }

    public void borrar() {
        String titulo, detalle;

        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);

            System.out.print("Borrar ---- " + _objHorario.getCodActividad());
            horarioFacade.borrar(_objHorario);
            Convenciones conve = new Convenciones();
            conve.setCodconvencion(0);
            _objHorario.setCodActividad(null);
            _objHorario.setCodconvencion(conve);
            _objHorario.setAsignado(false);
            
        } catch (Exception e) {
            System.out.print("HorarioController.borrar() -> Error al borrar ---- " + e.toString());
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);
            Logger.getLogger(Horario.class.getName()).log(Level.SEVERE, null, e);
        }
        
        _actualAction = Action.NINGUNA;
    }

    public void close() {
        System.out.println("Cerro Dialog");
    }

    //parece que no se usa
    public void mostrarMensaje() {
        System.out.println("HorarioController.MostrarMensaje");
        if (message != null) {
            FacesContext.getCurrentInstance().addMessage("mensajes", message);
        }
        message = null;
    }
            
    public void actualizandoMenu()
    {
        System.out.println("actualizandoMenu -> "+_objHorario.getCodconvencion().getCodconvencion());        
    }

    public void abrirActualizar(Horario objTemp) {
        
        _actualAction = Action.ACTUALIZAR;
        System.out.println("HorarioController.abrirActualizar() -> horario: " + objTemp.getCodconvencion().getNombre());
        
        _objHorario = objTemp;
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().execute("PF('eventDialog').show();");
    }
    
    public void actualizar() {
        String titulo, detalle;
        System.out.println("HorarioController.actualizar() -> nombre: " + _objHorario.getCodActividad());
        Convenciones convencion = _convencionesFacade.buscar(_codigo);
        _objHorario.setCodconvencion(convencion);
        Actividades actividad = _actividadesFacade.buscar(_codigoAct);
        _objHorario.setCodActividad(actividad);
        
        System.out.println("HorarioController.actualizar() -> nombre 2: " + _objHorario.getCodActividad());
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);
            System.out.println("HorarioController.actualizar() -> horario: " + _objHorario.getCodconvencion().getNombre());
            _objHorario.setCoddocente((Docentes) SessionUtils.get("docente"));
            horarioFacade.actualizar(_objHorario);
           

        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);
            Logger.getLogger(Horario.class.getName()).log(Level.SEVERE, null, e);
        }
        
        //_objHorario = null;
        _actualAction = Action.NINGUNA;
    }

    public SelectItem[] comboHoras() {
        //List<Convenciones> lista =_ejbFacade.listado(); 
        SelectItem[] listaItems = new SelectItem[_intervalos.length];
        int index = 0;
        for (String interv : _intervalos) {
            SelectItem item = new SelectItem(index, interv);
            listaItems[index] = item;
            index++;
        }

        return listaItems;
    }

    public SelectItem[] comboDias() {
        //List<Convenciones> lista =_ejbFacade.listado(); 
        SelectItem[] listaItems = new SelectItem[_dias.length];
        int index = 0;
        for (String nombre : _dias) {
            SelectItem item = new SelectItem(index, nombre);
            listaItems[index] = item;
            index++;
        }

        return listaItems;
    }

    public boolean esGuardar()
    {
        return _actualAction==Action.CREAR;
    }
    
    public boolean esActualizar()
    {
        return _actualAction==Action.ACTUALIZAR;
    }
    
    public double getTotalHoras() {
        _totalHoras=0;
        for (Horario obj : getListado()) {              
            //calculando horas
            if(obj.getCodActividad()!=null)
            {
                _totalHoras += obj.getCodActividad().getHoras();
            }
        }
        return _totalHoras;
    }

    public void setTotalHoras(double _totalHoras) {
        this._totalHoras = _totalHoras;
    }
    
    public void resetear() {
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

    public int getCodigoAct() {
        return _codigoAct;
    }

    public void setCodigoAct(int _codigoAct) {
        this._codigoAct = _codigoAct;
    }
    
    @FacesConverter(forClass = Horario.class, value = "horarioConverter")
    public static class HorarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try {
                if (value == null || value.length() == 0) {
                    return null;
                }

                Integer id = Integer.parseInt(value);
                HorarioController controller = (HorarioController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "horarioController");
                return controller.horarioFacade.buscar(id);
            } catch (NumberFormatException e) {
                Logger.getLogger(Horario.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Horario) {
                Horario obj = (Horario) value;
                return String.valueOf(obj.getCodhorario());
            }
            return null;
        }
    }
}
