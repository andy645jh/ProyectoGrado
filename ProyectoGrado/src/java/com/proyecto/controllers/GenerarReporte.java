package com.proyecto.controllers;

import com.proyecto.facades.ActividadMisionalFacade;
import com.proyecto.persistences.ActividadMisional;
import com.proyecto.persistences.Convenciones;
import com.proyecto.utilities.Formulario;
import com.proyecto.utilities.Mensajes;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.xml.registry.UnexpectedObjectException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.primefaces.context.RequestContext;

/**
 *
 * @author User
 */
@ManagedBean
@SessionScoped
public class GenerarReporte implements Serializable {

    public GenerarReporte() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        
        HashMap args = new HashMap();
        String ruta = FacesContext.getCurrentInstance().getExternalContext().
                    getRealPath("/reportes/");
        
        String logoEtiqueta = FacesContext.getCurrentInstance().getExternalContext().
                    getRealPath("/resources/img/logo_p4.png");
        
        args.put("RUTA_LOGO", logoEtiqueta);
            args.put("SUBREPORT_DIR", ruta);

            this.exportPdf(response,  args);
       

    }

    public void exportPdf(HttpServletResponse response,  HashMap param) throws IOException, ClassNotFoundException, SQLException {
        response.setContentType("application/pdf");
        JasperReport js;
        JasperPrint jp;
        
        String cadenaConexion = "jdbc:postgresql://localhost:5432/bd_proyecto";
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(cadenaConexion,
                    "user_java", "123456");
        
//        JRExporter exporter = null;
        OutputStream op = response.getOutputStream();
        try {
            String path = FacesContext.getCurrentInstance().getExternalContext().
                    getRealPath("/reportes/R-DC-54.jasper");
            js = JasperCompileManager.compileReport(path);
            jp = JasperFillManager.fillReport(js, param, connection);
            byte[] bites = JasperExportManager.exportReportToPdf(jp);
            response.setHeader("Content-disposition", "attachment; filename=Informe.pdf");
            response.setContentLength(bites.length);
            op.write(bites);
            op.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//    public void generarReporte() throws UnexpectedObjectException {
//        try {
//            String cadenaConexion = "jdbc:postgresql://localhost:5432/bd_proyecto";
//            Class.forName("org.postgresql.Driver");
//            Connection connection = DriverManager.getConnection(cadenaConexion,
//                    "user_java", "123456");
//            Map args = new HashMap();
//            String logoEtiqueta = FacesContext.getCurrentInstance().getExternalContext().
//                    getRealPath("/resources/img/logo_p4.png");
//
//            String ruta = FacesContext.getCurrentInstance().getExternalContext().
//                    getRealPath("/reportes/");
//
//            args.put("RUTA_LOGO", logoEtiqueta);
//            args.put("SUBREPORT_DIR", ruta);
//            String path = FacesContext.getCurrentInstance().getExternalContext().
//                    getRealPath("/reportes/R-DC-54.jasper");
//            JasperPrint reporte = JasperFillManager.fillReport(path, args, connection);
//            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().
//                    getExternalContext().getResponse();
//            String archivo = "RDC-54.pdf";
//            httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + archivo);
//            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
//            JasperExportManager.exportReportToPdfStream(reporte, servletOutputStream);
//            FacesContext.getCurrentInstance().responseComplete();
//        } catch (Exception e) {
//            throw new UnexpectedObjectException(e.getMessage(), e);
//        }
//    }
}