package com.proyecto.controllers;

import com.proyecto.facades.ActividadMisionalFacade;
import com.proyecto.facades.AsignacionFacade;
import com.proyecto.facades.CoordinacionFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.facades.PorcentajeFacade;
import com.proyecto.facades.TiempoAsignadoFacade;
import com.proyecto.persistences.ActividadMisional;
import com.proyecto.persistences.Asignacion;
import com.proyecto.persistences.Coordinacion;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Docentes_;
import com.proyecto.persistences.Porcentaje;
import com.proyecto.persistences.TiempoAsignado;
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
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 *
 * @author User
 */
@ManagedBean
@SessionScoped
public class PorcentajeController implements Serializable {

    @EJB
    private PorcentajeFacade _ejbFacade;
    private Porcentaje _obj;
    private FacesMessage message;
    private int _codCoordinacion;
    private int _codActInv;
    private int _codActExt;
    private int _codActCom;
    private int _codActODA;
    private int _codActAcrd;
    private int _codActVirt;
    private double _porcentaje_investigacion;
    private double _porcentaje_extension;
    private double _porcentaje_comites;
    private double _porcentaje_ODA;
    private double _porcentaje_acreditacion;
    private double _porcentaje_virtualidad;
    private String[] selectedDocentes;

    @EJB
    private AsignacionFacade _asignacionFacade;

    @EJB
    private DocentesFacade _docenteFacade;

    @EJB
    private CoordinacionFacade _coordinacionFacade;

    @EJB
    private ActividadMisionalFacade _actMisionalFacade;
    private TiempAsgController _asigController;

    public PorcentajeController() {
        _codActInv = 1;
        _codCoordinacion = 1;
        _codActExt = 4;
        _codActCom = 5;
        _codActODA = 6;
        _codActAcrd = 7;
        _codActVirt = 8;

    }

    public Porcentaje getCampo() {
        if (_obj == null) {
            _obj = new Porcentaje();
        }
        return _obj;
    }

    public List<Porcentaje> getListado() {
        return _ejbFacade.listado();
    }

    public void abrirCrear() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/porcentaje/crear", options, null);
    }

    public void agregar() {
        String titulo, detalle;

//
//        System.out.println("LO Q HAY EN COORDI " + _obj.getCodcoordinacion());
//        System.out.println("LO Q HAY EN ACT MISIONAL " + _obj.getCodmisional());
        try {
            Coordinacion coordinacion = _coordinacionFacade.buscar(_codCoordinacion);

            ActividadMisional actMisionalInv = _actMisionalFacade.buscar(_codActInv);
            ActividadMisional actMisionalexten = _actMisionalFacade.buscar(_codActExt);
            ActividadMisional actMisionalcom = _actMisionalFacade.buscar(_codActCom);
            ActividadMisional actMisionalODA = _actMisionalFacade.buscar(_codActODA);
            ActividadMisional actMisionalacred = _actMisionalFacade.buscar(_codActAcrd);
            ActividadMisional actMisionalVirt = _actMisionalFacade.buscar(_codActVirt);
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
//            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);
            System.out.println("coordinacion =" + coordinacion + " investigacion= extension=" + actMisionalexten + " comite=" + actMisionalcom + " ODA=" + actMisionalODA + " acreditacion=" + actMisionalacred + " virtualidad=" + actMisionalVirt);

            if (_porcentaje_investigacion != 0.0 && actMisionalInv != null) {
                _obj = new Porcentaje();
                System.out.println("INVESTIGACION " + actMisionalInv.getNombre());
                _obj.setCodmisional(actMisionalInv);
                _obj.setPorcentaje(_porcentaje_investigacion);
                _obj.setCodcoordinacion(coordinacion);
                _ejbFacade.crear(_obj);
                
            }
            if (_porcentaje_extension != 0.0) {
                _obj = new Porcentaje();
                _obj.setCodmisional(actMisionalexten);
                _obj.setPorcentaje(_porcentaje_extension);
                _obj.setCodcoordinacion(coordinacion);
                _ejbFacade.crear(_obj);
                
            }
            if (_porcentaje_comites != 0.0) {
                _obj = new Porcentaje();
                _obj.setCodmisional(actMisionalcom);
                _obj.setPorcentaje(_porcentaje_comites);
                _obj.setCodcoordinacion(coordinacion);
                _ejbFacade.crear(_obj);
               
            }
            if (_porcentaje_ODA != 0.0) {
                _obj = new Porcentaje();
                _obj.setCodmisional(actMisionalODA);
                _obj.setPorcentaje(_porcentaje_ODA);
                _obj.setCodcoordinacion(coordinacion);
                _ejbFacade.crear(_obj);
               
            }
            if (_porcentaje_acreditacion != 0.0) {
                _obj = new Porcentaje();
                _obj.setCodmisional(actMisionalacred);
                _obj.setPorcentaje(_porcentaje_acreditacion);
                _obj.setCodcoordinacion(coordinacion);
                _ejbFacade.crear(_obj);
                
            }
            if (_porcentaje_virtualidad != 0.0) {
                _obj = new Porcentaje();
                _obj.setCodmisional(actMisionalVirt);
                _obj.setPorcentaje(_porcentaje_virtualidad);
                _obj.setCodcoordinacion(coordinacion);
                _ejbFacade.crear(_obj);
               
            }
             for (String docente : selectedDocentes) {
                    System.out.println("VA A AGREGAR UN DOCENTE " + docente);
                    Docentes d = _docenteFacade.buscar(Integer.parseInt(docente));
                    System.out.println("DOCENTE ENCONTRADO " + d);
                    Asignacion t = new Asignacion();
                    t.setCoddocente(d);
                    //tiempo completo
                    if(d.getTipocontrato()==1){
                        t.setHorasclase(24.0);
                        t.setPreparacion(4.0);
                        t.setCapacitacion(4.0);
                    }else if(d.getTipocontrato()==2){
                        t.setHorasclase(12.0);
                        t.setPreparacion(2.0);
                        t.setCapacitacion(0.0);
                    }
                    
                    t.setTotalinv(_porcentaje_investigacion*120);
                    t.setTotalsocial(_porcentaje_extension*120);
                    t.setTotaloda(_porcentaje_ODA*120);
                    t.setTotalplan(_porcentaje_acreditacion*120);
                    t.setTotalvirt(_porcentaje_virtualidad*120);
                    t.setTotalcom(_porcentaje_comites*120);
                    _asignacionFacade.crear(t);
                }
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);

            Logger.getLogger(Porcentaje.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public void abrirActualizar(Porcentaje objtemp) {

        _obj = objtemp;
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/actmisional/actualizar", options, null);
    }

    public void actualizar() {
        String titulo, detalle;

        try {

            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
            _ejbFacade.actualizar(_obj);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);

        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);
            Logger.getLogger(Porcentaje.class.getName()).log(Level.SEVERE, null, e);

        }

        RequestContext context = RequestContext.getCurrentInstance();
        context.closeDialog(null);
    }

    public SelectItem[] combo(String texto) {
        List<Porcentaje> lista = _ejbFacade.listado();
        SelectItem[] listaItems = new SelectItem[lista.size()];
        int index = 0;
        for (Porcentaje porcentaje : lista) {
            SelectItem item = new SelectItem(porcentaje.getCodporcentaje(), porcentaje.getPorcentaje() + "");

            listaItems[index] = item;
            index++;
        }
        System.out.println("LISTADO " + lista.size());
        return listaItems;
    }

    public int getCodCoordinacion() {
        return _codCoordinacion;
    }

    public void setCodCoordinacion(int _codCoordinacion) {
        this._codCoordinacion = _codCoordinacion;
    }

    public double getPorcentaje_investigacion() {
        return _porcentaje_investigacion;
    }

    public void setPorcentaje_investigacion(double _porcentaje_investigacion) {
        this._porcentaje_investigacion = _porcentaje_investigacion;
    }

    public double getPorcentaje_extension() {
        return _porcentaje_extension;
    }

    public void setPorcentaje_extension(double _porcentaje_extension) {
        this._porcentaje_extension = _porcentaje_extension;
    }

    public double getPorcentaje_comites() {
        return _porcentaje_comites;
    }

    public void setPorcentaje_comites(double _porcentaje_comites) {
        this._porcentaje_comites = _porcentaje_comites;
    }

    public double getPorcentaje_ODA() {
        return _porcentaje_ODA;
    }

    public void setPorcentaje_ODA(double _porcentaje_ODA) {
        this._porcentaje_ODA = _porcentaje_ODA;
    }

    public double getPorcentaje_acreditacion() {
        return _porcentaje_acreditacion;
    }

    public void setPorcentaje_acreditacion(double _porcentaje_acreditacion) {
        this._porcentaje_acreditacion = _porcentaje_acreditacion;
    }

    public double getPorcentaje_virtualidad() {
        return _porcentaje_virtualidad;
    }

    public void setPorcentaje_virtualidad(double _porcentaje_virtualidad) {
        this._porcentaje_virtualidad = _porcentaje_virtualidad;
    }

    public String[] getSelectedDocentes() {
        return selectedDocentes;
    }

    public void setSelectedDocentes(String[] selectedDocentes) {
        this.selectedDocentes = selectedDocentes;
    }

}
