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
public class TiempoAsignado implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codtiempoasignado")
    private int _codtiempoasignado;
      
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "horas")    
    private Date _horas;  
        
    @JoinColumn(name = "codobligatoria", referencedColumnName = "codactividadobligatorias")
    @ManyToOne(optional = false)
    private ActividadObligatoria _codobliatoria;
        
    @JoinColumn(name = "coddocente", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Docentes _coddocente;
    
    @JoinColumn(name = "codporcentaje", referencedColumnName = "codporcentaje")
    @ManyToOne(optional = false)
    private Porcentaje _codporcentaje;

    public TiempoAsignado() {
    }

    public int getCodtiempoasignado() {
        return _codtiempoasignado;
    }

    public void setCodtiempoasignado(int _codtiempoasignado) {
        this._codtiempoasignado = _codtiempoasignado;
    }

    public Date getHoras() {
        return _horas;
    }

    public void setHoras(Date _horas) {
        this._horas = _horas;
    }

    public ActividadObligatoria getCodobliatoria() {
        return _codobliatoria;
    }

    public void setCodobliatoria(ActividadObligatoria _codobliatoria) {
        this._codobliatoria = _codobliatoria;
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
        hash = 61 * hash + this._codtiempoasignado;
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
        final TiempoAsignado other = (TiempoAsignado) obj;
        if (this._codtiempoasignado != other._codtiempoasignado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TiempoAsignado{" + "_horas=" + _horas + ", _coddocente=" + _coddocente + '}';
    }
    
    
}
