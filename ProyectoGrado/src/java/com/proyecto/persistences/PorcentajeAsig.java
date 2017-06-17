package com.proyecto.persistences;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author User
 */
@Entity
public class PorcentajeAsig implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codporcentaje_asig")
    private int _codPorcentajeAsig;
    
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
        
    @JoinColumn(name = "codcoordinacion", referencedColumnName = "codcoordinacion")
    @ManyToOne(optional = false)
    private Coordinacion _codcoordinacion;   
  

    public PorcentajeAsig() {
    }

    public Coordinacion getCodcoordinacion() {
        return _codcoordinacion;
    }

    public void setCodcoordinacion(Coordinacion _codcoordinacion) {
        this._codcoordinacion = _codcoordinacion;
    }
   

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this._codPorcentajeAsig;
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
        final PorcentajeAsig other = (PorcentajeAsig) obj;
        if (this._codPorcentajeAsig != other._codPorcentajeAsig) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(_codPorcentajeAsig);
    }

    public int getCodPorcentajeAsig() {
        return _codPorcentajeAsig;
    }

    public void setCodPorcentajeAsig(int _codPorcentajeAsig) {
        this._codPorcentajeAsig = _codPorcentajeAsig;
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
   
}
