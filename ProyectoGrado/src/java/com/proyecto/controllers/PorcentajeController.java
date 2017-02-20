package com.proyecto.controllers;

import com.proyecto.facades.ActividadMisionalFacade;
import com.proyecto.facades.CoordinacionFacade;
import com.proyecto.facades.PorcentajeFacade;
import com.proyecto.persistences.ActividadMisional;
import com.proyecto.persistences.Coordinacion;
import com.proyecto.persistences.Porcentaje;
import com.proyecto.persistences.TipoModalidades;
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
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 *
 * @author User
 */
@ManagedBean
@SessionScoped
public class PorcentajeController implements Serializable{

    @EJB
    private PorcentajeFacade _ejbFacade;
    private Porcentaje _obj;
    private FacesMessage message;
    private int _codCoordinacion;
    private int _codActMi;
    @EJB
    private CoordinacionFacade _coordinacionFacade;
    
    @EJB
    private ActividadMisionalFacade _actMisionalFacade;
    
    public PorcentajeController() {
    }
    
    public Porcentaje getCampo()
    {
        if(_obj==null)  _obj= new Porcentaje();
        return _obj;        
    }
    
    public List<Porcentaje> getListado()
    {
        return _ejbFacade.listado();
    }
    
    public void abrirCrear() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/porcentaje/crear", options, null);
    }
    
    public void agregar()
    {
        String titulo,detalle;
        Coordinacion coordinacion= _coordinacionFacade.buscar(_codCoordinacion);
        ActividadMisional actMisional= _actMisionalFacade.buscar(_codActMi);
        _obj.setCodcoordinacion(coordinacion);
        _obj.setCodmisional(actMisional);
                
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);
           
            _ejbFacade.crear(_obj);           
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,detalle);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
            
            Logger.getLogger(Porcentaje.class.getName()).log(Level.SEVERE,null,e);
            
        } 
    }
    
    public SelectItem[] combo(String texto)
    {
        List<Porcentaje> lista =_ejbFacade.listado(); 
        SelectItem[] listaItems = new SelectItem[lista.size()];
        int index=0;
        for (Porcentaje porcentaje : lista) {
            SelectItem item = new SelectItem(porcentaje.getCodporcentaje(), porcentaje.getPorcentaje()+"");
            
            listaItems[index]=item;
            index++;
        }
        System.out.println("LISTADO "+lista.size());
        return listaItems;
    }

    public int getCodCoordinacion() {
        return _codCoordinacion;
    }

    public void setCodCoordinacion(int _codCoordinacion) {
        this._codCoordinacion = _codCoordinacion;
    }

    public int getCodActMi() {
        return _codActMi;
    }

    public void setCodActMi(int _codActMi) {
        this._codActMi = _codActMi;
    }
}
