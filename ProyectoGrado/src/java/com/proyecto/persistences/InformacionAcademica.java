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
@Table(name = "info_academica")
public class InformacionAcademica implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codcademico")
    private int codcademico;
    
    @Column(name = "nivel")
    @Size(min = 1, max = 3)
    @NotNull
    private String nivel;
    
    @Column(name = "titulo")    
    @NotNull
    private String titulo;
    
    @Column(name = "institucion")
    @Size(min = 1, max = 100)
    @NotNull
    private String institucion;
       
    @JoinColumn(name = "cod_docente", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Docentes cod_docente;    
        
    @Column(name = "horas")   
    private double _horas;
    
    @Column(name = "tipo")   
    private int tipo = 1;
    
    @Column(name = "registro")    
    private String registro;
    
    @Column(name = "pais")  
    private String pais;
    
     @Column(name = "ano_grado")
    private int ano_grado;
  

    public InformacionAcademica() {  }

    public InformacionAcademica(int codcademico, String nivel, String titulo, String institucion, Docentes cod_docente, double _horas, int tipo, String registro, String pais, int ano_grado) {
        this.codcademico = codcademico;
        this.nivel = nivel;
        this.titulo = titulo;
        this.institucion = institucion;
        this.cod_docente = cod_docente;
        this._horas = _horas;
        this.tipo = tipo;
        this.registro = registro;
        this.pais = pais;
        this.ano_grado = ano_grado;
    }

    public int getCodcademico() {
        return codcademico;
    }

    public void setCodcademico(int codcademico) {
        this.codcademico = codcademico;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public Docentes getCod_docente() {
        return cod_docente;
    }

    public void setCod_docente(Docentes cod_docente) {
        this.cod_docente = cod_docente;
    }

    public double getHoras() {
        return _horas;
    }

    public void setHoras(double _horas) {
        this._horas = _horas;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getAno_grado() {
        return ano_grado;
    }

    public void setAno_grado(int ano_grado) {
        this.ano_grado = ano_grado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.codcademico;
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
        final InformacionAcademica other = (InformacionAcademica) obj;
        if (this.codcademico != other.codcademico) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InformacionAcademica{" + "titulo=" + titulo + '}';
    }

    
    
}

    