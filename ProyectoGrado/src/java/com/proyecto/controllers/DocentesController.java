package com.proyecto.controllers;

import com.proyecto.facades.AsignacionFacade;
import com.proyecto.facades.CoordinacionFacade;
import com.proyecto.utilities.Mensajes;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.facades.PermisosFacade;
import com.proyecto.persistences.Asignacion;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Permisos;
import com.proyecto.utilities.SessionUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
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
    
    @EJB
    private AsignacionFacade _ejbAsignacion;
 
    private String clave;
    private String usuario;
    private Docentes _doc;
    private UploadedFile foto;
    private int _codCoord;    
    private FacesMessage message;

    private String facultad = "";
    private String coordinacion = "";

    private String usuDocente;
    private LoginController _loginController;
    private StreamedContent _imageDoc;
   
    private int tipoContratoOld = 0;
    private Asignacion asignacion = new Asignacion();

    private UploadedFile _file = null;
    private String _url = "";
    private String _filename;
    
    public DocentesController() {
    }

    public Docentes getCampo() {
        if (_doc == null) {
            _doc = new Docentes();
        }
        return _doc;
    }

    public StreamedContent getImageDoc() {
        Docentes doc = (Docentes) SessionUtils.get("docente");
        try {
            int cedula = doc.getCedula();
            File f = new File(SessionUtils.getPathImages(cedula) + "pedido.png");
            System.out.println("File->>>>> " + f.exists());
            if (!f.exists()) {
                _imageDoc = null;
            } else {
                _imageDoc = (StreamedContent) new DefaultStreamedContent(new FileInputStream(f));
            }
            //_imageDoc = (StreamedContent) new DefaultStreamedContent(new FileInputStream(f));                
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _imageDoc;
    }

    public void mostrarMensaje() {
        if (message != null) {
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

    public void agregar() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String titulo, detalle;
        Docentes doc = (Docentes) SessionUtils.get("docente");
        Permisos p = new Permisos();
        
        
        String password=_doc.getCedula()+"";
        MessageDigest sha256=MessageDigest.getInstance("SHA-256");
        sha256.update(password.getBytes("UTF-8"));
        byte[] digest = sha256.digest();
        StringBuffer sb=new StringBuffer();
        for(byte b : digest) {        
		sb.append(String.format("%02x", b));
	}
        String hash=sb.toString(); 
                
        p.setUsuario(_doc.getCedula()+"");
        if(_doc.getTipo_usuario()==1){
            p.setRol("docente");
        }else if(_doc.getTipo_usuario()==2){
            p.setRol("administrador");
        }else if(_doc.getTipo_usuario()==3){
            p.setRol("auditor");
        }
        
        p.setClave(hash);

        _doc.setCodcoordinacion(doc.getCodcoordinacion());

        configurarAsignacion(_doc.getTipocontrato());

        try {
            _ejbFacade.crear(_doc);
            _permFacade.crear(p);
            asignacion.setCoddocente(_doc);
            asignacion.setCodcoordinacion(doc.getCodcoordinacion());
            
            if (asignacion.getCoddocente() != null) {
                _ejbAsignacion.crear(asignacion);
            }
            System.out.println("SE HA CREADO EL DOCENTE EN ASIGNACION " + _doc.getCedula());

            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);
            System.out.println("MENSAJE " + message);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);

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

        Docentes doc = (Docentes) SessionUtils.get("docente");
        String coordinacion = doc.getCodcoordinacion().getCodcoordinacion() + "";
        return _ejbFacade.buscarCampo("_codcoordinacion", coordinacion);
    }

    public void cambiarEstado(Docentes faceObj) {
        String titulo, detalle;

        if (faceObj.getInhabilitar() == 0) {
            faceObj.setInhabilitar(1);
        } else if (faceObj.getInhabilitar() == 1) {
            faceObj.setInhabilitar(0);
        }

        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarExitoso");
            Mensajes.exito(titulo, detalle);
            _ejbFacade.actualizar(faceObj);

        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Docentes.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void abrirActualizar(Docentes objtemp) {

        _doc = objtemp;
        tipoContratoOld = _doc.getTipocontrato();
        _codCoord = _doc.getCodcoordinacion().getCodcoordinacion();
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        System.out.println("VA A ABRIR ACTUALIZAR");
        RequestContext.getCurrentInstance().openDialog("/docentes/actualizar", options, null);
    }

    public String abrirPerfil() {

        _doc = (Docentes) SessionUtils.get("docente");
        System.out.println("CODIGO " + _doc.getFoto());

        Permisos p = _permFacade.buscarCampo("usuario", _doc.getCedula() + "");
        clave = p.getClave();
        usuario = p.getUsuario();
        return "/docentes/perfil";
    }

    public void actualizar() {
        String titulo, detalle;
        Docentes userSession = (Docentes) SessionUtils.get("docente");
        _doc.setCodcoordinacion(userSession.getCodcoordinacion());
        System.out.println("DocentesController.Actualizar cedula: " + _doc.getCedula());

        if (foto != null) {
            System.out.println("ENTRO A LA FUNCION ACTUALIZAR " + foto.getFileName());
            _doc.setFoto(foto.getFileName());
        }

        asignacion = _ejbAsignacion.buscarDocente("_coddocente", _doc.getCedula() + "");
        configurarAsignacion(_doc.getTipocontrato());

        try {
            _ejbFacade.actualizar(_doc);
            _ejbAsignacion.actualizar(asignacion);
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

    private void configurarAsignacion(int tipoContrato) {
        //tiempo completo=1
        //medio tiempo=2
        //catedra=3        
        if (tipoContrato == 1) {
            asignacion.setHorasclase(24.0);
            asignacion.setPreparacion(4.0);
            asignacion.setCapacitacion(4.0);
            asignacion.setSumatoria(32.0);
        } else {
            asignacion.setHorasclase(12.0);
            asignacion.setPreparacion(2.0);
            asignacion.setCapacitacion(0.0);
            asignacion.setSumatoria(14.0);
        }
        /*asignacion.setColectivo(0.0);
         asignacion.setInvestigacion(0.0);
         asignacion.setSocial(0.0);
         asignacion.setOda(0.0);
         asignacion.setPlaneacion(0.0);
         asignacion.setVirtualidad(0.0);
         asignacion.setComites(0.0);*/
    }

    public void resetear() {
        _doc = null;
    }

    public Docentes buscar() {
        System.out.println("USUARIO DE DOCENTE " + _loginController);

        usuDocente = _loginController.getUsuario();
        return _ejbFacade.buscar(Integer.parseInt(usuDocente));
    }

    public List<Docentes> getListarDocentesFiltrado() {
        if (coordinacion.equals("")) {
            return new ArrayList<Docentes>();
        } else {
            return _ejbFacade.buscarCampo("_codcoordinacion", coordinacion);
        }
    }

        public void upload(/*FileUploadEvent event*/) {
        //_file = event.getFile();
        if (_file.getFileName().length() <= 0) {
            Mensajes.error("Ha ocurrido algo", "No se a seleccionado ningun archivo");
            System.out.println("No se a seleccionado ningun archivo");
            return;
        }

        try {
            String str = _file.getFileName();
            String ext = str.substring(str.lastIndexOf('.'), str.length());
            if (ext.contains("png") || ext.contains("jpg") || ext.contains("jpeg") && _file.getSize() > 0) {
                //copyFile(_file.getFileName(), _file.getInputstream());
                copyFile("pedido.png", _file.getInputstream());
                /*_doc.setFoto(_url);
                _ejbFacade.actualizar(_doc);*/
            } else {
                Mensajes.error("Ha ocurrido algo", "No se puede subir ese archivo");
                System.out.println("Solo se aceptan archivos de formato .png, .jpg, .jpeg");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void copyFile(String fileName, InputStream in) {

        try {
            Docentes doc = (Docentes) SessionUtils.get("docente");
            _url = SessionUtils.getPathImages(doc.getCedula()) + fileName;
            OutputStream out = new FileOutputStream(new File(_url));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();
            System.out.println("Archivo creado en: " + (_url));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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

    public Docentes getDoc() {
        return _doc;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getCoordinacion() {
        return coordinacion;
    }

    public void setCoordinacion(String coordinacion) {
        this.coordinacion = coordinacion;
    }

    public UploadedFile getFile() {
        return _file;
    }

    public void setFile(UploadedFile _file) {
        this._file = _file;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String _url) {
        this._url = _url;
    }

    public String getFilename() {
        return _filename;
    }

    public void setFilename(String _filename) {
        this._filename = _filename;
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
