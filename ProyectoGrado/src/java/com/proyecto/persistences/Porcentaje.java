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
public class Porcentaje implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codporcentaje")
    private int _codporcentaje;
    
    @Column(name = "porcentaje")
    private Double _porcentaje;
    
    @JoinColumn(name = "codcoordinacion", referencedColumnName = "codcoordinacion")
    @ManyToOne(optional = false)
    private Coordinacion _codcoordinacion;
    
    @JoinColumn(name = "codactividadmisional", referencedColumnName = "codactividadmisional")
    @ManyToOne(optional = false)
    private ActividadMisional _codmisional;

    public Porcentaje() {
    }

    public int getCodporcentaje() {
        return _codporcentaje;
    }

    public void setCodporcentaje(int _codporcentaje) {
        this._codporcentaje = _codporcentaje;
    }

    public Double getPorcentaje() {
        return _porcentaje;
    }

    public void setPorcentaje(Double _porcentaje) {
        this._porcentaje = _porcentaje;
    }

    public Coordinacion getCodcoordinador() {
        return _codcoordinacion;
    }

    public void setCodcoordinador(Coordinacion _codcoordinador) {
        this._codcoordinacion = _codcoordinador;
    }

    public Coordinacion getCodcoordinacion() {
        return _codcoordinacion;
    }

    public void setCodcoordinacion(Coordinacion _codcoordinacion) {
        this._codcoordinacion = _codcoordinacion;
    }

    public ActividadMisional getCodmisional() {
        return _codmisional;
    }

    public void setCodmisional(ActividadMisional _codmisional) {
        this._codmisional = _codmisional;
    }



   

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this._codporcentaje;
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
        final Porcentaje other = (Porcentaje) obj;
        if (this._codporcentaje != other._codporcentaje) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return _porcentaje.toString();
    }
   
}
