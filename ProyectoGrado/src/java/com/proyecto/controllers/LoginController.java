package com.proyecto.controllers;

import com.proyecto.facades.DocentesFacade;
import com.proyecto.persistences.Docentes;
import com.proyecto.utilities.Mensajes;
import com.proyecto.utilities.SessionUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    private DocentesFacade docentesFacade;

    private String _usuario;
    private String _clave;
    private String _nombreUsuario;

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

    public String getNombreUsuario() {
        return _nombreUsuario;
    }

    public void setNombreUsuario(String _nombreUsuario) {
        this._nombreUsuario = _nombreUsuario;
    }

    public String login() {

        String titulo = "", detalle = "";

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        System.out.println("CLAVE:1 " + _clave);
        System.out.println("USUARIO:1 " + _usuario);
        try {
            System.out.println("REQUEST: " + request);
            request.login(_usuario, _clave);
            System.out.println("REQUEST:2 " + request);
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("bienvenidos");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("msjEntrada");
            Mensajes.exito(titulo, detalle);

            System.out.println("CLAVE: " + _clave);
            System.out.println("USUARIO: " + _usuario);

            if (request.isUserInRole("administrador")) {
                System.out.println("ADMINISTRADOR");
                Docentes doc = buscarDocente();
                docentesFacade.setCurrentDocente(doc);
                _nombreUsuario = doc.toString();

                SessionUtils.add("docente", doc);
                SessionUtils.add("coordinacion", doc.getCodcoordinacion());

                return "/docentes/listado?faces-redirect=true";
            } else if (request.isUserInRole("docente")) {
                System.out.println("DOCENTES");
                Docentes doc = buscarDocente();
                docentesFacade.setCurrentDocente(doc);
                _nombreUsuario = doc.toString();
                SessionUtils.add("docente", doc);
                SessionUtils.add("coordinacion", doc.getCodcoordinacion());
                return "/actividades/listado?faces-redirect=true";
            } else if (request.isUserInRole("auditor")) {
                System.out.println("AUDITOR");
                return "index_auditor?faces-redirect=true";
            }

            return "index";
        } catch (ServletException e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("msjErrorEntrada");
            Mensajes.error(titulo, detalle);
            System.err.println("ERROR " + e);

            return "index";
        }
    }

    public void logout() {

        /*FacesContext context = FacesContext.getCurrentInstance();
         HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
         try {
         SessionUtils.clear();
         request.logout();
         return "index?redirect=true";
         } catch (ServletException e) {
         context.addMessage(null, new FacesMessage("Logout failed."));
         return "#";
         }*/
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();

        try {
            // Usar el contexto de JSF para invalidar la sesión,
            // NO EL DE SERVLETS (nada de HttpServletRequest)
            ((HttpSession) ctx.getSession(false)).invalidate();
            SessionUtils.clear();
            // Redirección de nuevo con el contexto de JSF,
            // si se usa una HttpServletResponse fallará.
            // Sin embargo, como ya está fuera del ciclo de vida 
            // de JSF se debe usar la ruta completa -_-U
            ctx.redirect(ctxPath + "/faces/index.xhtml");
        } catch (IOException ex) {
            System.out.println("Error");
            ex.printStackTrace();
        }
    }

    public void resetear() {
        _clave = "";
        _usuario = "";
    }

    public Docentes buscarDocente() {
        Docentes doc = docentesFacade.buscar(Integer.parseInt(_usuario));
        System.out.println("BUSCAR DOCENTE " + doc.getNombres());
        return doc;

    }

    public String getUrlLogo() {
        FacesContext context = FacesContext.getCurrentInstance();
        Resource resource = context.getApplication().getResourceHandler().createResource("img/uts3.jpg");
        String url = resource.getRequestPath();
        return url;
    }
}
