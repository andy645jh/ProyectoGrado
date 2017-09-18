package com.proyecto.controllers;

import com.proyecto.utilities.SessionUtils;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author User
 */
@ViewScoped
@ManagedBean
public class ReportTestControllerElkin implements Serializable {

    
    public void crearReport() {       

        FacesContext faces = FacesContext.getCurrentInstance();
        ExternalContext external = faces.getExternalContext();
        HttpSession session = (HttpSession) external.getSession(true);        
        String url = getBase()+"test.xhtml;jsessionid=" + session.getId();
        
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(new URL(url).toString());
            renderer.layout();
            HttpServletResponse response = (HttpServletResponse) external.getResponse();

            response.setContentType("application/pdf");            
            OutputStream os = new FileOutputStream(SessionUtils.getPathReports() + "test54.pdf");
            response.setHeader("Content-Disposition", "inline; filename\"print=file=file-print.dpf\"");
            renderer.createPDF(os);
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        faces.responseComplete();
        System.out.println("Done 54!!");
    }

    public void crearReport26() {           

        
        FacesContext faces = FacesContext.getCurrentInstance();                    
        ExternalContext external = faces.getExternalContext();
        HttpSession session = (HttpSession) external.getSession(true);     
        
        //String url = "http://localhost:8082/ProyectoGradox/faces/test/test_26.xhtml;jsessionid=" + session.getId();        
        String url = getBase()+ "test_26.xhtml;jsessionid=" + session.getId();        
        
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(new URL(url).toString());
            renderer.layout();
            HttpServletResponse response = (HttpServletResponse) external.getResponse();

            response.setContentType("application/pdf");           
            OutputStream os = new FileOutputStream(SessionUtils.getPathReports()+ "test26.pdf");
            response.setHeader("Content-Disposition", "inline; filename\"print=file=file-print.dpf\"");
            renderer.createPDF(os);
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        faces.responseComplete();
        System.out.println("Done 26!!");
    }   
    
    public String getBase()
    {
        FacesContext faces = FacesContext.getCurrentInstance();        
        ExternalContext external = faces.getExternalContext();
        HttpSession session = (HttpSession) external.getSession(true);     
        
        String uri = external.getRequestScheme() + "://" + // "http" + "://
                external.getRequestServerName() + // "myhost"
                ":" + external.getRequestServerPort() + // ":" + "8080"
                external.getRequestContextPath() + // "/people"
                "/faces/test/";
        return uri;
    }    
}
