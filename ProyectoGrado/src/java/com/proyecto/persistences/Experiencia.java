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
@Table(name = "experiencia")
public class Experiencia implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_experiencia")
    private int cod_experiencia;
    
    @Column(name = "tiempo")
    @NotNull
    private int tiempo;
    
    @Column(name = "institucion")  
    @Size(min = 1, max = 150)
    @NotNull
    private String institucion;
    
    @Column(name = "actividad")
    @Size(min = 1, max = 50)
    @NotNull
    private String actividad;
       
    @Column(name = "dependencia")
    @Size(min = 1, max = 100)
    @NotNull
    private String dependencia;
    
    @Column(name = "tipo_contrato")   
    private String tipo_contrato;
    
    @Column(name = "tipo")    
    @NotNull
    private int tipo = 1;
        
    @Column(name = "fecha_ultima")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_ultima;
  
    @JoinColumn(name = "coddocente", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Docentes coddocente;
   
    public Experiencia() {  }

    public Experiencia(int cod_experiencia, int tiempo, String institucion, String actividad, String dependencia, String tipo_contrato, int tipo, Date fecha_ultima, Docentes coddocente) {
        this.cod_experiencia = cod_experiencia;
        this.tiempo = tiempo;
        this.institucion = institucion;
        this.actividad = actividad;
        this.dependencia = dependencia;
        this.tipo_contrato = tipo_contrato;
        this.tipo = tipo;
        this.fecha_ultima = fecha_ultima;
        this.coddocente = coddocente;
    }

    public int getCod_experiencia() {
        return cod_experiencia;
    }

    public void setCod_experiencia(int cod_experiencia) {
        this.cod_experiencia = cod_experiencia;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getTipo_contrato() {
        return tipo_contrato;
    }

    public void setTipo_contrato(String tipo_contrato) {
        this.tipo_contrato = tipo_contrato;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Date getFecha_ultima() {
        return fecha_ultima;
    }

    public void setFecha_ultima(Date fecha_ultima) {
        this.fecha_ultima = fecha_ultima;
    }

    public Docentes getCoddocente() {
        return coddocente;
    }

    public void setCoddocente(Docentes coddocente) {
        this.coddocente = coddocente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.cod_experiencia;
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
        final Experiencia other = (Experiencia) obj;
        if (this.cod_experiencia != other.cod_experiencia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Experiencia{" + "cod_experiencia=" + cod_experiencia + '}';
    }
    
    

}

    