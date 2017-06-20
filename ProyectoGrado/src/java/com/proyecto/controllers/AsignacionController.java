package com.proyecto.controllers;

import com.proyecto.facades.ActividadObligatoriaFacade;
import com.proyecto.facades.AsignacionFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.facades.PorcentajeFacade;
import com.proyecto.facades.TiempoAsignadoFacade;
import com.proyecto.persistences.ActividadObligatoria;
import com.proyecto.persistences.Asignacion;
import com.proyecto.persistences.Coordinacion;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Porcentaje;
import com.proyecto.persistences.TiempoAsignado;
import com.proyecto.utilities.Mensajes;
import com.proyecto.utilities.SessionUtils;
import com.proyecto.utilities.Totales;
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
    private double totalHoras;
    
    private Totales _totalesCalculados;
    private Totales _totalesEsperados;
    private List<Asignacion> _listadoAsign;

    public AsignacionController()
    {
        _totalesEsperados = new Totales();
        _totalesCalculados = new Totales();
    }

    public List<Asignacion> getListadoAsign() {
       
        Coordinacion coord = (Coordinacion) SessionUtils.get("coordinacion");
        List<Asignacion> listAsig = _ejbFacade.buscarA("_codcoordinacion", String.valueOf(coord.getCodcoordinacion()));
        
        
        System.out.println("entro a la funcion: "+listAsig.size());

        totalHC = 0;
        totalPC = 0;
        totalCap = 0;
        totalCD = 0;
        totalHI = 0;
        totalPS = 0;
        totalODA = 0;
        totalPlan = 0;
        totalvirt = 0;
        totalCom = 0;
        for (Asignacion asg : listAsig) {
            /*if (asg.getHorasclase() != null) {
                totalHC += asg.getHorasclase();
            }*/
            if (asg.getPreparacion() != null) {
                totalPC += asg.getPreparacion();
            }
            if (asg.getCapacitacion() != null) {
                totalCap += asg.getCapacitacion();
            }
            if (asg.getColectivo() != null) {
                totalCD += asg.getColectivo();
            }
            if (asg.getInvestigacion() != null) {
                totalHI += asg.getInvestigacion();
            }
            if (asg.getSocial() != null) {
                totalPS += asg.getSocial();
            }
            if (asg.getOda() != null) {
                totalODA += asg.getOda();
            }
            if (asg.getPlaneacion() != null) {
                totalPlan += asg.getPlaneacion();
            }
            if (asg.getVirtualidad() != null) {
                totalvirt += asg.getVirtualidad();
            }
            if (asg.getComites() != null) {
                totalCom += asg.getComites();
            }

            if(asg.getCoddocente().getTipocontrato()==1)
            {
                totalHC += 24;
            }else{
                totalHC += 12;
            }
        }                
        
        _totalesEsperados.setTotalHC(totalHC);
        System.out.println("tamaño de la lista " + coord.getAcreditacion());  
        
        if(coord.getAcreditacion() != null)
        {
                  
            _totalesEsperados.setTotalCom(coord.getComites()*totalHC/100);
            _totalesEsperados.setTotalPlan(coord.getAcreditacion()*totalHC/100);
            _totalesEsperados.setTotalODA(coord.getOda()*totalHC/100);
            _totalesEsperados.setTotalPS(coord.getExtension()*totalHC/100);
            _totalesEsperados.setTotalHI(coord.getInvestigacion()*totalHC/100);
            _totalesEsperados.setTotalVirt(coord.getVirtualidad()*totalHC/100);
        }       
            
        
        return listAsig;
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
                System.out.println("VA A AGREGAR UN DOCENTE " + docente);

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

        if (_listadoAsign == null) {
            _listadoAsign = _ejbFacade.listado();
        }

        actualizar(_listadoAsign.get(event.getRowIndex()));

        System.out.println("VALOR ANTES " + oldValue + " FILA " + event.getRowIndex() + " COLUMNA " + _listadoAsign.get(event.getRowIndex()).getCoddocente().getTipocontrato());
        System.out.println("VALOR DESPUES " + newValue + " OBJETO  " + _listadoAsign.get(event.getRowIndex()).getCodasg());

        if (_listadoAsign.get(event.getRowIndex()).getCapacitacion() == null) {
            _listadoAsign.get(event.getRowIndex()).setCapacitacion(0.0);
        }
        if (_listadoAsign.get(event.getRowIndex()).getHorasclase() == null) {
            _listadoAsign.get(event.getRowIndex()).setHorasclase(0.0);
        }
        if (_listadoAsign.get(event.getRowIndex()).getPreparacion() == null) {
            _listadoAsign.get(event.getRowIndex()).setPreparacion(0.0);
        }
        if (_listadoAsign.get(event.getRowIndex()).getColectivo() == null) {
            _listadoAsign.get(event.getRowIndex()).setColectivo(0.0);
        }
        if (_listadoAsign.get(event.getRowIndex()).getInvestigacion() == null) {
            _listadoAsign.get(event.getRowIndex()).setInvestigacion(0.0);
        }
        if (_listadoAsign.get(event.getRowIndex()).getSocial() == null) {
            _listadoAsign.get(event.getRowIndex()).setSocial(0.0);
        }
        if (_listadoAsign.get(event.getRowIndex()).getOda() == null) {
            _listadoAsign.get(event.getRowIndex()).setOda(0.0);
        }
        if (_listadoAsign.get(event.getRowIndex()).getPlaneacion() == null) {
            _listadoAsign.get(event.getRowIndex()).setPlaneacion(0.0);
        }
        if (_listadoAsign.get(event.getRowIndex()).getVirtualidad() == null) {
            _listadoAsign.get(event.getRowIndex()).setVirtualidad(0.0);
        }
        if (_listadoAsign.get(event.getRowIndex()).getComites() == null) {
            _listadoAsign.get(event.getRowIndex()).setComites(0.0);
        }

        if (_listadoAsign.get(event.getRowIndex()).getCoddocente().getTipocontrato() == 1) {

            totalHoras = _listadoAsign.get(event.getRowIndex()).getCapacitacion() + _listadoAsign.get(event.getRowIndex()).getHorasclase()
                    + _listadoAsign.get(event.getRowIndex()).getPreparacion() + _listadoAsign.get(event.getRowIndex()).getColectivo()
                    + _listadoAsign.get(event.getRowIndex()).getInvestigacion() + _listadoAsign.get(event.getRowIndex()).getSocial()
                    + _listadoAsign.get(event.getRowIndex()).getOda() + _listadoAsign.get(event.getRowIndex()).getPlaneacion()
                    + _listadoAsign.get(event.getRowIndex()).getVirtualidad() + _listadoAsign.get(event.getRowIndex()).getComites();

            System.out.println("antes de ir a la funcion");
            getListadoAsign();

        } else if (_listadoAsign.get(event.getRowIndex()).getCoddocente().getTipocontrato() == 2) {
            totalHoras = _listadoAsign.get(event.getRowIndex()).getCapacitacion() + _listadoAsign.get(event.getRowIndex()).getHorasclase()
                    + _listadoAsign.get(event.getRowIndex()).getPreparacion() + _listadoAsign.get(event.getRowIndex()).getColectivo()
                    + _listadoAsign.get(event.getRowIndex()).getInvestigacion() + _listadoAsign.get(event.getRowIndex()).getSocial()
                    + _listadoAsign.get(event.getRowIndex()).getOda() + _listadoAsign.get(event.getRowIndex()).getPlaneacion()
                    + _listadoAsign.get(event.getRowIndex()).getVirtualidad() + _listadoAsign.get(event.getRowIndex()).getComites();

            System.out.println("antes de ir a la funcion");
            getListadoAsign();
        }

        System.out.println("antes de ir a la funcion");
        getListadoAsign();

        return "/actividades/listado";
    }

    public void controlHoras() {

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

    public double getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(double totalHoras) {
        this.totalHoras = totalHoras;
    }

}
