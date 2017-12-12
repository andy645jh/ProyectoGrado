package com.proyecto.controllers;

import com.proyecto.utilities.Formulario;
import com.proyecto.utilities.Mensajes;
import com.proyecto.facades.ActividadesFacade;
import com.proyecto.facades.AsignacionFacade;
import com.proyecto.facades.ProductosFacade;
import com.proyecto.facades.TipoModalidadesFacade;
import com.proyecto.persistences.Actividades;
import com.proyecto.persistences.Asignacion;
import com.proyecto.persistences.Coordinacion;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Horario;
import com.proyecto.persistences.Productos;
import com.proyecto.persistences.TipoModalidades;
import com.proyecto.utilities.SessionUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.sound.midi.Soundbank;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class ActividadesController implements Serializable {

    @EJB
    private ActividadesFacade _ejbFacade;

    @EJB
    private TipoModalidadesFacade _modalidadFacade;

    @EJB
    private AsignacionFacade _asignacionFacade;

    @EJB
    private ProductosFacade _productosFacade;

    private Actividades _obj;

    private String cedula = "";

    private String _rutaTxt = "/com/java/utilities/txtActividades";
    private String _titulo = "Operacion";
    private String _mensajeCorrecto = "Se ha realizado correctamente";
    private String _mensajeError = "No se completo la operacion";
    private FacesMessage message;
    private int _codigo;
    private double _max;

    private Asignacion _asignacion;
    private Coordinacion _coordinacion;
    private Docentes _docente;
    private double _totalHoras = 0.0;
    
    @PostConstruct
    private void init() {
        _coordinacion = (Coordinacion) SessionUtils.get("coordinacion");
        _docente = (Docentes) SessionUtils.get("docente");
        List<Asignacion> list = _asignacionFacade.buscarA("_coddocente", (_docente.getCedula() + ""));
        _asignacion = list.get(0);
        System.out.println("Asignacion: " + _asignacion);

    }

    public ActividadesController() {
    }

    public Actividades getCampo() {
        if (_obj == null) {
            _obj = new Actividades();
        }
        return _obj;
    }

    public void mostrarMensaje() {
        if (message != null) {
            System.out.println("ES DIFERENTE DE NULL");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, message);
        }
    }

    public void abrirCrear() {
        System.out.print("Enttro");

        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/actividades/crear", options, null);

    }

    public void agregar() {
        String titulo, detalle;
        TipoModalidades modalidad = _modalidadFacade.buscar(_codigo);
        Docentes d = (Docentes) SessionUtils.get("docente");
        Productos producto = new Productos();
       
        _obj.setCodtipo(modalidad);

        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);

            _obj.setCoddocente(d);
            _ejbFacade.crear(_obj);

            producto.setCodactividad(_obj);
            producto.setFechacompromiso(null);
            producto.setFechaentrega(null);
            producto.setComentarios(null);
            producto.setDescripcion(null);
            producto.setCoddocente(d);
        
            _productosFacade.crear(producto);
            
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);

        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);
            Logger.getLogger(Actividades.class.getName()).log(Level.SEVERE, null, e);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
        }
    }

    public double getTotalHoras() {
        _totalHoras=0;
        for (Actividades obj : getListado()) {              
            //calculando horas
            if(obj!=null)
            {
                _totalHoras += obj.getHoras();
            }
        }
        return _totalHoras;
    }
    
    public SelectItem[] combo(String texto) {
        return Formulario.addObject(_ejbFacade.listado(), texto);
    }

    public SelectItem[] comboActividadesRestantes(String texto) {

        Docentes doc = (Docentes) SessionUtils.get("docente");
        cedula = doc.getCedula() + "";
        List<Actividades> lstActividades = _ejbFacade.buscarCampo("_coddocente", cedula);
        List<Productos> lstProductos = _productosFacade.buscarCampo("_coddocente", cedula);
        List<Actividades> lstActFaltantes = new ArrayList<>();
        SelectItem[] lista =new SelectItem[1];;

        for (int i = 0; i < lstActividades.size(); i++) {
            if (_productosFacade.buscarCampo("_codactividad", lstActividades.get(i).getCodactividad() + "").isEmpty()) {
                lstActFaltantes.add(lstActividades.get(i));
                System.out.println("NO ESTA "+lstActividades.get(i).getCodactividad());
            }
        }

        if (!lstActFaltantes.isEmpty()) {
            SelectItem[] listaItems = new SelectItem[lstActFaltantes.size()];
            for (int i = 0; i < lstActFaltantes.size(); i++) {
                SelectItem item = new SelectItem(lstActFaltantes.get(i).getCodactividad(), lstActFaltantes.get(i).getNombre());
                listaItems[i] = item;
            }
            return listaItems;
        }
            SelectItem item = new SelectItem(0, "No hay actividades");
            lista[0] = item;
        
        return lista;
    }

    public SelectItem[] comboFiltrado(String texto) {
        Docentes doc = (Docentes) SessionUtils.get("docente");
//        Docentes doc=docentesFacade.buscar(109877);

        int ced = doc.getCedula();

        List<Actividades> lista = _ejbFacade.buscarCampo("_coddocente", "" + ced);
        SelectItem[] listaItems = new SelectItem[lista.size()];
        int index = 0;
        for (Actividades actividad : lista) {
            SelectItem item = new SelectItem(actividad.getCodactividad(), actividad.getNombre_corto());
            listaItems[index] = item;
            index++;
        }

        return listaItems;
    }

    public String btnBuscar() {
        cedula = _obj.getCoddocente().getCedula() + "";
        return "index_evaluador";
    }

    public List<Actividades> getListarEvaluaciones() {
        if (cedula == "") {
            return new ArrayList<Actividades>();
        } else {
            return _ejbFacade.buscarCampo("_coddocente", cedula);
        }
    }

    public List<Actividades> getListado() {
        Docentes doc = (Docentes) SessionUtils.get("docente");
        cedula = doc.getCedula() + "";

        return _ejbFacade.buscarCampo("_coddocente", cedula);
    }

    public void borrar(Actividades faceObj, HorarioController horaControl) {
        
        String titulo, detalle;

        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarExitoso");
//            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);
            Mensajes.exito(titulo, detalle);
            _ejbFacade.borrar(faceObj);
            horaControl.resetear();
        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Actividades.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void abrirActualizar(Actividades objtemp) {
        _obj = objtemp;
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/actividades/actualizar", options, null);
    }

    public void abrirEvaluacion(Actividades objTemp) {

        _obj = objTemp;
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);

        RequestContext.getCurrentInstance().openDialog("evaluaciones/actualizar", options, null);
    }

    public void hixoClick() {
        System.out.println("HIXO CLICL EN EL BOTON ");
    }

    public void guardarEvaluacion() {
        String titulo, detalle;

        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");

            _ejbFacade.actualizar(_obj);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);

        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);
            Logger.getLogger(Actividades.class.getName()).log(Level.SEVERE, null, e);

        }

        RequestContext context = RequestContext.getCurrentInstance();
        context.closeDialog(null);
    }

    public void error() {
        String titulo, detalle;
        titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
        detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
        Mensajes.error(titulo, detalle);
    }

    public void actualizar() {
        String titulo, detalle;
        TipoModalidades modalidad = _modalidadFacade.buscar(_codigo);
        Docentes d = (Docentes) SessionUtils.get("docente");
        System.out.println("EDITAR DOCENTE   " + d.getCedula());
        _obj.setCodtipo(modalidad);

        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");

//            _obj.setCoddocente(docentesFacade.getCurrentDocente());
            _obj.setCoddocente(d);
            _ejbFacade.actualizar(_obj);

            message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);

        } catch (Exception e) {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");

            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);

            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
            Logger.getLogger(Actividades.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void resetear() {
        _obj = null;
    }

    public int getCodigo() {
        return _codigo;
    }

    public void setCodigo(int _codigo) {
        this._codigo = _codigo;
    }

    public Coordinacion getCoordinacion() {
        return _coordinacion;
    }

    public double getMax() {
        _max = calculateMax();
        System.out.println("Max Antes: " + _max);
        _max -= acumHorasPorTipo(_codigo);
        System.out.println("Max Despues: " + _max);
        return _max;
    }

    private double calculateMax() {
        System.out.println("Codigo: " + _codigo);
        switch (_codigo) {
            case 1:
                return _asignacion.getExtension();

            case 4:
                return _asignacion.getComites();

            case 5:
                return _asignacion.getInvestigacion();

            case 6:
                return _asignacion.getOda();

            case 10:
                return _asignacion.getAcreditacion();

            case 11:
                return _asignacion.getVirtualidad();
        }
        return 0;
    }

    private double acumHorasPorTipo(int tipo) {
        List<Actividades> listado = getListado();
        double acum = 0;
        for (Actividades actividadTemp : listado) {
            if (actividadTemp.getCodtipo().getCodtipo() == tipo) {
                acum += actividadTemp.getHoras();
            }
        }
        return acum;
    }

    public void setMax(double _max) {
        this._max = _max;
    }

    @FacesConverter(forClass = Actividades.class, value = "actividadesConverter")
    public static class ActividadesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try {
                if (value == null || value.length() == 0) {
                    return null;
                }

                Integer id = Integer.parseInt(value);
                ActividadesController controller = (ActividadesController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "actividadesController");
                return controller._ejbFacade.buscar(id);
            } catch (NumberFormatException e) {
                Logger.getLogger(Actividades.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Actividades) {
                Actividades obj = (Actividades) value;
                return String.valueOf(obj.getCodactividad());
            }
            return null;
        }
    }
}
