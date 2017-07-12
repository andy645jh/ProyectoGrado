package com.proyecto.controllers;

import com.proyecto.facades.AsignacionFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.persistences.Asignacion;
import com.proyecto.persistences.Coordinacion;
import com.proyecto.persistences.TiempoAsignado;
import com.proyecto.utilities.Mensajes;
import com.proyecto.utilities.SessionUtils;
import com.proyecto.utilities.Totales;
import java.io.Serializable;
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
    
    private double _calculadoDC;
    private double _esperadoDC;
    
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
           
        //System.out.println("AsignacionController.getListadoAsign -> entro a la funcion: "+listAsig.size());
        Coordinacion coord = (Coordinacion) SessionUtils.get("coordinacion");
        if(_listadoAsign==null)
        {
            _listadoAsign = _ejbFacade.buscarA("_codcoordinacion", String.valueOf(coord.getCodcoordinacion()));
        } 
        //calculate();
        return _listadoAsign;
    }
       
    public void calculate(Object valueOfThisSorting)
    {
        Coordinacion coord = (Coordinacion) SessionUtils.get("coordinacion");
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
        _calculadoDC =0;
        _esperadoDC =0;
                
        System.out.println("-----------------");
        for (Asignacion asg : _listadoAsign) {
            if (asg.getHorasclase() != null) {
                totalHC += asg.getHorasclase();
            }
            if (asg.getPreparacion() != null) {
                totalPC += asg.getPreparacion();
            }
            if (asg.getCapacitacion() != null) {
                totalCap += asg.getCapacitacion();
            }
            if (asg.getColectivo() != null) {
                
                //System.out.println("Colectivo: "+asg.getColectivo()+" -- total: "+totalCD);
                totalCD += asg.getColectivo();
                //_calculadoDC += asg.getColectivo();
                setCalculadoDC(_calculadoDC + asg.getColectivo());
                //System.out.print(" -- total despues: "+totalCD);
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
        
        //seteando los valores acumulados
        _totalesCalculados.setTotalHC(totalHC);
        _totalesCalculados.setTotalCom(totalCom);
        _totalesCalculados.setTotalVirt(totalvirt);
        _totalesCalculados.setTotalPlan(totalPlan);
        _totalesCalculados.setTotalODA(totalODA);
        _totalesCalculados.setTotalPS(totalPS);
        _totalesCalculados.setTotalHI(totalHI);
        _totalesCalculados.setTotalCD(totalCD);
        _totalesCalculados.setTotalPC(totalPC);
        _totalesCalculados.setTotalCap(totalCap);
                
        _totalesEsperados.setTotalHC(totalHC);
        System.out.println("Calculado: "+_calculadoDC);
        if(coord.isAsignado())
        {                  
            _totalesEsperados.setTotalCom(coord.getComites()*totalHC/100);
            _totalesEsperados.setTotalPlan(coord.getAcreditacion()*totalHC/100);
            _totalesEsperados.setTotalODA(coord.getOda()*totalHC/100);
            _totalesEsperados.setTotalPS(coord.getExtension()*totalHC/100);
            _totalesEsperados.setTotalHI(coord.getInvestigacion()*totalHC/100);
            _totalesEsperados.setTotalVirt(coord.getVirtualidad()*totalHC/100);
        }                  
        //System.out.println("AsignacionController.getListadoAsign -> tamaño de la lista " + _totalesEsperados.getTotalHC());  
        
    }
    
    public void abrirCrear() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/tiempodoc/crear", options, null);
//        return "/tiempodoc/crear";
    }

    public void actualizar(Asignacion _obj) 
    {
        
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
            Logger.getLogger(TiempoAsignado.class.getName()).log(Level.SEVERE, null, e);

        }

//        RequestContext context = RequestContext.getCurrentInstance();
//        context.closeDialog(null);
    }

    public void onCellEdit(CellEditEvent event) {

        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (_listadoAsign == null) {
            _listadoAsign = _ejbFacade.listado();
        }
        //Asignacion asigTemp2 = (Asignacion) event.;
        Asignacion asigTemp = (Asignacion) _listadoAsign.get(event.getRowIndex());
        System.out.println("AsignacionController.onCellEdit -> Nuevo Valor: "+asigTemp.toString());
        
        actualizar(_listadoAsign.get(event.getRowIndex()));

        System.out.println("AsignacionController.onCellEdit -> VALOR ANTES " + oldValue );
        System.out.println("AsignacionController.onCellEdit -> VALOR DESPUES " + newValue);
        
        calculate(null);       
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

    public void setListadoAsign(List<Asignacion> lista) {
        this._listadoAsign = lista;
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

    public Totales getTotalesCalculados() {
        return _totalesCalculados;
    }

    public void setTotalesCalculados(Totales _totalesCalculados) {
        this._totalesCalculados = _totalesCalculados;
    }

    public Totales getTotalesEsperados() {
        return _totalesEsperados;
    }

    public void setTotalesEsperados(Totales _totalesEsperados) {
        this._totalesEsperados = _totalesEsperados;
    }

    public double getCalculadoDC() {
        return _calculadoDC;
    }

    public void setCalculadoDC(double _calculadoDC) {
        this._calculadoDC = _calculadoDC;
    }

}
