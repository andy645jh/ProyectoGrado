package com.proyecto.controllers;

import com.proyecto.facades.AsignacionFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.persistences.Asignacion;
import com.proyecto.persistences.Coordinacion;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.TiempoAsignado;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.data.SortEvent;

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
    private double _totalSum;
    
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

    public void reset()
    {
        _totalesEsperados = new Totales();
        _totalesCalculados = new Totales();
        _listadoAsign = null;
    }
    
    public List<Asignacion> getListadoAsign() {

        if (_listadoAsign == null) {
            _listadoAsign = _ejbFacade.buscarA("_codcoordinacion", String.valueOf(_coordinacion.getCodcoordinacion()));
            _listSum = new ArrayList<>();
            List<Asignacion> listaRemover = new ArrayList<Asignacion>();
            
            _totalSum =0;
            for (Asignacion asigTemp : _listadoAsign) {    
                System.out.println("Habilitado: "+ asigTemp.getCoddocente().getInhabilitar());
                if((asigTemp.getCoddocente().getTipocontrato()==2 || asigTemp.getCoddocente().getTipocontrato()==1) && asigTemp.getCoddocente().getInhabilitar()==0)
                {
                    _totalSum += asigTemp.getSumatoria();
                    _listSum.add(asigTemp.getSumatoria());
                }else{
                    listaRemover.add(asigTemp);
                }             
            }
            _listadoAsign.removeAll(listaRemover);
            /*for (Asignacion asignacion : listaRemover) {
                for (int i = 0; i < _listadoAsign.size(); i++) {
                    if(_listadoAsign.get(i)==asignacion)
                    {                        
                        _listadoAsign.remove(asignacion);
                    }
                }
            }*/
        }
        calculate();
        System.out.println("Pide Listado: " + _listSum.size());
        return _listadoAsign;
    }

    public double getCalculate(Asignacion asigTemp) {
        double sum = asigTemp.getColectivo() + asigTemp.getComites();
        sum += asigTemp.getHorasclase() + asigTemp.getInvestigacion() + asigTemp.getOda() + asigTemp.getExtension();
        sum += asigTemp.getAcreditacion() + asigTemp.getPreparacion() + asigTemp.getVirtualidad();
        return sum;
    }

    public void calculate() {
        
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
            if (asg.getColectivo() != null) {
                //System.out.println("Colectivo: "+asg.getColectivo()+" -- total: "+totalCD);
                totalCD += asg.getColectivo();
            }
            if (asg.getInvestigacion() != null) {
                totalHI += asg.getInvestigacion();
            }
            if (asg.getExtension() != null) {
                totalPS += asg.getExtension();
            }
            if (asg.getOda() != null) {
                totalODA += asg.getOda();
            }
            if (asg.getAcreditacion() != null) {
                totalPlan += asg.getAcreditacion();
            }
            if (asg.getVirtualidad() != null) {
                totalvirt += asg.getVirtualidad();
            }
            if (asg.getComites() != null) {
                totalCom += asg.getComites();
            }

            /*if (asg.getCoddocente().getTipocontrato() == 1) {
                totalHC += 24;
            } else {
                totalHC += 12;
            }*/

            System.out.println("Total Horas: " + asg.getSumatoria());

        }

        //seteando los valores acumulados
        _totalesCalculados.setTotalHorasClase(totalHC);
        _totalesCalculados.setTotalComites(totalCom);
        _totalesCalculados.setTotalVirt(totalvirt);
        _totalesCalculados.setTotalAcreditacion(totalPlan);
        _totalesCalculados.setTotalODA(totalODA);
        _totalesCalculados.setTotalExtension(totalPS);
        _totalesCalculados.setTotalHoraInv(totalHI);
        _totalesCalculados.setTotalColectivo(totalCD);
        _totalesCalculados.setTotalPreparacion(totalPC);       

        _totalesEsperados.setTotalHorasClase(totalHC);
        //System.out.println("AsignacionController.calculate() -> Calculado: "+_calculadoDC);
        if (_coordinacion.isAsignado()) {
            _totalesEsperados.setTotalComites(_coordinacion.getComites() * totalHC / 100);
            _totalesEsperados.setTotalAcreditacion(_coordinacion.getAcreditacion() * totalHC / 100);
            _totalesEsperados.setTotalODA(_coordinacion.getOda() * totalHC / 100);
            _totalesEsperados.setTotalExtension(_coordinacion.getExtension() * totalHC / 100);
            _totalesEsperados.setTotalHoraInv(_coordinacion.getInvestigacion() * totalHC / 100);
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

    public String checkValues(double valCal, double valMax)
    {
        if(valCal>valMax)
        {
            return "red-color";
        }
        return "black-color";
    }
    
    public String checkSum(double sum, int tipo)
    {
        if(tipo==1 || tipo==4)
        {
            if(sum>53.33)
            {
                return "red-color";
            }else{
                return "black-color";
            }
        }else{
            if(sum>26.33)
            {
                return "red-color";
            }else{
                return "black-color";
            }            
        }
    }
    
    public double valorMax(Docentes docTemp)
    {
        switch(docTemp.getTipocontrato())
        {            
            case 1:
            case 4:
                return 53.33;
            case 2:
                return 26.33;
            default:
                return 53.33;
        }
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

        Double sum = asigTemp.getColectivo() + asigTemp.getComites();
        sum += asigTemp.getHorasclase() + asigTemp.getInvestigacion() + asigTemp.getOda() + asigTemp.getExtension();
        sum += asigTemp.getAcreditacion() + asigTemp.getPreparacion() + asigTemp.getVirtualidad();
        asigTemp.setSumatoria(sum);

        System.out.println("AsignacionController.onCellEdit -> Nuevo Valor: " + asigTemp.toString());

        actualizar(asigTemp);
       
        _listSum = new ArrayList<>();
        _totalSum = 0;
        for (Asignacion asigT : _listadoAsign) {
            _totalSum += asigT.getSumatoria();
            _listSum.add(asigT.getSumatoria());
        }       
        
    }

    public double calcularPorcentaje(double totalColumna)
    {
        return totalColumna / (_totalesCalculados.getTotalHoras()-_totalesCalculados.getTotalColectivo()+_totalesCalculados.getTotalPreparacion()+_totalesCalculados.getTotalHorasClase());
    }
    
    public void sortListener(SortEvent sort)
    {
        System.out.println("Intenta Ordenar");
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

    public double getTotalSum() {
        return _totalSum;
    }

    public void setTotalSum(double _totalSum) {
        this._totalSum = _totalSum;
    }
}
