package com.proyecto.controllers;

import com.proyecto.facades.ActividadesFacade;
import com.proyecto.facades.CoordinacionFacade;
import com.proyecto.facades.FacultadFacade;
import com.proyecto.facades.SemanaFacade;
import com.proyecto.persistences.Actividades;
import com.proyecto.persistences.Convenciones;
import com.proyecto.persistences.Coordinacion;
import com.proyecto.persistences.Facultad;
import com.proyecto.persistences.Semana;
import com.proyecto.utilities.Formulario;
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
public class SemanaController implements Serializable {

    @EJB
    private SemanaFacade _ejbFacade;
    private Semana _obj;
    private FacesMessage message;

    public SemanaController() {
    }

    public Semana getCampo() {
        if (_obj == null) {
            _obj = new Semana();
        }
        return _obj;
    }

    public void resetear() {
        _obj = null;
    }

    public List<Semana> getListado() {
        return _ejbFacade.listado();
    }
    
    
    public void saveMessage() {        
        FacesContext context = FacesContext.getCurrentInstance();     
        context.addMessage(null, message);
    }



    public void abrirCrear() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("height", 300);
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/semana/crear", options, null);
    }

    public void agregar() {
        String titulo, detalle;

        try {
            _ejbFacade.crear(_obj);
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);       
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
            Logger.getLogger(Coordinacion.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public void borrar(Semana faceObj) {
        String titulo, detalle;
        System.out.println("VA A BORRAR");

        try {
            _ejbFacade.borrar(faceObj);
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarExitoso");
            Mensajes.exito(titulo, detalle);

        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Coordinacion.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void abrirActualizar(Semana objtemp) {

        _obj = objtemp;
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("height", 300);
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/semana/actualizar", options, null);
    }

    public void actualizar() {
        String titulo, detalle;
        try {

            _ejbFacade.actualizar(_obj);
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);

        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            Logger.getLogger(Coordinacion.class.getName()).log(Level.SEVERE, null, e);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);
        }

    }

}
