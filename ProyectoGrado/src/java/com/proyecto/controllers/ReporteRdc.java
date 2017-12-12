/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.controllers;

import static com.proyecto.controllers.HorarioController._intervalos;
import com.proyecto.facades.ActividadesFacade;
import com.proyecto.facades.ConvencionesFacade;
import com.proyecto.facades.HorarioFacade;
import com.proyecto.facades.ProductosFacade;
import com.proyecto.facades.SemanaFacade;
import com.proyecto.persistences.Actividades;
import com.proyecto.persistences.Convenciones;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Horario;
import com.proyecto.persistences.Productos;
import com.proyecto.persistences.Semana;
import com.proyecto.utilities.Intervalo;
import com.proyecto.utilities.SessionUtils;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Init;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author elkin
 */
@ManagedBean
@SessionScoped
public class ReporteRdc implements Serializable {

    @EJB
    private SemanaFacade _semanaFac;

    @EJB
    private ActividadesFacade _actFac;

    @EJB
    private ProductosFacade _productosFacade;

    @EJB
    private HorarioFacade _horarioFacade;
    
    @EJB
    private ConvencionesFacade _convFacade;
    
    private List<Actividades> _actividades;
    private List<Productos> _productos;
    private Docentes _doc;
    private List<Convenciones> _lstConvenciones;
    
    public ReporteRdc() {

    }

    @PostConstruct
    public void init() {
        _lstConvenciones=_convFacade.listado();
    }
    
    public List<String> getListadoHeaderSeguimiento() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");

        List<Semana> list = _semanaFac.listado();
        List<String> listHeaderSemanas = new ArrayList<String>();

        for (Semana semana : list) {
            String col = sdf.format(semana.getHorainicio()) + " - " + sdf.format(semana.getHorafinal());
            listHeaderSemanas.add(col);
        }

        return listHeaderSemanas;
    }

    public List<String[]> getListadoSeguimiento() {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
        int size = getListadoHeaderSeguimiento().size();

        String[] listFila = new String[size];
        List<String[]> listCompleta = new ArrayList<>();
        Docentes docente = getDoc();
        System.out.println("--> Docente: " + docente.getCedula());
        List<Actividades> listaActividades = getActividades();

        for (int i = 0; i < listaActividades.size(); i++) {
            for (int j = 0; j < size; j++) {
                String horas = listaActividades.get(i).getHoras() + "";
                listFila[j] = horas;
            }
            listCompleta.add(listFila);
        }
        return listCompleta;
    }

    public List<Intervalo> getListInterval() {
        Intervalo[] _arrayInterval;
        List<Horario> listHorario = _horarioFacade.buscarCampo("_coddocente", getDoc().getCedula() + "");
        _arrayInterval = new Intervalo[_intervalos.length];
        System.out.println("TAMAÑO " + listHorario.size());
        for (int i = 0; i < _arrayInterval.length; i++) {
            _arrayInterval[i] = new Intervalo();
            _arrayInterval[i].setInitData(_intervalos[i], i);
        }

        for (Horario obj : listHorario) {
            //eventModel.addEvent(new DefaultScheduleEvent(obj.getNombre(), obj.getHorainicio(), obj.getHorafinal(),obj));
            System.out.println("HOra: " + obj.getHora());
            //cuadrando la lista de horarios        
            _arrayInterval[obj.getHora()].setDia(obj);
        }

        //convertir array a lista
        return Arrays.asList(_arrayInterval);
        //RequestContext.getCurrentInstance().update(":formHorario:nuevaLista");
    }

    public List<Productos> getProductos() {        
        //ordenamiento ascendente
        Collections.sort(_productos, (o1, o2) -> o1.getCodactividad().getCodactividad() - o2.getCodactividad().getCodactividad());
        
        return _productos;
    }

    public List<Actividades> getActividades() {
        int cedula = getDoc().getCedula();
        _actividades = new ArrayList<>();
        _productos = _productosFacade.buscarCampo("_coddocente", cedula+"");
        
        for (Productos producto : _productos) {
            //System.out.println("ReporteRdc.getActividades() actividad->"+producto.getCodactividad().getNombre());
            _actividades.add(producto.getCodactividad());
        }
        
        //ordenamiento ascendente
        Collections.sort(_actividades, (o1, o2) -> o1.getCodactividad() - o2.getCodactividad());        
        return _actividades;
    }

    public String getSemestre()
    {
        return SessionUtils.getSemestre()+" - "+SessionUtils.getYear();
    }
    
    public Docentes getDoc() {
        _doc = (Docentes) SessionUtils.get("docente");
        return _doc;
    }

    public List<Convenciones> getLstConvenciones() {
        return _lstConvenciones;
    }

    public void setLstConvenciones(List<Convenciones> _lstConvenciones) {
        this._lstConvenciones = _lstConvenciones;
    }

}
