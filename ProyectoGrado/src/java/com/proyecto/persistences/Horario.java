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
public class Horario implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codhorario")
    private int _codhorario;
    
    @Column(name = "dia")
    @Size(min = 1, max = 300)
    @NotNull
    private String _dia;
    
    @Column(name = "nombre")
    @Size(min = 1, max = 300)
    @NotNull
    private String _nombre;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "hora_inicio")    
    private Date _horainicio;   
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "hora_fin")    
    private Date _horafinal;  
        
    @JoinColumn(name = "coddocente", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Docentes _coddocente;
    
    @JoinColumn(name = "codconvencion", referencedColumnName = "codconvencion")
    @ManyToOne(optional = false)
    private Convenciones _codconvencion;

    
    private int _hora;
    private int _diaa;

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

    public String getDia() {
        return _dia;
    }

    public void setDia(String _dia) {
        this._dia = _dia;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
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
        return "Horario{" + "_nombre=" + _nombre + '}';
    }

    public int getDiaa() {
        return _diaa;
    }

    public void setDiaa(int _diaa) {
        this._diaa = _diaa;
    }
    
    
}
