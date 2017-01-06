/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.persistences;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
public class Coordinacion implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codcoordinacion")
    private int _codcoordinacion;
    
    @Column(name = "nombre")
    @Size(min = 1, max = 100)
    @NotNull
    private String _nombre;
    
     @JoinColumn(name = "codfacultad", referencedColumnName = "codfacultad")
    @ManyToOne(optional = false)
    private Facultad _codfacultad;

    public Coordinacion() {
    }

    public int getCodcoordinacion() {
        return _codcoordinacion;
    }

    public void setCodcoordinacion(int _codcoordinacion) {
        this._codcoordinacion = _codcoordinacion;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public Facultad getCodfacultad() {
        return _codfacultad;
    }

    public void setCodfacultad(Facultad _codfacultad) {
        this._codfacultad = _codfacultad;
    }

    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this._codcoordinacion;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinacion other = (Coordinacion) obj;
        if (this._codcoordinacion != other._codcoordinacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coordinacion{" + "_nombre=" + _nombre + '}';
    }
     
     
    
}
