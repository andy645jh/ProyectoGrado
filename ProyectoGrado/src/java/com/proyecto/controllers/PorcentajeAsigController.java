package com.proyecto.controllers;

import com.proyecto.facades.ActividadMisionalFacade;
import com.proyecto.facades.AsignacionFacade;
import com.proyecto.facades.CoordinacionFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.facades.PorcentajeAsigFacade;
import com.proyecto.persistences.Coordinacion;
import com.proyecto.persistences.PorcentajeAsig;
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
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 *
 * @author User
 */
@ManagedBean
@SessionScoped
public class PorcentajeAsigController implements Serializable {

    @EJB
    private PorcentajeAsigFacade _ejbFacade;
    private PorcentajeAsig _obj;
    private FacesMessage _message;  

    @EJB
    private AsignacionFacade _asignacionFacade;

    @EJB
    private DocentesFacade _docenteFacade;

    @EJB
    private CoordinacionFacade _coordinacionFacade;

    @EJB
    private ActividadMisionalFacade _actMisionalFacade;
    private TiempAsgController _asigController;  
    
    public PorcentajeAsigController() {      
        
    }

    public PorcentajeAsig getCampo() {
        if (_obj == null) {
            _obj = new PorcentajeAsig();
        }
        return _obj;
    }

    public List<PorcentajeAsig> getListado() {
        return _ejbFacade.listado();
    }

    public PorcentajeAsig getPocentajeAsig() {
        return _ejbFacade.buscar(1);
    }
    
    public void abrirCrear() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/porcentaje/crear", options, null);
    }

    public void agregar()
    {
        
        String titulo, detalle;       
        
        Coordinacion coord = (Coordinacion) SessionUtils.get("coordinacion");
        _obj.setCodcoordinacion(coord);
        System.out.println("COORDINACION " + coord);
        
        //_ejbFacade.buscar(this)
        try {
            if(_obj.getCodPorcentajeAsig()==0)
            {
                //crear            
                System.out.println("CREAR");
                _ejbFacade.crear(_obj);
                titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
                detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            }else{
                //actualizar
                System.out.println("ACTUALIZAR");
                _ejbFacade.actualizar(_obj);
                titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
                detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
            }
                        
            _message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);
            //System.out.println("coordinacion =" + coordinacion + " investigacion= extension=" + actMisionalexten + " comite=" + actMisionalcom + " ODA=" + actMisionalODA + " acreditacion=" + actMisionalacred + " virtualidad=" + actMisionalVirt);
           
            RequestContext context = RequestContext.getCurrentInstance();
            //context.closeDialog(null);
        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            _message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);
            RequestContext context = RequestContext.getCurrentInstance();
            //context.closeDialog(null);

            Logger.getLogger(PorcentajeAsig.class.getName()).log(Level.SEVERE, null, e);

        }
//        System.out.println("LO Q HAY EN ACT MISIONAL " + _obj.getCodmisional());
        /*try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
//            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);
            //System.out.println("coordinacion =" + coordinacion + " investigacion= extension=" + actMisionalexten + " comite=" + actMisionalcom + " ODA=" + actMisionalODA + " acreditacion=" + actMisionalacred + " virtualidad=" + actMisionalVirt);
           
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            _message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);

            Logger.getLogger(PorcentajeAsig.class.getName()).log(Level.SEVERE, null, e);

        }*/
    }

    public void abrirActualizar(PorcentajeAsig objtemp) {

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
            _message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);

        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            _message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);
            Logger.getLogger(PorcentajeAsig.class.getName()).log(Level.SEVERE, null, e);

        }

        RequestContext context = RequestContext.getCurrentInstance();
        context.closeDialog(null);
    }
/*
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
*/
}
