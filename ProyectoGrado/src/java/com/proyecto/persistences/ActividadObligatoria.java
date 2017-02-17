package com.proyecto.persistences;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
@Table(name = "actividadobligatorias")
public class ActividadObligatoria implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codactividadobligatorias")
    private int _codactobligatorias;
    
    @Column(name = "nombre")
    @Size(min = 1, max = 100)
    @NotNull
    private String _nombre;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "hora")    
    private Date _hora; 

    public ActividadObligatoria() {
    }

    public int getCodactobligatorias() {
        return _codactobligatorias;
    }

    public void setCodactobligatorias(int _codactobligatorias) {
        this._codactobligatorias = _codactobligatorias;
    }

   

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public Date getHora() {
        return _hora;
    }

    public void setHora(Date _hora) {
        this._hora = _hora;
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this._codactobligatorias;
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
        final ActividadObligatoria other = (ActividadObligatoria) obj;
        if (this._codactobligatorias != other._codactobligatorias) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  _nombre;
    }
    
    
}
