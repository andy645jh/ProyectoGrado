package com.proyecto.persistences;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
public class Horario implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codhorario")
    private int _codhorario;    
   
    @JoinColumn(name = "codactividad", referencedColumnName = "codactividad")
    @ManyToOne(optional = false)
    private Actividades _codActividad; 
        
    @JoinColumn(name = "coddocente", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Docentes _coddocente;
    
    @JoinColumn(name = "codconvencion", referencedColumnName = "codconvencion")
    @ManyToOne(optional = false)
    private Convenciones _codconvencion;

    @Column(name = "hora")  
    @NotNull
    private int _hora;
    
    @Column(name = "dia")    
    @NotNull
    private int _dia;

    @Transient 
    private boolean _asignado = false;
    
    public int getHora() {
        return _hora;
    }

    public void setHora(int _hora) {
        this._hora = _hora;
    }
    
    public Horario() {
    }

    public int getCodhorario() {
        return _codhorario;
    }

    public void setCodhorario(int _codhorario) {
        this._codhorario = _codhorario;
    }

    public Actividades getCodActividad() {        
        return _codActividad;
    }

    public void setCodActividad(Actividades _nombre) {
        this._codActividad = _nombre;
    }
    
    public Docentes getCoddocente() {
        return _coddocente;
    }

    public void setCoddocente(Docentes _coddocente) {
        this._coddocente = _coddocente;
    }

    public Convenciones getCodconvencion() {
        return _codconvencion;
    }

    public void setCodconvencion(Convenciones _codconvencion) {
        this._codconvencion = _codconvencion;
    }
   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this._codhorario;
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
        final Horario other = (Horario) obj;
        if (this._codhorario != other._codhorario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return _codActividad==null ? "": _codActividad.getNombre();
    }

    public int getDia() {
        return _dia;
    }

    public void setDia(int _diaa) {
        this._dia = _diaa;
    }

    public boolean isAsignado() {
        return _asignado;
    }

    public void setAsignado(boolean _estaAsignado) {
        this._asignado = _estaAsignado;
    }
    
    
}
