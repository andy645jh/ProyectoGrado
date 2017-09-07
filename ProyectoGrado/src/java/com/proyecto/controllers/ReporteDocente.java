/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.controllers;

import com.proyecto.facades.ExperienciaFacade;
import com.proyecto.facades.InformacionAcademicaFacade;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Experiencia;
import com.proyecto.persistences.InformacionAcademica;
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
    
    private Docentes _doc;
    private List<InformacionAcademica> _estudios;
    private List<InformacionAcademica> _estudiosTitulados;
    private List<InformacionAcademica> _estudiosCursos;
    
    private List<Experiencia> _experiencias;
    private List<Experiencia> _experienciasPro;
    private List<Experiencia> _experienciasDoc;
    
    public ReporteDocente() {
        
    }  
    
    public void init()
    {
        llenarListasEstudios();
        llenarListasExperiencia();
    }
    
    public void llenarListasEstudios()
    {
        _estudios = _facEstudios.buscarCampo("cod_docente", getDoc().getCedula() + "");
        _estudiosTitulados = new ArrayList<>();
        _estudiosCursos = new ArrayList<>();
        System.out.println("Estudio: "+_estudios.size());
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
        _experiencias = _facExperiencia.buscarCampo("coddocente", getDoc().getCedula() + "");
        _experienciasPro = new ArrayList<>();
        _experienciasDoc = new ArrayList<>();
        
        System.out.println("Experiencias: "+_experiencias.size());
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
        _doc = (Docentes) SessionUtils.get("docente");
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
}
