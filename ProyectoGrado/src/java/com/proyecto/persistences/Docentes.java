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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "docentes")
public class Docentes implements Serializable
{
    @Id
    @Column(name = "cedula")
    private int _cedula;
    
    @Column(name = "nombres")
    @Size(min = 1, max = 100)
    @NotNull
    private String _nombres;
    
    @Column(name = "apellidos")
    @Size(min = 1, max = 100)
    @NotNull
    private String _apellidos;   
    
    @Column(name = "codigo")
    private String _codigo;  
      
    
    @Column(name = "direccion")
    @Size(min = 1, max = 50)
    private String _direccion;
        
    @Column(name = "telefono")
    @Size(min = 1, max = 50)
    private String _telefono;
        
    @Column(name = "correo")
    @Size(min = 1, max = 50)
    private String _correo;
        
    @Size(min = 1, max = 200)
    @Column(name = "foto") 
    private String _foto;
    
    @JoinColumn(name = "codcoordinacion", referencedColumnName = "codcoordinacion")
    @ManyToOne(optional = false)
    private Coordinacion _codcoordinacion;   
    
    @Column(name = "formacion")
    private String _formacion; 
    
    @NotNull
    @Column(name = "tipo_contrato")
    private int _tipocontrato; 
    
    @Column(name = "fechanac")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date _fechanac;
    
    @Column(name = "celular")
    private int celular;
    
    @Column(name = "tipo_doc")
    private int tipo_doc;
    
    @Column(name = "lugar_exp")
    private String lugar_exp;
    
    @Column(name = "municipio")
    private String municipio;
    
    @Column(name = "lugar_nac")
    private String lugar_nac;
    
    @Column(name = "genero")
    private int genero;
    
    @Column(name = "fecha_exp")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_exp;
    
    @Column(name = "matricula_prof")
    private String matricula_prof;
    
    @Transient
    private String _nombreCoord;
   
    public Docentes() { }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this._cedula;
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
        final Docentes other = (Docentes) obj;
        if (this._cedula != other._cedula) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return _nombres + " " + _apellidos ;
    }
    
    public int getCedula() {
        return _cedula;
    }

    public void setCedula(int cedula) {
        this._cedula = cedula;
    }

    public String getNombres() {
        return _nombres;
    }

    public void setNombres(String _nombres) {
        this._nombres = _nombres;
    }

    public String getApellidos() {
        return _apellidos;
    }

    public void setApellidos(String _apellidos) {
        this._apellidos = _apellidos;
    }

    public String getDireccion() {
        return _direccion;
    }

    public void setDireccion(String _direccion) {
        this._direccion = _direccion;
    }

    public String getTelefono() {
        return _telefono;
    }

    public void setTelefono(String _telefono) {
        this._telefono = _telefono;
    }

    public String getCorreo() {
        return _correo;
    }

    public void setCorreo(String _correo) {
        this._correo = _correo;
    }

    public String getFoto() {
        return _foto;
    }

    public void setFoto(String _foto) {
        this._foto = _foto;
    }

    public Coordinacion getCodcoordinacion() {
        return _codcoordinacion;
    }

    public void setCodcoordinacion(Coordinacion _codcoordinacion) {
        this._codcoordinacion = _codcoordinacion;
    }
    
    public String getFormacion() {
        return _formacion;
    }

    public void setFormacion(String _formacion) {
        this._formacion = _formacion;
    }

    public String getCodigo() {
        return _codigo;
    }

    public void setCodigo(String _codigo) {
        this._codigo = _codigo;
    }

    public Date getFechanac() {
        return _fechanac;
    }

    public void setFechanac(Date _fechanac) {
        this._fechanac = _fechanac;
    }

    public int getTipocontrato() {
        return _tipocontrato;
    }

    public void setTipocontrato(int _tipocontrato) {
        this._tipocontrato = _tipocontrato;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public int getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(int tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public String getLugar_exp() {
        return lugar_exp;
    }

    public void setLugar_exp(String lugar_exp) {
        this.lugar_exp = lugar_exp;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLugar_nac() {
        return lugar_nac;
    }

    public void setLugar_nac(String lugar_nac) {
        this.lugar_nac = lugar_nac;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public Date getFecha_exp() {
        return fecha_exp;
    }

    public void setFecha_exp(Date fecha_exp) {
        this.fecha_exp = fecha_exp;
    }

    public String getMatricula_prof() {
        return matricula_prof;
    }

    public void setMatricula_prof(String matricula_prof) {
        this.matricula_prof = matricula_prof;
    }

    public String getNombreCoord() {
        _nombreCoord = _codcoordinacion.getNombre();
        return _nombreCoord;
    }
    
    
}
