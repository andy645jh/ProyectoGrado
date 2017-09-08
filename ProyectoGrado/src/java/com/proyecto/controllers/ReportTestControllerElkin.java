package com.proyecto.controllers;

import java.io.ByteArrayOutputStream;
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
import org.primefaces.model.StreamedContent;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author User
 */
@ViewScoped
@ManagedBean
public class ReportTestControllerElkin implements Serializable {

//    private static final Logger log = LoggerFactory.getLogger(ReportTestController.class);
    private StreamedContent media;
    private ByteArrayOutputStream outputStream;
       

    public void crearReport() {       

        FacesContext faces = FacesContext.getCurrentInstance();
        ExternalContext external = faces.getExternalContext();
        HttpSession session = (HttpSession) external.getSession(true);        
        String url = "http://localhost:8082/ProyectoGradox/faces/test/test.xhtml;jsessionid=" + session.getId();
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(new URL(url).toString());
            renderer.layout();
            HttpServletResponse response = (HttpServletResponse) external.getResponse();

            response.setContentType("application/pdf");
            String HTML_TO_PDF = "C:\\informes_jasper\\test54.pdf";
            OutputStream os = new FileOutputStream(HTML_TO_PDF);
            response.setHeader("Content-Disposition", "inline; filename\"print=file=file-print.dpf\"");
            //OutputStream outputStream = response.getOutputStream();

            renderer.createPDF(os);
            os.close();
            //outputStream.close();

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
        String url = "http://localhost:8082/ProyectoGradox/faces/test/test_26.xhtml;jsessionid=" + session.getId();
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(new URL(url).toString());
            renderer.layout();
            HttpServletResponse response = (HttpServletResponse) external.getResponse();

            response.setContentType("application/pdf");
            String HTML_TO_PDF = "C:\\informes_jasper\\test26.pdf";
            OutputStream os = new FileOutputStream(HTML_TO_PDF);
            response.setHeader("Content-Disposition", "inline; filename\"print=file=file-print.dpf\"");
            //OutputStream outputStream = response.getOutputStream();

            renderer.createPDF(os);
            os.close();
            //outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        faces.responseComplete();
        System.out.println("Done 26!!");
    }   
}
