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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clases")
public class Clases implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codclase")
    private int _codclase;
    
    @Column(name = "nombre")
    @Size(min = 1, max = 100)
    @NotNull
    private String _nombre;
    
    @NotNull
    @Column(name = "dia")       
    private int _dia; 
    
    @JoinColumn(name = "coddocente", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Docentes _coddocente;
    
    @JoinColumn(name = "codconvencion", referencedColumnName = "codconvencion")
    @ManyToOne(optional = false)
    private Convenciones _codconvencion;    
        
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "horainicio")    
    private Date _codhorainicio;   
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "horafinal")    
    private Date _codhorafinal;  
    
    public Clases() { }
    
    @Override
    public String toString() {
        return  _nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this._codclase;
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
        final Clases other = (Clases) obj;
        if (this._codclase != other._codclase) {
            return false;
        }
        return true;
    }

    public int getCodclase() {
        return _codclase;
    }

    public void setCodclase(int _codclase) {
        this._codclase = _codclase;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public int getDia() {
        return _dia;
    }

    public void setDia(int _dia) {
        this._dia = _dia;
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

    public Date getCodhorainicio() {
        return _codhorainicio;
    }

    public void setCodhorainicio(Date _codhorainicio) {
        this._codhorainicio = _codhorainicio;
    }

    public Date getCodhorafinal() {
        return _codhorafinal;
    }

    public void setCodhorafinal(Date _codhorafinal) {
        this._codhorafinal = _codhorafinal;
    }
    
}
