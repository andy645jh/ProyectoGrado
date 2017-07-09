package com.proyecto.persistences;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
@Table(name = "coordinacion")
public class Coordinacion implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codcoordinacion")
    private int _codcoordinacion;
    
    @Column(name = "nombre")
    @Size(min = 1, max = 100)
    @NotNull
    private String _nombre;
    
    @Column(name = "investigacion")
    private Double _investigacion;
    
    @Column(name = "extension")
    private Double _extension;
    
    @Column(name = "comites")
    private Double _comites;
    
    @Column(name = "oda")
    private Double _oda;
    
    @Column(name = "acreditacion")
    private Double _acreditacion;
    
    @Column(name = "virtualidad")
    private Double _virtualidad;
    
    @Column(name = "asignado")
    private boolean _asignado;
    
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

    public Double getInvestigacion() {
        return _investigacion;
    }

    public void setInvestigacion(Double _investigacion) {
        this._investigacion = _investigacion;
    }

    public Double getExtension() {
        return _extension;
    }

    public void setExtension(Double _extension) {
        this._extension = _extension;
    }

    public Double getComites() {
        return _comites;
    }

    public void setComites(Double _comites) {
        this._comites = _comites;
    }

    public Double getOda() {
        return _oda;
    }

    public void setOda(Double _oda) {
        this._oda = _oda;
    }

    public Double getAcreditacion() {
        return _acreditacion;
    }

    public void setAcreditacion(Double _acreditacion) {
        this._acreditacion = _acreditacion;
    }

    public Double getVirtualidad() {
        return _virtualidad;
    }

    public void setVirtualidad(Double _virtualidad) {
        this._virtualidad = _virtualidad;
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
        return _nombre;
    }

    public boolean isAsignado() {
        return _asignado;
    }

    public void setAsignado(boolean _asignado) {
        this._asignado = _asignado;
    }
     
    public void setInfo(Coordinacion coord)
    {
        _acreditacion = coord.getAcreditacion();
        _comites = coord.getComites();
        _extension = coord.getExtension();
        _investigacion = coord.getInvestigacion();
        _oda = coord.getOda();
        _virtualidad = coord.getVirtualidad();
    }
    
}
