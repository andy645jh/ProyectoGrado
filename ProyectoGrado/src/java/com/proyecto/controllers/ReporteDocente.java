/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.controllers;

import com.proyecto.facades.ExperienciaFacade;
import com.proyecto.facades.InformacionAcademicaFacade;
import com.proyecto.facades.ParticipacionFacade;
import com.proyecto.facades.ProduccionFacade;
import com.proyecto.facades.SocializacionFacade;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Experiencia;
import com.proyecto.persistences.InformacionAcademica;
import com.proyecto.persistences.Participacion;
import com.proyecto.persistences.Produccion;
import com.proyecto.persistences.Socializacion;
import com.proyecto.utilities.SessionUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author elkin
 */
@ManagedBean
@SessionScoped
public class ReporteDocente implements Serializable {

    @EJB
    private InformacionAcademicaFacade _facEstudios;
    
    @EJB
    private ExperienciaFacade _facExperiencia;
    
    @EJB
    private ProduccionFacade _facProduccion;
    
    @EJB
    private ParticipacionFacade _facParticipacion;
    
    @EJB
    private SocializacionFacade _facSocial;
    
    private Docentes _doc;
    private List<InformacionAcademica> _estudios;
    private List<InformacionAcademica> _estudiosTitulados;
    private List<InformacionAcademica> _estudiosCursos;
    
    private List<Experiencia> _experiencias;
    private List<Experiencia> _experienciasPro;
    private List<Experiencia> _experienciasDoc;
    
    private List<Produccion> _producciones;
    private List<Participacion> _participaciones;
    private List<Participacion> _partInvest;
    private List<Participacion> _partAcademicas;
    private List<Participacion> _partSocial;
    private List<Participacion> _partDeport;
    
    private List<Socializacion> _socializaciones;
    
    public ReporteDocente() {
        
    }  
    
    public void init()
    {
        _doc = (Docentes) SessionUtils.get("docente");
        _partInvest = new ArrayList<>();
        _partAcademicas = new ArrayList<>();
        _partSocial = new ArrayList<>();
        
        llenarListasEstudios();
        llenarListasExperiencia();
        llenarListaProduccion();
        llenarListaParticipaciones();
        llenarListaSocializaciones();
    }
    
    public void llenarListaSocializaciones()
    {
        _socializaciones = _facSocial.buscarCampo("coddocente", _doc.getCedula() + "");
        
        /*System.out.println("Estudio: "+_doc);
        for (InformacionAcademica infoTemp : _estudios) {
            if(infoTemp.getTipo()==1)
            {
                _estudiosTitulados.add(infoTemp);
            }else{
                _estudiosCursos.add(infoTemp);
            }
        }*/
    }
    
    public void llenarListaParticipaciones()
    {
        _participaciones = _facParticipacion.buscarCampo("coddocente", _doc.getCedula() + "");
                
        for (Participacion partTemp : _participaciones) {
            if(partTemp.getTipo()==1)
            {
                _partInvest.add(partTemp);
            }else if(partTemp.getTipo()==2)
            {
                _partAcademicas.add(partTemp);
            }else if(partTemp.getTipo()==3){
                _partSocial.add(partTemp);
            }else{
                _partDeport.add(partTemp);
            }
        }
    }
    
    public void llenarListaProduccion()
    {
        _producciones = _facProduccion.buscarCampo("coddocente", _doc.getCedula() + "");
        
        /*System.out.println("Estudio: "+_doc);
        for (InformacionAcademica infoTemp : _estudios) {
            if(infoTemp.getTipo()==1)
            {
                _estudiosTitulados.add(infoTemp);
            }else{
                _estudiosCursos.add(infoTemp);
            }
        }*/
    }
    
    public void llenarListasEstudios()
    {
        _estudios = _facEstudios.buscarCampo("cod_docente", _doc.getCedula() + "");
        _estudiosTitulados = new ArrayList<>();
        _estudiosCursos = new ArrayList<>();
        System.out.println("Estudio: "+_doc);
        for (InformacionAcademica infoTemp : _estudios) {
            if(infoTemp.getTipo()==1)
            {
                _estudiosTitulados.add(infoTemp);
            }else{
                _estudiosCursos.add(infoTemp);
            }
        }
    }
    
    public void llenarListasExperiencia()
    {
        _experiencias = _facExperiencia.buscarCampo("coddocente", _doc.getCedula() + "");
        _experienciasPro = new ArrayList<>();
        _experienciasDoc = new ArrayList<>();
        
        System.out.println("Experiencias: "+_doc);
        for (Experiencia infoTemp : _experiencias) {
            if(infoTemp.getTipo()==1)
            {
                _experienciasPro.add(infoTemp);
            }else{
                _experienciasDoc.add(infoTemp);
            }
        }
    } 

    public Docentes getDoc() {
        //_doc = (Docentes) SessionUtils.get("docente");
        return _doc;
    }

    public List<InformacionAcademica> getEstudiosTitulados() {
        return _estudiosTitulados;
    }

    public List<InformacionAcademica> getEstudiosCursos() {
        return _estudiosCursos;
    }

    public List<Experiencia> getExperienciasPro() {
        return _experienciasPro;
    }

    public List<Experiencia> getExperienciasDoc() {
        return _experienciasDoc;
    }

    public List<Produccion> getProducciones() {
        return _producciones;
    }

    public List<Participacion> getParticipaciones() {
        return _participaciones;
    }

    public List<Participacion> getPartInvest() {
        return _partInvest;
    }

    public List<Participacion> getPartAcademicas() {
        return _partAcademicas;
    }

    public List<Participacion> getPartSocial() {
        return _partSocial;
    }

    public List<Socializacion> getSocializaciones() {
        return _socializaciones;
    }

    public List<Participacion> getPartDeport() {
        return _partDeport;
    }
}
