package com.proyecto.persistences;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author User
 */
@Entity
public class Asignacion implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codasg")
    private int _codasg;
              
    @JoinColumn(name = "coddocente", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Docentes _coddocente;
    
    @JoinColumn(name = "codcoord", referencedColumnName = "codcoordinacion")
    @ManyToOne(optional = false)
    private Coordinacion _codcoordinacion;
    
    @Column(name = "horasclase")
    private Double _horasclase;
    
    @Column(name = "preparacion")
    private Double _preparacion;
   
    @Column(name = "capacitacion")
    private Double _capacitacion;
    
    @Column(name = "colectivo")
    private Double _colectivo;
   
    @Column(name = "investigacion")
    private Double _investigacion;
  
    @Column(name = "social")
    private Double _social;
    
    @Column(name = "oda")
    private Double _oda;
  
    @Column(name = "planeacion")
    private Double _planeacion;
   
    @Column(name = "virtualidad")
    private Double _virtualidad;
    
    @Column(name = "comites")
    private Double _comites;

    @Transient
    private Double _totalHoras;
    
    public Asignacion() {
    }

    public int getCodasg() {
        return _codasg;
    }

    public void setCodasg(int _codasg) {
        this._codasg = _codasg;
    }

    public Double getHorasclase() {
        return _horasclase;
    }

    public void setHorasclase(Double _horasclase) {
        this._horasclase = _horasclase;
    }

   

    public Double getPreparacion() {
        return _preparacion;
    }

    public void setPreparacion(Double _preparacion) {
        this._preparacion = _preparacion;
    }

   
    public Double getCapacitacion() {
        return _capacitacion;
    }

    public void setCapacitacion(Double _capacitacion) {
        this._capacitacion = _capacitacion;
    }

    public Double getColectivo() {
        return _colectivo;
    }

    public void setColectivo(Double _colectivo) {
        this._colectivo = _colectivo;
    }

   
    public Double getInvestigacion() {
        return _investigacion;
    }

    public void setInvestigacion(Double _investigacion) {
        this._investigacion = _investigacion;
    }

  

    public Double getSocial() {
        return _social;
    }

    public void setSocial(Double _social) {
        this._social = _social;
    }

    

    public Double getOda() {
        return _oda;
    }

    public void setOda(Double _oda) {
        this._oda = _oda;
    }

    public Double getPlaneacion() {
        return _planeacion;
    }

    public void setPlaneacion(Double _planeacion) {
        this._planeacion = _planeacion;
    }

    

    public Double getVirtualidad() {
        return _virtualidad;
    }

    public void setVirtualidad(Double _virtualidad) {
        this._virtualidad = _virtualidad;
    }

    

    public Double getComites() {
        return _comites;
    }

    public void setComites(Double _comites) {
        this._comites = _comites;
    }    

    public Docentes getCoddocente() {
        return _coddocente;
    }

    public void setCoddocente(Docentes _coddocente) {
        this._coddocente = _coddocente;
    }

    public Coordinacion getCodcoordinacion() {
        return _codcoordinacion;
    }
    
    public void setCodcoordinacion(Coordinacion _codcoordinacion) {
        this._codcoordinacion = _codcoordinacion;
    }  
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this._codasg;
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
        final Asignacion other = (Asignacion) obj;
        if (this._codasg != other._codasg) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Asignaciones -> C: "+_colectivo+", I; "+_investigacion+", O: "+_oda+" V: "+_virtualidad+", Ca:"+_capacitacion;
    }

    public Double getTotalHoras() {
        return _totalHoras;
    }

    public void setTotalHoras(Double _totalHoras) {
        this._totalHoras = _totalHoras;
    }
    
    
}
