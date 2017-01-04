
package com.proyecto.controllers;

import com.proyecto.facades.DocentesFacade;
import com.proyecto.persistences.Docentes;
import com.proyecto.utilities.Mensajes;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@ManagedBean
@SessionScoped
public class LoginController implements Serializable{
    @EJB
    private DocentesFacade docentesFacade;
    
    private String _usuario;
    private String _clave;
    
    public LoginController() {
    }
    
    public String getUsuario() {
        return _usuario;
    }

    public void setUsuario(String _usuario) {
        this._usuario = _usuario;
    }

    public String getClave() {
        return _clave;
    }

    public void setClave(String _clave) {
        this._clave = _clave;
    }
    
    public String login(){
        
        String titulo="", detalle="";
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        System.out.println("CLAVE:1 " + _clave);
            System.out.println("USUARIO:1 " + _usuario);
        try 
        {         
            System.out.println("REQUEST: " + request);
            request.login(_usuario, _clave);
             System.out.println("REQUEST:2 " + request);
            titulo=ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("bienvenidos");
            detalle=ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("msjEntrada");
            Mensajes.exito(titulo, detalle);
            
            
            
            System.out.println("CLAVE: " + _clave);
            System.out.println("USUARIO: " + _usuario);
            
            
            if(request.isUserInRole("administrador"))
            {   
                System.out.println("ADMINISTRADOR");           
                return "index_admin?faces-redirect=true";
            }else if(request.isUserInRole("docente"))
            {
                System.out.println("DOCENTES");
                docentesFacade.setCurrentDocente(buscarDocente());
                return "index_docentes?faces-redirect=true";
            }else if(request.isUserInRole("evaluador"))
            {
                System.out.println("EVALUADOR");
                return "index_evaluador?faces-redirect=true";
            }
            
            return "index";
        } catch (ServletException e)
        {          
            titulo=ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle=ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("msjErrorEntrada");
            Mensajes.error(titulo, detalle);
            System.err.println("ERROR "+e);

            return "index";
        }
    }
    
    
    public String logout() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
          request.logout();
          return "/faces/index?faces-redirect=true";
        } catch (ServletException e) {
          context.addMessage(null, new FacesMessage("Logout failed."));
          return "#";
        }
      }
    
    public void resetear()
    {
        _clave="";
        _usuario="";
    }
    
    public Docentes buscarDocente(){
        Docentes doc = docentesFacade.buscar(Integer.parseInt(_usuario));
        System.out.println("BUSCAR DOCENTE "+doc.getNombres());
        return doc;
        
    }
    
    
}
