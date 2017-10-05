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
        List<String> docs = new ArrayList();
        for (File fTemp : files) {
            String extension = "";

            if (fTemp.getName().lastIndexOf(".") != -1 && fTemp.getName().lastIndexOf(".") != 0) {
                extension = fTemp.getName().substring(fTemp.getName().lastIndexOf(".") + 1);
                if (extension.equals("pdf")) {
                    docs.add(fTemp.getName());
                }
            }
            //System.out.println("Archivo: " + extension);

        }
        return docs;
    }

    public String openPdf(String cedula, String archivo) throws IOException{
        System.out.println("Cedula: " + cedula + " -- Nombre: " + archivo);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();   
        SessionUtils.add("cedula", cedula);
        SessionUtils.add("archivo", archivo);   
        
        //ec.redirect(ec.getRequestContextPath() +"/pdfServlet");
        return ec.getRequestContextPath() +"/pdfServlet";
    }
}
