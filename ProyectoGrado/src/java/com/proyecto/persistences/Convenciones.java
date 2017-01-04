package com.proyecto.persistences;

import java.io.Serializable;
import java.util.Objects;
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

@Entity
@Table(name = "convenciones")
public class Convenciones implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codconvencion")
    private int _codconvencion;
    
    @Column(name = "nombre")
    @Size(min = 1, max = 100)
    @NotNull
    private String _nombre;
    
   
    

    public Convenciones() {  }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this._codconvencion;
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
        final Convenciones other = (Convenciones) obj;
        if (this._codconvencion != other._codconvencion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return _nombre;
    }
    
    public int getCodconvencion() {
        return _codconvencion;
    }

    public void setCodconvencion(int _codconvencion) {
        this._codconvencion =_codconvencion;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

}
