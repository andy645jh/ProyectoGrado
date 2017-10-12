package com.proyecto.controllers;

import com.proyecto.utilities.SessionUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

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

    public void openPdf(String cedula, String archivo) throws IOException {
        File file = new File(SessionUtils.getPathReports(Integer.parseInt(cedula)) + archivo);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            // Open file.
            input = new BufferedInputStream(new FileInputStream(file), 10240);

            // Init servlet response.
            response.reset();
            // lire un fichier pdf
            response.setHeader("Content-type", "application/pdf");
            response.setContentLength((int) file.length());

            response.setHeader("Content-disposition", "inline; filename=" + archivo);
            response.setHeader("pragma", "public");
            output = new BufferedOutputStream(response.getOutputStream(), 10240);

            // Write file contents to response.
            byte[] buffer = new byte[10240];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            // Finalize task.
            output.flush();
        } catch (Exception e) {
            System.out.println("AuditorController-> Error: "+e.toString());
        } finally {
            // Gently close streams.

            if (output != null) {
                output.close();
            }
            if (input != null) {
                input.close();
            }
            return;
        }
    }
    /*ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();   
     Map<String, String> params =ec.getRequestParameterMap();
      
     System.out.println("Cedula: " + cedula + " -- Nombre: " + _listaDocs.get(archivo));
     SessionUtils.add("cedula", cedula);
     SessionUtils.add("archivo", _listaDocs.get(archivo));   
        
     //ec.redirect(ec.getRequestContextPath() +"/pdfServlet");
     return ec.getRequestContextPath() +"/pdfServlet";*/

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
