package com.proyecto.controllers;

import com.proyecto.facades.ActividadObligatoriaFacade;
import com.proyecto.facades.AsignacionFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.facades.PorcentajeFacade;
import com.proyecto.facades.TiempoAsignadoFacade;
import com.proyecto.persistences.ActividadObligatoria;
import com.proyecto.persistences.Asignacion;
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

public class AsignacionController implements Serializable {

    private Asignacion _obj;
    @EJB
    private AsignacionFacade _ejbFacade;
    @EJB
    private DocentesFacade _facadeDoc;
   
    private FacesMessage message;
    private int _codPorcentaje;
    private int codDocente;
    private int codActDocencia;
    private double totalHC;
    private double totalPC;
    private double totalCap;
    private double totalCD;
    private double totalHI;
    private double totalPS;
    private double totalODA;
    private double totalPlan;
    private double totalvirt;
    private double totalCom;
    private String prueba;
    private List<Asignacion> _listadoAsign;
    
    public AsignacionController() {
    }

    public List<Asignacion> getListadoAsign() {
          /*prueba="prueba";*/
        totalHC=0;
        totalPC=0;
        totalCap=0;
        totalCD=0;
        totalHI=0;
        totalPS=0;
        totalODA=0;
        totalPlan=0;
        totalvirt=0;
        totalCom=0;
        for (Asignacion asg : _ejbFacade.listado()) {
            if(asg.getHorasclase()!=null){totalHC+=asg.getHorasclase();}
            if(asg.getPreparacion()!=null){totalPC+=asg.getPreparacion();}
            if(asg.getCapacitacion()!=null){totalCap+=asg.getCapacitacion();}
            if(asg.getColectivo()!=null){totalCD+=asg.getColectivo();}
            if(asg.getInvestigacion()!=null){totalHI+=asg.getInvestigacion();}
            if(asg.getSocial()!=null){totalPS+=asg.getSocial();}
            if(asg.getOda()!=null){totalODA+=asg.getOda();}
            if(asg.getPlaneacion()!=null){ totalPlan+=asg.getPlaneacion();}
            if(asg.getVirtualidad()!=null){totalvirt+=asg.getVirtualidad();}
            if(asg.getComites()!=null){totalCom+=asg.getComites();}
            
        }
        if(_listadoAsign==null) _listadoAsign = _ejbFacade.listado();
        return _listadoAsign;
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

    public void borrar(Asignacion faceObj) {
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

    public void abrirActualizar(Asignacion objtemp) {

        _obj = objtemp;
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/tiempodoc/actualizar", options, null);
    }

    public void actualizar(Asignacion _obj) {
        String titulo, detalle;
//        Docentes d = _facadeDoc.buscar(codDocente);
//
//        _obj.setCoddocente(d);

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

//        RequestContext context = RequestContext.getCurrentInstance();
//        context.closeDialog(null);
    }

    public String onCellEdit(CellEditEvent event) {
        
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
         if(_listadoAsign==null) _listadoAsign = _ejbFacade.listado();
         
         actualizar(_listadoAsign.get(event.getRowIndex()));
        
        System.out.println("VALOR ANTES "+oldValue+" FILA "+event.getRowIndex()+" COLUMNA "+_listadoAsign.get(event.getRowIndex()).getCoddocente());
        System.out.println("VALOR DESPUES "+newValue+" OBJETO  "+_listadoAsign.get(event.getRowIndex()).getCodasg());
        
//        if (newValue != null && !newValue.equals(oldValue)) {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
        
        return "/asignacion/listado";
    }
    
    public void controlHoras(){
        
    }
    

    public Asignacion getObj() {
        return _obj;
    }

    public void setObj(Asignacion _obj) {
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

    public double getTotalHC() {
        return totalHC;
    }

    public void setTotalHC(double totalHC) {
        this.totalHC = totalHC;
    }

    public double getTotalPC() {
        return totalPC;
    }

    public void setTotalPC(double totalPC) {
        this.totalPC = totalPC;
    }

    public double getTotalCap() {
        return totalCap;
    }

    public void setTotalCap(double totalCap) {
        this.totalCap = totalCap;
    }

    public double getTotalCD() {
        return totalCD;
    }

    public void setTotalCD(double totalCD) {
        this.totalCD = totalCD;
    }

    public double getTotalHI() {
        return totalHI;
    }

    public void setTotalHI(double totalHI) {
        this.totalHI = totalHI;
    }

    public double getTotalPS() {
        return totalPS;
    }

    public void setTotalPS(double totalPS) {
        this.totalPS = totalPS;
    }

    public double getTotalODA() {
        return totalODA;
    }

    public void setTotalODA(double totalODA) {
        this.totalODA = totalODA;
    }

    public double getTotalPlan() {
        return totalPlan;
    }

    public void setTotalPlan(double totalPlan) {
        this.totalPlan = totalPlan;
    }

    public double getTotalvirt() {
        return totalvirt;
    }

    public void setTotalvirt(double totalvirt) {
        this.totalvirt = totalvirt;
    }

    public double getTotalCom() {
        return totalCom;
    }

    public void setTotalCom(double totalCom) {
        this.totalCom = totalCom;
    }

    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }    
}
