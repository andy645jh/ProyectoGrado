package com.proyecto.controllers;

import com.proyecto.utilities.Formulario;
import com.proyecto.utilities.Mensajes;
import com.proyecto.facades.ActividadesFacade;
import com.proyecto.facades.AsignacionFacade;
import com.proyecto.facades.ProductosFacade;
import com.proyecto.facades.TipoModalidadesFacade;
import com.proyecto.persistences.Actividades;
import com.proyecto.persistences.Asignacion;
import com.proyecto.persistences.Coordinacion;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Horario;
import com.proyecto.persistences.Productos;
import com.proyecto.persistences.TipoModalidades;
import com.proyecto.utilities.SessionUtils;
import java.io.Serializable;
import java.util.ArrayList;
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
import javax.sound.midi.Soundbank;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class EvaluarController implements Serializable {

    @EJB
    private ActividadesFacade _actividadesFacade;

    

    @EJB
    private ProductosFacade _productosFacade;
    
    private Productos _prod;

    private Productos _obj;

    private String cedula = "";

    private String _rutaTxt = "/com/java/utilities/txtActividades";
    private String _titulo = "Operacion";
    private String _mensajeCorrecto = "Se ha realizado correctamente";
    private String _mensajeError = "No se completo la operacion";
    private FacesMessage message;
    private int _codigo;
    private double _max;

    private Asignacion _asignacion;
    private Coordinacion _coordinacion;
    private Docentes _docente;
    private double _totalHoras = 0.0;
    
    @PostConstruct
    private void init() {
        _coordinacion = (Coordinacion) SessionUtils.get("coordinacion");
        _docente = (Docentes) SessionUtils.get("docente");

    }

    public EvaluarController() {
    }

    public Productos getCampo() {
        if (_obj == null) {
            _obj = new Productos();
        }
        return _obj;
    }

    public void mostrarMensaje() {
        if (message != null) {
            System.out.println("ES DIFERENTE DE NULL");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, message);
        }
    }


    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    
   

    public String btnBuscar() {
//        cedula = _obj.getCoddocente().getCedula() + "";
        System.out.println("CEDULA EVALUAR "+cedula);
        return "evaluar/listado";
    }

    public List<Productos> getListarEvaluaciones() {
        if (cedula == "") {
            return new ArrayList<Productos>();
        } else {
            System.out.println("CEDULA EVALUAR2 "+cedula);
            return _productosFacade.buscarCampo("_coddocente", cedula);
        }
    }


    public void abrirEvaluacion(Productos objTemp) {

        _obj = objTemp;
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);

        RequestContext.getCurrentInstance().openDialog("actualizar", options, null);
    }

    public void hixoClick() {
        System.out.println("HIXO CLICL EN EL BOTON ");
    }

    public void guardarEvaluacion() {
        String titulo, detalle;
        Actividades act = _obj.getCodactividad();

        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");

            _productosFacade.actualizar(_obj);
            _actividadesFacade.actualizar(act);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);

        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);
            Logger.getLogger(Actividades.class.getName()).log(Level.SEVERE, null, e);

        }

        RequestContext context = RequestContext.getCurrentInstance();
        context.closeDialog(null);
    }

    public void error() {
        String titulo, detalle;
        titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
        detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
        Mensajes.error(titulo, detalle);
    }

   

    public void resetear() {
        _obj = null;
    }

    public int getCodigo() {
        return _codigo;
    }

    public void setCodigo(int _codigo) {
        this._codigo = _codigo;
    }

    public Coordinacion getCoordinacion() {
        return _coordinacion;
    }

    

}
