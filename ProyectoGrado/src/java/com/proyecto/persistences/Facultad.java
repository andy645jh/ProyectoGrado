package com.proyecto.persistences;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
public class Facultad implements Serializable {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codfacultad")
    private int _codfacultad;
    
    @Column(name = "nombre")
    @Size(min = 1, max = 100)
    @NotNull
    private String _nombre;
    
    @Column(name = "abreviatura")
    @Size(min = 1, max = 100)
    @NotNull
    private String _abreviatura;

    public Facultad() {
    }

    public int getCodfacultad() {
        return _codfacultad;
    }

    public void setCodfacultad(int _codfacultad) {
        this._codfacultad = _codfacultad;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String getAbreviatura() {
        return _abreviatura;
    }

    public void setAbreviatura(String _abreviatura) {
        this._abreviatura = _abreviatura;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this._codfacultad;
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
        final Facultad other = (Facultad) obj;
        if (this._codfacultad != other._codfacultad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Facultad{" + "_nombre=" + _nombre + '}';
    }
    
    
}
