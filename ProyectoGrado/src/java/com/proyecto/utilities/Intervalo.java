/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.utilities;

import com.proyecto.persistences.Horario;

/**
 *
 * @author Elkin
 */
public class Intervalo {

    private String _hora;
    private Horario _lunes;
    private Horario _martes;
    private Horario _miercoles;
    private Horario _jueves;
    private Horario _viernes;
    private Horario _sabado;

    public Intervalo() {        
    }
    
    public void setDia(Horario base) {
        switch (base.getDiaa()) {
            case 0:
                _hora = base.getHora()+"";
                break;
                
            case 1:
                _lunes = base;
                break;

            case 2:
                _martes = base;
                break;

            case 3:
                _miercoles = base;
                break;
            case 4:
                _jueves = base;
                break;
            case 5:
                _viernes = base;
                break;
            case 6:
                _sabado = base;
                break;
        }
    }

    public String getHora() {
        return _hora;
    }

    public void setHora(String _hora) {
        this._hora = _hora;
    }

    public Horario getLunes() {
        return _lunes;
    }

    public void setLunes(Horario _lunes) {
        this._lunes = _lunes;
    }

    public Horario getMartes() {
        return _martes;
    }

    public void setMartes(Horario _martes) {
        this._martes = _martes;
    }

    public Horario getMiercoles() {
        return _miercoles;
    }

    public void setMiercoles(Horario _miercoles) {
        this._miercoles = _miercoles;
    }

    public Horario getJueves() {
        return _jueves;
    }

    public void setJueves(Horario _jueves) {
        this._jueves = _jueves;
    }

    public Horario getViernes() {
        return _viernes;
    }

    public void setViernes(Horario _viernes) {
        this._viernes = _viernes;
    }

    public Horario getSabado() {
        return _sabado;
    }

    public void setSabado(Horario _sabado) {
        this._sabado = _sabado;
    }

}
