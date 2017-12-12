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
@Table(name = "participacion")
public class Participacion implements Serializable
{
      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codparticipacion")
    private int codparticipacion;
    
    @Column(name = "tipo_part")
    private String tipo_part;
    
    @Column(name = "evento")  
    @Size(min = 1, max = 200)
    private String evento;
    
    @Column(name = "tema")
    private String tema;
       
    @Column(name = "ambito")
    @Size(min = 1, max = 100)
    private String ambito;
    
    @Column(name = "activ_culturales")
    @Size(min = 1, max = 200)
    private String activ_culturales;
    
    @Column(name = "dedica_cultural")
    @Size(min = 1, max = 200)
    private String dedica_cultural;
    
    @Column(name = "desempeno")
    private String desempeno;
    
    @Column(name = "dedicacion")   
    private Double dedicacion;
    
    @Column(name = "tipo")  
    private int tipo;
        
    @Column(name = "fecha")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
  
    @JoinColumn(name = "coddocente", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Docentes coddocente;
   
    public Participacion() {  }

    public Participacion(int codparticipacion, String tipo_part, String evento, String tema, String ambito, String activ_culturales, String dedica_cultural, String desempeno, Double dedicacion, int tipo, Date fecha, Docentes coddocente) {
        this.codparticipacion = codparticipacion;
        this.tipo_part = tipo_part;
        this.evento = evento;
        this.tema = tema;
        this.ambito = ambito;
        this.activ_culturales = activ_culturales;
        this.dedica_cultural = dedica_cultural;
        this.desempeno = desempeno;
        this.dedicacion = dedicacion;
        this.tipo = tipo;
        this.fecha = fecha;
        this.coddocente = coddocente;
    }

    public int getCodparticipacion() {
        return codparticipacion;
    }

    public void setCodparticipacion(int codparticipacion) {
        this.codparticipacion = codparticipacion;
    }

    public String getTipo_part() {
        return tipo_part;
    }

    public void setTipo_part(String tipo_part) {
        this.tipo_part = tipo_part;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getActiv_culturales() {
        return activ_culturales;
    }

    public void setActiv_culturales(String activ_culturales) {
        this.activ_culturales = activ_culturales;
    }

    public String getDedica_cultural() {
        return dedica_cultural;
    }

    public void setDedica_cultural(String dedica_cultural) {
        this.dedica_cultural = dedica_cultural;
    }

    public String getDesempeno() {
        return desempeno;
    }

    public void setDesempeno(String desempeno) {
        this.desempeno = desempeno;
    }

    public Double getDedicacion() {
        return dedicacion;
    }

    public void setDedicacion(Double dedicacion) {
        this.dedicacion = dedicacion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        hash = 71 * hash + this.codparticipacion;
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
        final Participacion other = (Participacion) obj;
        if (this.codparticipacion != other.codparticipacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Participacion{" + "evento=" + evento + ", tema=" + tema + '}';
    }

    

}

    