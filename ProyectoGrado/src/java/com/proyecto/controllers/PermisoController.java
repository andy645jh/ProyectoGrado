/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.controllers;

import com.proyecto.facades.PermisosFacade;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Permisos;
import com.proyecto.utilities.SessionUtils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author elkin
 */
@ManagedBean
@SessionScoped
public class PermisoController {

    @EJB
    private PermisosFacade _ejbFacade;
    private Permisos _permiso;
    private String _actual;
    private String _nueva;
    private String _confirma;
    private Docentes _doc;
    
    public PermisoController() {
        
    }
    
    @PostConstruct
    public void init()
    {
        _doc = (Docentes) SessionUtils.get("docente");
        _permiso = _ejbFacade.buscarCampo("usuario", _doc.getCedula()+"");
    }

    public void actualizar() throws UnsupportedEncodingException, NoSuchAlgorithmException 
    {
        String hash = encrypt(_actual);
        
        if(hash.equals(_permiso.getClave()) && _nueva.equals(_confirma))
        {
            _permiso.setClave(encrypt(_nueva));
            _ejbFacade.actualizar(_permiso);
            _actual = "";
            _nueva = "";
            _confirma = "";
            System.out.println("PermisoController.actualizar -> Se actualizo");
        }else{
            // mostrar mensaje
            System.out.println("PermisoController.actualizar -> No se actualizo");
        }
    }
    
    private String encrypt(String dato) throws UnsupportedEncodingException, NoSuchAlgorithmException
    {
        MessageDigest sha256=MessageDigest.getInstance("SHA-256");
        sha256.update(dato.getBytes("UTF-8"));
        byte[] digest = sha256.digest();
        StringBuffer sb=new StringBuffer();
        for(byte b : digest) {        
            sb.append(String.format("%02x", b));
	}
        return sb.toString(); 
    }
    
    public Permisos getPermiso() {
        return _permiso;
    }

    public void setPermiso(Permisos _permiso) {
        this._permiso = _permiso;
    }

    public String getActual() {
        return _actual;
    }

    public void setActual(String _actual) {
        this._actual = _actual;
    }

    public String getNueva() {
        return _nueva;
    }

    public void setNueva(String _nueva) {
        this._nueva = _nueva;
    }

    public String getConfirma() {
        return _confirma;
    }

    public void setConfirma(String _confirma) {
        this._confirma = _confirma;
    }
}
