package com.proyecto.controllers;

import com.proyecto.persistences.Docentes;
import com.proyecto.utilities.AutoFileCloser;
import com.proyecto.utilities.SessionUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author User
 */
@SessionScoped
@ManagedBean
public class ReportController implements Serializable {

    private String _cedula;
    private String _archivo;

    public void crearReport() throws IOException {
        Docentes doc = (Docentes) SessionUtils.get("docente");
        _cedula = doc.getCedula() + "";
        FacesContext faces = FacesContext.getCurrentInstance();
        ExternalContext external = faces.getExternalContext();
        HttpSession session = (HttpSession) external.getSession(true);
        String url = getBase() + "reporte_54.xhtml;jsessionid=" + session.getId();
        HttpServletResponse response = (HttpServletResponse) external.getResponse();
        String ruta = "";

        try {

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(new URL(url).toString());           
            renderer.layout();
            
            //reporte unico
            _archivo = "reporte_54.pdf";
            //_archivo = "reporte_54_"+SessionUtils.getYear()+"_"+SessionUtils.getSemestre()+".pdf";
            ruta = SessionUtils.getPathReports(doc.getCedula()) + _archivo;
            OutputStream os = new FileOutputStream(ruta);
            //response.setHeader("Content-Disposition", "inline; filename=reporte_54.pdf");               
            renderer.createPDF(os);
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        faces.responseComplete();
        System.out.println("Done 54!! -> " + ruta);
    }

    public void crearReport26() {
        Docentes doc = (Docentes) SessionUtils.get("docente");
        _cedula = doc.getCedula() + "";
        FacesContext faces = FacesContext.getCurrentInstance();
        ExternalContext external = faces.getExternalContext();
        HttpSession session = (HttpSession) external.getSession(true);
        HttpServletResponse response = (HttpServletResponse) external.getResponse();
        String url = getBase() + "reporte_26.xhtml;jsessionid=" + session.getId();
        String ruta = "";
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(new URL(url).toString());          
            renderer.layout();
            //reporte unico
            
            _archivo = "reporte_26.pdf";
            //_archivo = "reporte_26_"+SessionUtils.getYear()+"_"+SessionUtils.getSemestre()+".pdf";
            ruta = SessionUtils.getPathReports(doc.getCedula()) + _archivo;
            // http:myhost:port
            //ruta = external.getRequestScheme() + "://"+ external.getRequestServerName()+ ":" + external.getRequestServerPort()+"/7969/"+_archivo;
         
            System.out.println("ReportController---> Creando PDF");
            OutputStream os = new FileOutputStream(ruta);
            renderer.createPDF(os);
            System.out.println("ReportController---> Cerrando Stream PDF");
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        faces.responseComplete();        
        System.out.println("Done 26!! -> " + ruta);
    }

    public String getBase() {
        FacesContext faces = FacesContext.getCurrentInstance();
        ExternalContext external = faces.getExternalContext();
        HttpSession session = (HttpSession) external.getSession(true);

        String uri = external.getRequestScheme() + "://"
                + // "http" + "://
                external.getRequestServerName()
                + // "myhost"
                ":" + external.getRequestServerPort()
                + // ":" + "8080"
                external.getRequestContextPath()
                + // "/people"
                "/faces/reportes/";
        return uri;
    }
    /*
     public void openPdf(String cedula, String archivo) throws IOException{
     System.out.println("Cedula: " + _cedula + " -- Nombre: " + _archivo);
     ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();   
     SessionUtils.add("cedula", cedula);
     SessionUtils.add("archivo", archivo);
     ec.redirect(ec.getRequestContextPath() +"/pdfServlet");
     }
     */

    public String getCedula() {
        return _cedula;
    }

    public String getArchivo() {
        return _archivo;
    }
}
