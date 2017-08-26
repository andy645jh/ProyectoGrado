package com.proyecto.controllers;

import com.proyecto.facades.CoordinacionFacade;
import com.proyecto.utilities.Formulario;
import com.proyecto.utilities.Mensajes;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.facades.PermisosFacade;
import com.proyecto.persistences.Coordinacion;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Permisos;
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
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class DocentesController implements Serializable {

    @EJB
    private DocentesFacade _ejbFacade;
    @EJB
    private CoordinacionFacade _coordFacade;
    @EJB
    private PermisosFacade _permFacade;
    private String clave;
    private String usuario;
    private Docentes _obj;
    private UploadedFile foto;
    private int _codCoord;
    private String _rutaTxt = "/com/java/utilities/txtDocentes";
    private String _titulo = "Operacion";
    private String _mensajeCorrecto = "Se ha realizado correctamente";
    private String _mensajeError = "No se completo la operacion";
    private FacesMessage message;

    private String usuDocente;
    private LoginController _loginController;

    public DocentesController() {
    }

    public Docentes getCampo() {
        if (_obj == null) {
            _obj = new Docentes();
        }
        return _obj;
    }
    
    public void mostrarMensaje() {           
        if(message!=null){
            System.out.println("ES DIFERENTE DE NULL");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, message);
        }
    }

    public void abrirCrear() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/docentes/crear", options, null);
    }

    public void agregar() {

        String titulo, detalle;
//        Coordinacion c = _coordFacade.buscar(_codCoord);
        Docentes doc = _ejbFacade.getCurrentDocente();
        System.out.println("VA A AGREGAR DOCENTE " + doc.getCodcoordinacion());
//        String coordinacion= doc.getCodcoordinacion().getCodcoordinacion()+"";
        _obj.setCodcoordinacion(doc.getCodcoordinacion());
//        _obj.setCodcoordinacion(c);
//        _obj.setCod(c);
        try {
            _ejbFacade.crear(_obj);
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);
            System.out.println("MENSAJE "+message);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);

            //return "crear";
        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);
            Logger.getLogger(Docentes.class.getName()).log(Level.SEVERE, null, e);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
        }

        
    }

    public SelectItem[] combo(String texto) {
        List<Docentes> lista = _ejbFacade.listado();
        SelectItem[] listaItems = new SelectItem[lista.size()];
        int index = 0;
        for (Docentes doc : lista) {
            SelectItem item = new SelectItem(doc.getCedula(), doc.getNombres() + " " + doc.getApellidos());

            listaItems[index] = item;
            index++;
        }

        return listaItems;

    }
    
    public List<Docentes> getListado() {
        
        Docentes doc = _ejbFacade.getCurrentDocente();
        String coordinacion= doc.getCodcoordinacion().getCodcoordinacion()+"";
        return _ejbFacade.buscarCampo("_codcoordinacion",coordinacion);
    }

    public void borrar(Docentes faceObj) {
        String titulo, detalle;

        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarExitoso");
            Mensajes.exito(titulo, detalle);
            _ejbFacade.borrar(faceObj);

        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Docentes.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void abrirActualizar(Docentes objtemp) {

        _obj = objtemp;
        _codCoord = _obj.getCodcoordinacion().getCodcoordinacion();
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        System.out.println("VA A ABRIR ACTUALIZAR");
        RequestContext.getCurrentInstance().openDialog("/docentes/actualizar", options, null);
    }

    public String abrirPerfil() {

        _obj = _ejbFacade.getCurrentDocente();
        System.out.println("CODIGO " + _obj.getFoto());
        
        Permisos p = _permFacade.buscarCampo("usuario", _obj.getCedula() + "");
        clave = p.getClave();
        usuario = p.getUsuario();
        return "/docentes/perfil";
    }

    public void actualizar() {
        String titulo, detalle;
//        Coordinacion c = _coordFacade.buscar(_codCoord);
//        _obj.setCodcoordinacion(c);
        
        Docentes doc = _ejbFacade.getCurrentDocente();
//        String coordinacion= doc.getCodcoordinacion().getCodcoordinacion()+"";
        _obj.setCodcoordinacion(doc.getCodcoordinacion());
        System.out.println("ACTUALIZARRRRRRRRR ");

        if (foto != null) {
            System.out.println("ENTRO A LA FUNCION ACTUALIZAR " + foto.getFileName());
            _obj.setFoto(foto.getFileName());
        }
//        Coordinacion c = _coordFacade.buscar(_codCoord);
//        _obj.setCodcoordinacion(c);
//        System.out.println("VA A AGREGAR DOCENTE "+_obj.getCodcoordinacion());

        try {
            _ejbFacade.actualizar(_obj);
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);
            Mensajes.exito(titulo, detalle);

        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Docentes.class.getName()).log(Level.SEVERE, null, e);
        }

        RequestContext context = RequestContext.getCurrentInstance();
        context.closeDialog(null);
    }

    public void resetear() {
        _obj = null;
    }


    public Docentes buscar() {
        System.out.println("USUARIO DE DOCENTE " + _loginController);

        usuDocente = _loginController.getUsuario();
        return _ejbFacade.buscar(Integer.parseInt(usuDocente));
    }

    public int getCodCoord() {
        return _codCoord;
    }

    public void setCodCoord(int _codCoord) {
        this._codCoord = _codCoord;
    }

    public UploadedFile getFoto() {
        return foto;
    }

    public void setFoto(UploadedFile foto) {
        this.foto = foto;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @FacesConverter(forClass = Docentes.class, value = "docentesConverter")
    public static class DocentesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try {
                if (value == null || value.length() == 0) {
                    return null;
                }

                Integer id = Integer.parseInt(value);
                DocentesController controller = (DocentesController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "docentesController");
                System.out.println("Docentse NUMERO ENCONTRADO EN EL COMBO " + controller._ejbFacade.buscar(id));
                return controller._ejbFacade.buscar(id);
            } catch (NumberFormatException e) {
                Logger.getLogger(Docentes.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Docentes) {
                Docentes obj = (Docentes) value;
                return String.valueOf(obj.getCedula());
            }
            return null;
        }
    }
}
