/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.utilities;

import java.io.Serializable;

/**
 *
 * @author Elkin
 */
public class Totales implements Serializable {
    private double _totalHoras = 0;
    private double _totalHorasClase = 0;
    private double _totalPreparacion = 0;   
    private double _totalColectivo = 0;        
    
    private double _totalHoraInv = 0;
    private double _totalExtension = 0;
    private double _totalODA = 0;
    private double _totalAcreditacion = 0;
    private double _totalVirt = 0;
    private double _totalComites = 0;

    public Totales() { }    

    public double getTotalHoras() {
        return _totalHoras;
    }

    public void setTotalHoras(double _totalHoras) {
        this._totalHoras = _totalHoras;
    }

    public double getTotalHorasClase() {
        return _totalHorasClase;
    }

    public void setTotalHorasClase(double _totalHC) {
        this._totalHorasClase = _totalHC;
    }

    public double getTotalPreparacion() {
        return _totalPreparacion;
    }

    public void setTotalPreparacion(double _totalPC) {
        this._totalPreparacion = _totalPC;
    }

    public double getTotalColectivo() {
        return _totalColectivo;
    }

    public void setTotalColectivo(double _totalCD) {
        this._totalColectivo = _totalCD;
    }

    public double getTotaHoraInv() {
        return _totalHoraInv;
    }

    public void setTotalHoraInv(double _totalHI) {
        this._totalHoraInv = _totalHI;
    }

    public double getTotalExtension() {
        return _totalExtension;
    }

    public void setTotalExtension(double _totalPS) {
        this._totalExtension = _totalPS;
    }

    public double getTotalODA() {
        return _totalODA;
    }

    public void setTotalODA(double _totalODA) {
        this._totalODA = _totalODA;
    }

    public double getTotalAcreditacion() {
        return _totalAcreditacion;
    }

    public void setTotalAcreditacion(double _totalPlan) {
        this._totalAcreditacion = _totalPlan;
    }

    public double getTotalVirt() {
        return _totalVirt;
    }

    public void setTotalVirt(double _totalVirt) {
        this._totalVirt = _totalVirt;
    }

    public double getTotalComites() {
        return _totalComites;
    }

    public void setTotalComites(double _totalCom) {
        this._totalComites = _totalCom;
    }
    
    
}
