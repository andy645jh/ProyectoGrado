package com.proyecto.persistences;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
@Table(name = "permisos")
public class Permisos implements Serializable {

    @Id
    @Column(name = "codpermiso")
    private int codpermiso;
    
    @Column(name = "rol")
    @Size(min = 1, max = 100)
    private String rol;
    
    @Column(name = "usuario")
    @Size(min = 1, max = 100)
    private String usuario;
    
    @Column(name = "clave")
    @Size(min = 1, max = 255)
    private String clave;

    public int getCodpermiso() {
        return codpermiso;
    }

    public void setCodpermiso(int codpermiso) {
        this.codpermiso = codpermiso;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codpermiso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos)) {
            return false;
        }
        Permisos other = (Permisos) object;
        if (this.codpermiso != other.codpermiso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.persistences.permisos[ id=" + codpermiso + " ]";
    }
    
}
