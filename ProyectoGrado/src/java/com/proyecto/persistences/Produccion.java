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
@Table(name = "produccion")
public class Produccion implements Serializable
{
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codproduccion")
    private int codproduccion;
    
    @Column(name = "tipo_prod_part")
    @NotNull
    private String tipo_prod_part;
    
    @Column(name = "nombre")  
    @NotNull
    private String nombre;
    
    @Column(name = "editorial_inv")
    @Size(min = 1, max = 200)
    @NotNull
    private String editorial_inv;
       
    @Column(name = "libros")
    @Size(min = 1, max = 100)
    private String libros;
    
    @Column(name = "estado")   
    private String estado;
    
    @Column(name = "tipo")    
    @NotNull
    private int tipo=1;
        
    @Column(name = "meses")
    private int meses;
    
    @Column(name = "num_anos")
    private int num_anos;
    
    @Column(name = "ano")
    private int ano;
  
    @JoinColumn(name = "coddocente", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Docentes coddocente;
   
    public Produccion() {  }

    public Produccion(int codproduccion, String tipo_prod_part, String nombre, String editorial_inv, String libros, String estado, int tipo, Date meses, int ano, Docentes coddocente) {
        this.codproduccion = codproduccion;
        this.tipo_prod_part = tipo_prod_part;
        this.nombre = nombre;
        this.editorial_inv = editorial_inv;
        this.libros = libros;
        this.estado = estado;
        this.tipo = tipo;
        this.ano = ano;
        this.coddocente = coddocente;
    }

    public int getCodproduccion() {
        return codproduccion;
    }

    public void setCodproduccion(int codproduccion) {
        this.codproduccion = codproduccion;
    }

    public String getTipo_prod_part() {
        return tipo_prod_part;
    }

    public void setTipo_prod_part(String tipo_prod_part) {
        this.tipo_prod_part = tipo_prod_part;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEditorial_inv() {
        return editorial_inv;
    }

    public void setEditorial_inv(String editorial_inv) {
        this.editorial_inv = editorial_inv;
    }

    public String getLibros() {
        return libros;
    }

    public void setLibros(String libros) {
        this.libros = libros;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public int getNum_anos() {
        return num_anos;
    }

    public void setNum_anos(int num_anos) {
        this.num_anos = num_anos;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Docentes getCoddocente() {
        return coddocente;
    }

    public void setCoddocente(Docentes coddocente) {
        this.coddocente = coddocente;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.codproduccion;
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
        final Produccion other = (Produccion) obj;
        if (this.codproduccion != other.codproduccion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produccion{" + "nombre=" + nombre + '}';
    }

    
}

    