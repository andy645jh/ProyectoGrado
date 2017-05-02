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
    
    @JoinColumn(name = "codporcentaje", referencedColumnName = "codporcentaje")
    @ManyToOne(optional = false)
    private Porcentaje _codporcentaje;
    
    @Column(name = "horasclase")
    private Double _horasclase;
    @Column(name = "totalhc")
    private Double _totalhc;
    @Column(name = "preparacion")
    private Double _preparacion;
    @Column(name = "totalprep")
    private Double _totalprep;
    @Column(name = "capacitacion")
    private Double _capacitacion;
    @Column(name = "totalcapa")
    private Double _totalcapa;
    @Column(name = "colectivo")
    private Double _colectivo;
    @Column(name = "totalcolectivo")
    private Double _totalcolectivo;
    @Column(name = "investigacion")
    private Double _investigacion;
    @Column(name = "totalinv")
    private Double _totalinv;
    @Column(name = "social")
    private Double _social;
    @Column(name = "totalsocial")
    private Double _totalsocial;
    @Column(name = "oda")
    private Double _oda;
    @Column(name = "totaloda")
    private Double _totaloda;
    @Column(name = "planeacion")
    private Double _planeacion;
    @Column(name = "totalplan")
    private Double _totalplan;
    @Column(name = "virtualidad")
    private Double _virtualidad;
    @Column(name = "totalvirt")
    private Double _totalvirt;
    @Column(name = "comites")
    private Double _comites;
    @Column(name = "totalcom")
    private Double _totalcom;

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

    public Double getTotalhc() {
        return _totalhc;
    }

    public void setTotalhc(Double _totalhc) {
        this._totalhc = _totalhc;
    }

    public Double getPreparacion() {
        return _preparacion;
    }

    public void setPreparacion(Double _preparacion) {
        this._preparacion = _preparacion;
    }

    public Double getTotalprep() {
        return _totalprep;
    }

    public void setTotalprep(Double _totalprep) {
        this._totalprep = _totalprep;
    }

    public Double getCapacitacion() {
        return _capacitacion;
    }

    public void setCapacitacion(Double _capacitacion) {
        this._capacitacion = _capacitacion;
    }

    public Double getTotalcapa() {
        return _totalcapa;
    }

    public void setTotalcapa(Double _totalcapa) {
        this._totalcapa = _totalcapa;
    }

    public Double getColectivo() {
        return _colectivo;
    }

    public void setColectivo(Double _colectivo) {
        this._colectivo = _colectivo;
    }

    public Double getTotalcolectivo() {
        return _totalcolectivo;
    }

    public void setTotalcolectivo(Double _totalcolectivo) {
        this._totalcolectivo = _totalcolectivo;
    }

    public Double getInvestigacion() {
        return _investigacion;
    }

    public void setInvestigacion(Double _investigacion) {
        this._investigacion = _investigacion;
    }

    public Double getTotalinv() {
        return _totalinv;
    }

    public void setTotalinv(Double _totalinv) {
        this._totalinv = _totalinv;
    }

    public Double getSocial() {
        return _social;
    }

    public void setSocial(Double _social) {
        this._social = _social;
    }

    public Double getTotalsocial() {
        return _totalsocial;
    }

    public void setTotalsocial(Double _totalsocial) {
        this._totalsocial = _totalsocial;
    }

    public Double getOda() {
        return _oda;
    }

    public void setOda(Double _oda) {
        this._oda = _oda;
    }

    public Double getTotaloda() {
        return _totaloda;
    }

    public void setTotaloda(Double _totaloda) {
        this._totaloda = _totaloda;
    }

    public Double getPlaneacion() {
        return _planeacion;
    }

    public void setPlaneacion(Double _planeacion) {
        this._planeacion = _planeacion;
    }

    public Double getTotalplan() {
        return _totalplan;
    }

    public void setTotalplan(Double _totalplan) {
        this._totalplan = _totalplan;
    }

    public Double getVirtualidad() {
        return _virtualidad;
    }

    public void setVirtualidad(Double _virtualidad) {
        this._virtualidad = _virtualidad;
    }

    public Double getTotalvirt() {
        return _totalvirt;
    }

    public void setTotalvirt(Double _totalvirt) {
        this._totalvirt = _totalvirt;
    }

    public Double getComites() {
        return _comites;
    }

    public void setComites(Double _comites) {
        this._comites = _comites;
    }

    public Double getTotalcom() {
        return _totalcom;
    }

    public void setTotalcom(Double _totalcom) {
        this._totalcom = _totalcom;
    }

    public Docentes getCoddocente() {
        return _coddocente;
    }

    public void setCoddocente(Docentes _coddocente) {
        this._coddocente = _coddocente;
    }

    public Porcentaje getCodporcentaje() {
        return _codporcentaje;
    }

    public void setCodporcentaje(Porcentaje _codporcentaje) {
        this._codporcentaje = _codporcentaje;
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
        return "TiempoAsignado{" + _codasg;
    }
    
    
}
