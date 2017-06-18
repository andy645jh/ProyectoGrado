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
@Table(name = "socializacion")
public class Socializacion implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codsocializar")
    private int codsocializar;
  
    
    @Column(name = "articulo")  
    @Size(min = 1, max = 200)
    @NotNull
    private String articulo;
    
    @Column(name = "conferencia")
    @Size(min = 1, max = 200)
    @NotNull
    private String conferencia;
       
    @Column(name = "informe")
    @NotNull
    private String informe;
    
    
    @JoinColumn(name = "coddocente", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Docentes coddocente;
   
    public Socializacion() {  }

    public Socializacion(int codsocializar, String articulo, String conferencia, String informe, Docentes coddocente) {
        this.codsocializar = codsocializar;
        this.articulo = articulo;
        this.conferencia = conferencia;
        this.informe = informe;
        this.coddocente = coddocente;
    }

    public int getCodsocializar() {
        return codsocializar;
    }

    public void setCodsocializar(int codsocializar) {
        this.codsocializar = codsocializar;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getConferencia() {
        return conferencia;
    }

    public void setConferencia(String conferencia) {
        this.conferencia = conferencia;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
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
        hash = 97 * hash + this.codsocializar;
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
        final Socializacion other = (Socializacion) obj;
        if (this.codsocializar != other.codsocializar) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Socializacion{" + "articulo=" + articulo + ", conferencia=" + conferencia + '}';
    }
    
    

}

    