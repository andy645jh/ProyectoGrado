
package com.proyecto.controllers;

import com.proyecto.utilities.Formulario;
import com.proyecto.utilities.Mensajes;
import com.proyecto.facades.ActividadesFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.facades.TipoModalidadesFacade;
import com.proyecto.persistences.Actividades;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.TipoModalidades;
import java.io.Serializable;
import java.util.ArrayList;
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
public class ActividadesController implements Serializable
{  
    @EJB
    private ActividadesFacade _ejbFacade;
    
    @EJB
    private DocentesFacade docentesFacade;
    
    @EJB TipoModalidadesFacade _modalidadFacade;
    
    private Actividades _obj;
    
    private String cedula="";
    
    private String _rutaTxt = "/com/java/utilities/txtActividades"; 
    private String _titulo="Operacion";
    private String _mensajeCorrecto = "Se ha realizado correctamente";
    private String _mensajeError = "No se completo la operacion";
    private FacesMessage message;
    private int _codigo;
    
    public ActividadesController() { }
    
    public Actividades getCampo()
    {  if(_obj==null)  _obj= new Actividades();
        return _obj;        
    }
      
    
    public void abrirCrear() {
        System.out.print("Enttro");
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/actividades/crear", options, null);
        
    }
    
    public void agregar()
    {
        String titulo,detalle;
        TipoModalidades modalidad= _modalidadFacade.buscar(_codigo);
        Docentes d=docentesFacade.getCurrentDocente();
        System.out.println("CREAR DOCENTE   "+d.getCedula());
        _obj.setCodtipo(modalidad);
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);
            
            _obj.setCoddocente(d);
            _ejbFacade.crear(_obj);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            Logger.getLogger(Actividades.class.getName()).log(Level.SEVERE,null,e);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
        }
    }
    
    public SelectItem[] combo(String texto)
    {
        return Formulario.addObject(_ejbFacade.listado(), texto);
    }
    
    public SelectItem[] comboFiltrado(String texto)
    {
        Docentes doc=docentesFacade.getCurrentDocente();
//        Docentes doc=docentesFacade.buscar(109877);
        
        int ced = doc.getCedula();
        
        List<Actividades> lista =_ejbFacade.buscarCampo("_coddocente", ""+ced); 
        SelectItem[] listaItems = new SelectItem[lista.size()];
        int index=0;
        for (Actividades actividad : lista) {
            SelectItem item = new SelectItem(actividad.getCodactividad(), actividad.getNombre());
            
            listaItems[index]=item;
            index++;
        }
        
        return listaItems;
    }
    
    public String btnBuscar()
    {       
        cedula= _obj.getCoddocente().getCedula()+"";        
        return "index_evaluador";
    }
    
    
    public List<Actividades> getListarEvaluaciones()
    {
       if(cedula==""){
            return new ArrayList<Actividades>();
       }else{
            return _ejbFacade.buscarCampo("_coddocente",cedula);
       }
    }
     
    public List<Actividades> getListado()
    {
        Docentes doc = docentesFacade.getCurrentDocente();
        cedula= doc.getCedula()+"";
//        cedula= "109877";
        return _ejbFacade.buscarCampo("_coddocente",cedula);
    }
    
    
    public void borrar(Actividades faceObj)
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);
            _ejbFacade.borrar(faceObj);
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Actividades.class.getName()).log(Level.SEVERE,null,e);
        }
    }    
    
    public void abrirActualizar(Actividades objtemp)
    {
        _obj = objtemp;
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);       
        RequestContext.getCurrentInstance().openDialog("/actividades/actualizar", options, null);
    }
    
    public void abrirEvaluacion(Actividades objTemp) {
        
        _obj= objTemp;
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
       
        RequestContext.getCurrentInstance().openDialog("evaluaciones/actualizar", options, null);
    }
    
    public void hixoClick(){
        System.out.println("HIXO CLICL EN EL BOTON ");
    }
    
    public void guardarEvaluacion()
    {        
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
         
            _ejbFacade.actualizar(_obj);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);          
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            Logger.getLogger(Actividades.class.getName()).log(Level.SEVERE,null,e);
          
        }
        
        RequestContext context = RequestContext.getCurrentInstance();  
        context.closeDialog(null);
    }  
    
    public void error()
    {
        String titulo,detalle;
        titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
        detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
        Mensajes.error(titulo, detalle);
    }
    
    public void mostrarMensaje()
    {        
        if(message!=null) FacesContext.getCurrentInstance().addMessage("mensajes", message);
        message=null;
    }
    
    public void actualizar()
    {        
        String titulo,detalle;
        TipoModalidades modalidad= _modalidadFacade.buscar(_codigo);
        Docentes d=docentesFacade.getCurrentDocente();
        System.out.println("EDITAR DOCENTE   "+d.getCedula());
        _obj.setCodtipo(modalidad);
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
                       
//            _obj.setCoddocente(docentesFacade.getCurrentDocente());
            _obj.setCoddocente(d);
            _ejbFacade.actualizar(_obj);
            
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);
            RequestContext context = RequestContext.getCurrentInstance();  
            context.closeDialog(null);
                        
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
           
            
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
            Logger.getLogger(Actividades.class.getName()).log(Level.SEVERE,null,e);
        }
    }  
    
    public void resetear()
    {
        _obj = null;
    }  

    public int getCodigo() {
        return _codigo;
    }

    public void setCodigo(int _codigo) {
        this._codigo = _codigo;
    }
    
    @FacesConverter(forClass = Actividades.class, value = "actividadesConverter")
    public static class ActividadesControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                if (value == null || value.length() == 0) return null;
                
                Integer id = Integer.parseInt(value);
                ActividadesController controller = (ActividadesController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "actividadesController");
                return controller._ejbFacade.buscar(id);
            }catch(NumberFormatException e){
                Logger.getLogger(Actividades.class.getName()).log(Level.SEVERE, null, e);               
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Actividades){
                Actividades obj = (Actividades) value;
                return String.valueOf(obj.getCodactividad());
            }
            return null;
        }
    }
}
