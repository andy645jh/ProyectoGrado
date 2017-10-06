package com.proyecto.controllers;

import com.proyecto.utilities.SessionUtils;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class AuditorController implements Serializable {

    private List<String> _listaDocs;

    public AuditorController() {

    }

    public List<String> getListaDocs(int cedula) {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        //int cedula = Integer.parseInt(params.get("doc"));
        //System.out.println("Listado de Docs");
        //int cedula = doc.getCedula();
        File f = new File(SessionUtils.getPathReports(cedula));
        File[] files = f.listFiles();
        _listaDocs = new ArrayList();
        for (File fTemp : files) {
            String extension = "";

            if (fTemp.getName().lastIndexOf(".") != -1 && fTemp.getName().lastIndexOf(".") != 0) {
                extension = fTemp.getName().substring(fTemp.getName().lastIndexOf(".") + 1);
                if (extension.equals("pdf")) {
                    _listaDocs.add(fTemp.getName());
                }
            }
            //System.out.println("Archivo: " + extension);

        }
        return _listaDocs;
    }

    public String openPdf(String cedula, int archivo){
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();   
        Map<String, String> params =ec.getRequestParameterMap();
        /*String cedula = params.get("cedula");
        String archivo = params.get("archivo");*/
        System.out.println("Cedula: " + cedula + " -- Nombre: " + _listaDocs.get(archivo));
        SessionUtils.add("cedula", cedula);
        SessionUtils.add("archivo", _listaDocs.get(archivo));   
        
        //ec.redirect(ec.getRequestContextPath() +"/pdfServlet");
        return ec.getRequestContextPath() +"/pdfServlet";
    }
    
    /*public String openPdf() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext(); 
        Map<String, String> params =ec.getRequestParameterMap();
        String cedula = params.get("cedula");
        String archivo = params.get("archivo");
        System.out.println("Cedula 1: " + cedula + " -- Nombre 1: " + archivo);
          
        SessionUtils.add("cedula", cedula);
        SessionUtils.add("archivo", archivo);   
        
        //ec.redirect(ec.getRequestContextPath() +"/pdfServlet");
        return ec.getRequestContextPath() +"/pdfServlet";
    }*/
}
