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
    private double _totalHC = 0;
    private double _totalPC = 0;
    private double _totalCap = 0;
    private double _totalCD = 0;        
    
    private double _totalHI = 0;
    private double _totalPS = 0;
    private double _totalODA = 0;
    private double _totalPlan = 0;
    private double _totalVirt = 0;
    private double _totalCom = 0;

    public Totales() { }    

    public double getTotalHoras() {
        return _totalHoras;
    }

    public void setTotalHoras(double _totalHoras) {
        this._totalHoras = _totalHoras;
    }

    public double getTotalHC() {
        return _totalHC;
    }

    public void setTotalHC(double _totalHC) {
        this._totalHC = _totalHC;
    }

    public double getTotalPC() {
        return _totalPC;
    }

    public void setTotalPC(double _totalPC) {
        this._totalPC = _totalPC;
    }

    public double getTotalCap() {
        return _totalCap;
    }

    public void setTotalCap(double _totalCap) {
        this._totalCap = _totalCap;
    }

    public double getTotalCD() {
        return _totalCD;
    }

    public void setTotalCD(double _totalCD) {
        this._totalCD = _totalCD;
    }

    public double getTotalHI() {
        return _totalHI;
    }

    public void setTotalHI(double _totalHI) {
        this._totalHI = _totalHI;
    }

    public double getTotalPS() {
        return _totalPS;
    }

    public void setTotalPS(double _totalPS) {
        this._totalPS = _totalPS;
    }

    public double getTotalODA() {
        return _totalODA;
    }

    public void setTotalODA(double _totalODA) {
        this._totalODA = _totalODA;
    }

    public double getTotalPlan() {
        return _totalPlan;
    }

    public void setTotalPlan(double _totalPlan) {
        this._totalPlan = _totalPlan;
    }

    public double getTotalVirt() {
        return _totalVirt;
    }

    public void setTotalVirt(double _totalVirt) {
        this._totalVirt = _totalVirt;
    }

    public double getTotalCom() {
        return _totalCom;
    }

    public void setTotalCom(double _totalCom) {
        this._totalCom = _totalCom;
    }
    
    
}
