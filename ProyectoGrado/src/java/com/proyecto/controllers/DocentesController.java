
package com.proyecto.controllers;

import com.proyecto.utilities.Formulario;
import com.proyecto.utilities.Mensajes;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.persistences.Docentes;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;


@ManagedBean
@SessionScoped
public class DocentesController implements Serializable
{  
    @EJB
    private DocentesFacade _ejbFacade;
    private Docentes _obj;
    
    private String _rutaTxt = "/com/java/utilities/txtDocentes"; 
    private String _titulo="Operacion";
    private String _mensajeCorrecto = "Se ha realizado correctamente";
    private String _mensajeError = "No se completo la operacion";
    private FacesMessage message;
    
    private String usuDocente;
    private LoginController _loginController;
    
    public DocentesController() { }
    
    public Docentes getCampo()
    {
        if(_obj==null)  _obj= new Docentes();
        return _obj;        
    }
    
    public void abrirCrear() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/docentes/crear", options, null);
    }
    
    public void agregar()
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);            
            _ejbFacade.crear(_obj);
            
            //return "crear";
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            Logger.getLogger(Docentes.class.getName()).log(Level.SEVERE,null,e);
        }
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.closeDialog(null);
    }
    
    public SelectItem[] combo(String texto)
    {
        return Formulario.addObject(_ejbFacade.listado(), texto);
    }
    
    public List<Docentes> getListado()
    {
        return _ejbFacade.listado();
    }
        
    public void borrar(Docentes faceObj)
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarExitoso");
            Mensajes.exito(titulo, detalle);
            _ejbFacade.borrar(faceObj);
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Docentes.class.getName()).log(Level.SEVERE,null,e);
        }
    }    
    
    public void abrirActualizar(Docentes objtemp) {
        
        _obj = objtemp;
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        System.out.println("VA A ABRIR ACTUALIZAR");
        RequestContext.getCurrentInstance().openDialog("/docentes/actualizar", options, null);
    }
    
    public void actualizar()
    {
        System.out.println("ENTRO A LA FUNCION ACTUALIZAR");
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);              
            _ejbFacade.actualizar(_obj);
           
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            Logger.getLogger(Docentes.class.getName()).log(Level.SEVERE,null,e);
        }
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.closeDialog(null);
    }  
    
    public void resetear()
    {
        _obj = null;
    }
    
    public void mostrarMensaje()
    {        
        if(message!=null) FacesContext.getCurrentInstance().addMessage("mensajes", message);
        message=null;
    }
    
    public Docentes buscar(){
        System.out.println("USUARIO DE DOCENTE "+_loginController);
       
        
        usuDocente =_loginController.getUsuario();
        return _ejbFacade.buscar(Integer.parseInt(usuDocente));
    }
    
    @FacesConverter(forClass = Docentes.class, value = "docentesConverter")
    public static class DocentesControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                if (value == null || value.length() == 0) return null;
                
                Integer id = Integer.parseInt(value);
                DocentesController controller = (DocentesController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "docentesController");
                System.out.println("Docentse NUMERO ENCONTRADO EN EL COMBO "+controller._ejbFacade.buscar(id));
                return controller._ejbFacade.buscar(id);
            }catch(NumberFormatException e){
                Logger.getLogger(Docentes.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Docentes){
                Docentes obj = (Docentes) value;
                return String.valueOf(obj.getCedula());
            }
            return null;
        }
    }
}
