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
import java.util.ArrayList;
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
import javax.faces.context.FacesContextFactory;
import org.primefaces.component.api.UIData;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.data.FilterEvent;

/**
 *
 * @author Usuario
 */
@ManagedBean
@SessionScoped
public class AsignacionController implements Serializable {

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

    private Coordinacion _coordinacion;

    private Totales _totalesCalculados;
    private Totales _totalesEsperados;
    private List<Asignacion> _listadoAsign;
    private List<Double> _listSum;

    public AsignacionController() {
        _totalesEsperados = new Totales();
        _totalesCalculados = new Totales();

        _coordinacion = (Coordinacion) SessionUtils.get("coordinacion");
    }

    public List<Asignacion> getListadoAsign() {

        if (_listadoAsign == null) {
            _listadoAsign = _ejbFacade.buscarA("_codcoordinacion", String.valueOf(_coordinacion.getCodcoordinacion()));
            _listSum = new ArrayList<>();

            for (Asignacion asigTemp : _listadoAsign) {
                _listSum.add(asigTemp.getSumatoria());
            }
        }
        calculate();
        System.out.println("Pide Listado: " + _listSum.size());
        return _listadoAsign;
    }

    public void filterListener(FilterEvent filter) {
        System.out.println("AsignacionController.filter -> " + filter.toString());
    }

    public double getCalculate(Asignacion asigTemp) {
        double sum = asigTemp.getCapacitacion() + asigTemp.getColectivo() + asigTemp.getComites();
        sum += asigTemp.getHorasclase() + asigTemp.getInvestigacion() + asigTemp.getOda() + asigTemp.getSocial();
        sum += asigTemp.getPlaneacion() + asigTemp.getPreparacion() + asigTemp.getVirtualidad();
        return sum;
    }

    public void calculate() {

        //_coordinacion = (Coordinacion) SessionUtils.get("coordinacion");
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

            if (asg.getCoddocente().getTipocontrato() == 1) {
                totalHC += 24;
            } else {
                totalHC += 12;
            }

            System.out.println("Total Horas: " + asg.getSumatoria());

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
        //System.out.println("AsignacionController.calculate() -> Calculado: "+_calculadoDC);
        if (_coordinacion.isAsignado()) {
            _totalesEsperados.setTotalCom(_coordinacion.getComites() * totalHC / 100);
            _totalesEsperados.setTotalPlan(_coordinacion.getAcreditacion() * totalHC / 100);
            _totalesEsperados.setTotalODA(_coordinacion.getOda() * totalHC / 100);
            _totalesEsperados.setTotalPS(_coordinacion.getExtension() * totalHC / 100);
            _totalesEsperados.setTotalHI(_coordinacion.getInvestigacion() * totalHC / 100);
            _totalesEsperados.setTotalVirt(_coordinacion.getVirtualidad() * totalHC / 100);
        }
        //System.out.println("AsignacionController.getListadoAsign -> tamaño de la lista " + _totalesEsperados.getTotalHC());  
        //RequestContext.getCurrentInstance().update("formAsig:summary");        
    }

    public void abrirCrear() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/tiempodoc/crear", options, null);
//        return "/tiempodoc/crear";
    }

    public void actualizar(Asignacion obj) {

        String titulo, detalle;

        try {

            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
            _ejbFacade.actualizar(obj);
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

    public void complete() {
        System.out.println("COmplete");
    }

    public void onCellEdit(RowEditEvent event) {
        
        /*Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();*/

        /*if (_listadoAsign == null) {
         _listadoAsign = _ejbFacade.listado();
         }*/
        Asignacion asigTemp = (Asignacion)event.getObject();

        Double sum = asigTemp.getCapacitacion() + asigTemp.getColectivo() + asigTemp.getComites();
        sum += asigTemp.getHorasclase() + asigTemp.getInvestigacion() + asigTemp.getOda() + asigTemp.getSocial();
        sum += asigTemp.getPlaneacion() + asigTemp.getPreparacion() + asigTemp.getVirtualidad();
        asigTemp.setSumatoria(sum);

        System.out.println("AsignacionController.onCellEdit -> Nuevo Valor: " + asigTemp.toString());

        actualizar(asigTemp);

        _listadoAsign = _ejbFacade.buscarA("_codcoordinacion", String.valueOf(_coordinacion.getCodcoordinacion()));

        _listSum = new ArrayList<>();
        for (Asignacion asigT : _listadoAsign) {
            _listSum.add(asigT.getSumatoria());
        }        
        
    }

    public void calculateTotales(Object obj) {
        System.out.println("Entro a calcular");
        calculate();
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

    public void setListadoAsign(final List<Asignacion> lista) {
        this._listadoAsign = lista;
    }

    public Totales getTotalesCalculados() {
        //calculate();
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

    public List<Double> getListSum() {
        return _listSum;
    }

    public void setListSum(List<Double> _listSum) {
        this._listSum = _listSum;
    }
}
