package com.proyecto.persistences;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "productos")
public class Productos implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codproducto")
    private int _codproducto;
    
    @Column(name = "descripcion")
    @Size(min = 1, max = 300)
    @NotNull
    private String _descripcion;
    
    @Column(name = "fechacompromiso") 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date _fechacompromiso;
    
    @Column(name = "fechaentrega")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date _fechaentrega;
    
    @Column(name = "comentarios") 
    private String _comentarios;
    
    @JoinColumn(name = "codact", referencedColumnName = "codactividad")
    @ManyToOne(optional = false)
    private Actividades _codactividad;
    

    public Productos() {  }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this._codproducto;
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
        final Productos other = (Productos) obj;
        if (this._codproducto != other._codproducto) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return _descripcion;
    }

    public int getCodproducto() {
        return _codproducto;
    }

    public void setCodproducto(int _codproducto) {
        this._codproducto = _codproducto;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public Date getFechacompromiso() {
        return _fechacompromiso;
    }

    public void setFechacompromiso(Date _fechacompromiso) {
        this._fechacompromiso = _fechacompromiso;
    }

    public Date getFechaentrega() {
        return _fechaentrega;
    }

    public void setFechaentrega(Date _fechaentrega) {
        this._fechaentrega = _fechaentrega;
    }

    public String getComentarios() {
        return _comentarios;
    }

    public void setComentarios(String _comentarios) {
        this._comentarios = _comentarios;
    }

    public Actividades getCodactividad() {
        return _codactividad;
    }

    public void setCodactividad(Actividades _codactividad) {
        this._codactividad = _codactividad;
    }

}
