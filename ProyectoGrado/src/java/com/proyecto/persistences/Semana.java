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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
public class Semana implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codsemana")
    private int _codsemana;
        
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_inicio")    
    private Date _horainicio;   
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_fin")    
    private Date _horafinal;  


    public Semana() {
    }

    public int getCodsemana() {
        return _codsemana;
    }

    public void setCodsemana(int _codsemana) {
        this._codsemana = _codsemana;
    }

    public Date getHorainicio() {
        return _horainicio;
    }

    public void setHorainicio(Date _horainicio) {
        this._horainicio = _horainicio;
    }

    public Date getHorafinal() {
        return _horafinal;
    }

    public void setHorafinal(Date _horafinal) {
        this._horafinal = _horafinal;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this._codsemana;
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
        final Semana other = (Semana) obj;
        if (this._codsemana != other._codsemana) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Horario{" + "_nombre=" + _codsemana + '}';
    }
    
    
}
