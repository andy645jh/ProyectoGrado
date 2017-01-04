package com.proyecto.persistences;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "tipomodalidades")
public class TipoModalidades implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codtipo")
    private int _codtipo;
    
    @Column(name = "nombre")
    @Size(min = 1, max = 100)
    @NotNull
    private String _nombre;     

    public TipoModalidades() {   }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this._codtipo;
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
        final TipoModalidades other = (TipoModalidades) obj;
        if (this._codtipo != other._codtipo) {
            return false;
        }
        if (!Objects.equals(this._nombre, other._nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return _nombre;
    }

    public int getCodtipo() {
        return _codtipo;
    }

    public void setCodtipo(int _codtipo) {
        this._codtipo = _codtipo;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }
   
    
}

