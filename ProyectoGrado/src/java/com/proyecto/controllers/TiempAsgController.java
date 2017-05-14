package com.proyecto.controllers;

import com.proyecto.facades.ActividadObligatoriaFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.facades.PorcentajeFacade;
import com.proyecto.facades.TiempoAsignadoFacade;
import com.proyecto.persistences.ActividadObligatoria;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Porcentaje;
import com.proyecto.persistences.TiempoAsignado;
import com.proyecto.utilities.Mensajes;
import javax.inject.Named;
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
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Usuario
 */
@ManagedBean
@SessionScoped

public class TiempAsgController implements Serializable {

    private TiempoAsignado _obj;
    @EJB
    private TiempoAsignadoFacade _ejbFacade;
    @EJB
    private DocentesFacade _facadeDoc;
    @EJB
    private PorcentajeFacade _facadePorcentaje;
    @EJB
    private ActividadObligatoriaFacade _facadeActDoc;
    private FacesMessage message;
    private int _codPorcentaje;
    private int codDocente;
    private int codActDocencia;
    private int totalHC;

    public TiempAsgController() {
    }

    public List<TiempoAsignado> getListado() {
        
        
        
        
        totalHC=87;
        return _ejbFacade.listadoAgrupado();
    }

    public void abrirCrear() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/tiempodoc/crear", options, null);
//        return "/tiempodoc/crear";
    }

    public void agregar(String[] selectedDocentes) {

//        Docentes d = _facadeDoc.buscar(codDocente);
//        Porcentaje p = _facadePorcentaje.buscar(_codPorcentaje);
//        ActividadObligatoria a = _facadeActDoc.buscar(codActDocencia);
//        
//        _obj.setCoddocente(d);
//        _obj.setCodporcentaje(p);
//        _obj.setCodobliatoria(a);
        
        System.out.println("FUNCUIN AGREGAR");
        String titulo, detalle;

        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);

            for (String docente : selectedDocentes) {
                System.out.println("VA A AGREGAR UN DOCENTE "+docente);
//                _obj = new TiempoAsignado();
//                _obj.setCoddocente(null);
            }

//            _ejbFacade.crear(_obj);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);

            Logger.getLogger(TiempoAsignado.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public void borrar(TiempoAsignado faceObj) {
        String titulo, detalle;

        try {
            _ejbFacade.borrar(faceObj);
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarExitoso");
            Mensajes.exito(titulo, detalle);

        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(TiempoAsignado.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void abrirActualizar(TiempoAsignado objtemp) {

        _obj = objtemp;
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/tiempodoc/actualizar", options, null);
    }

    public void actualizar() {
        String titulo, detalle;
        Docentes d = _facadeDoc.buscar(codDocente);
        Porcentaje p = _facadePorcentaje.buscar(_codPorcentaje);
        ActividadObligatoria a = _facadeActDoc.buscar(codActDocencia);

        _obj.setCoddocente(d);
        _obj.setCodporcentaje(p);
        _obj.setCodobliatoria(a);

        try {

            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
            _ejbFacade.actualizar(_obj);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);

        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);
            Logger.getLogger(TiempoAsignado.class.getName()).log(Level.SEVERE, null, e);

        }

        RequestContext context = RequestContext.getCurrentInstance();
        context.closeDialog(null);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public TiempoAsignado getObj() {

        if (_obj == null) {
            _obj = new TiempoAsignado();
        }
        return _obj;
    }

    public void setObj(TiempoAsignado _obj) {
        this._obj = _obj;
    }

    public int getCodDocente() {
        return codDocente;
    }

    public void setCodDocente(int codDocente) {
        this.codDocente = codDocente;
    }

    public int getCodActDocencia() {
        return codActDocencia;
    }

    public void setCodActDocencia(int codActDocencia) {
        this.codActDocencia = codActDocencia;
    }

    public int getCodPorcentaje() {
        return _codPorcentaje;
    }

    public void setCodPorcentaje(int _codigito) {
        this._codPorcentaje = _codigito;
    }

    

}
