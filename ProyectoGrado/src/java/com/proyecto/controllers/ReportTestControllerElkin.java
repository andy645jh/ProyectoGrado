/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.controllers;

import com.proyecto.facades.ActividadesFacade;
import com.proyecto.facades.SemanaFacade;
import com.proyecto.persistences.Actividades;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Semana;
import com.proyecto.utilities.SessionUtils;
import com.test.ctrl.Persona;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Init;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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
    private String number;
    private JasperPrint jasperPrint;
    private Docentes _currentDocente;
    
    @EJB 
    private SemanaFacade _semanaFacade;
    
    @EJB 
    private ActividadesFacade _actividadesFacade;
    
    @PostConstruct
    public void init()
    {
        _currentDocente = (Docentes) SessionUtils.get("docente");
    }
    
    public void generateReport() {
        try {
//            List<country> countries = getListCountriesDummy();
            JasperReport js;
            JasperPrint jp;

            Map<String, Object> map = new HashMap<String, Object>();
//            
            String ruta = FacesContext.getCurrentInstance().getExternalContext().
                    getRealPath("/reportes\\");

            String logoEtiqueta = FacesContext.getCurrentInstance().getExternalContext().
                    getRealPath("/resources/img/logo_reportes.PNG");

            String cadenaConexion = "jdbc:postgresql://localhost:5432/bd_proyecto";
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(cadenaConexion,"user_java", "123456");

            map.put("LOGO", logoEtiqueta);
            map.put("SUBREPORT_DIR", ruta+"\\");

            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/test1.jasper");
            String pdf = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/");

            try {
                System.out.println("URL " + path + " PARAMETROS " + map.size());
                JasperReport jasperReport = JasperCompileManager.compileReport(FacesContext.getCurrentInstance().getExternalContext().
                 getRealPath("/reportes/rdc54.jrxml"));
               
                List<Actividades> listaActividades = _actividadesFacade.buscarCampo("_coddocente", "73167775");
                
                // Parameters for report
                Map<String, Object> parameters = new HashMap<String, Object>();
                
                parameters.put("docente", _currentDocente);
                parameters.put("actividades", new JRBeanCollectionDataSource(listaActividades));
                
                /*List<Persona> lista = new ArrayList<Persona>();
                lista.add(new Persona("Elkin",20));
                lista.add(new Persona("Giovanny",21));
                lista.add(new Persona("Sergio",18));
                
                List<Semana> listaSemanas = _semanaFacade.listado();
                System.out.println("Semanas: "+ listaSemanas.size());
                parameters.put("semanas", new JRBeanCollectionDataSource(listaSemanas));
                parameters.put("lista", new JRBeanCollectionDataSource(lista));
                parameters.put("mi_nombre", "Giovanny");*/
//              
                //jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,connection);
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,new JREmptyDataSource());
               
                //JasperViewer.viewReport(jasperPrint);
                //JasperViewer viewer = new JasperViewer(jasperPrint, false);
                //viewer.setVisible(true);
                //pdf();
                
                JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\informes_jasper\\test1.pdf");
                // Make sure the output directory exists.
                /*File outDir = new File("C:/jasperoutput");
                outDir.mkdirs();

                // Export to PDF.
                JasperExportManager.exportReportToHtmlFile(jasperPrint,"C:/jasperoutput/StyledTextReport.html");
*/
                System.out.println("Done!");
            } catch (Exception e) {
                System.out.println("Error: " + e.toString());
                e.printStackTrace();
            }

//            System.out.println("RUTA PDF "+pdf);
//            
//            String printFile = JasperFillManager.fillReportToFile(path,
//            map, connection);
//         if (printFile != null) {
//            JasperExportManager.exportReportToPdfFile(printFile,pdf+"prueba.pdf");
//         
//               System.out.println("done printing...!!");
//         }
//            
//            js = JasperCompileManager.compileReport(path);
//            jp = JasperFillManager.fillReport(js, map, connection);
//            
////            byte[] bites = JasperExportManager.exportReportToPdf(jp);
//            
//           byte[] bites = JasperRunManager.runReportToPdf(js, map, connection);
//            PdfReader reader = new PdfReader(bites);
//ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            PdfStamper stamper = new PdfStamper(reader, baos);
//            PdfWriter writer = stamper.getWriter();
//            PdfAction action = new PdfAction(PdfAction.PRINTDIALOG);
//writer.setOpenAction(action);
//stamper.close();
//            outputStream = JasperReportUtil.getOutputStreamFromReport(map, getPathFileJasper());
//            media = JasperReportUtil.getStreamContentFromOutputStream(outputStream, "application/pdf", getNameFilePdf());
//            System.out.println("MEDIA "+media);
        } catch (Exception e) {
//            log.error(e.getMessage(), e);
            System.out.println("ERROR " + e.getMessage());
        }
    }

    public void pdf() throws JRException, IOException {

        HttpServletResponse httpReponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpReponse.addHeader("Content-disposition", "filename=report.pdf");

//        httpReponse.setContentType("application/pdf");
        httpReponse.setContentType("application/force-download");
        ServletOutputStream salida = httpReponse.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, salida);
        FacesContext.getCurrentInstance().responseComplete();

    }

    public StreamedContent fileDownloadView() {
        String pdf = FacesContext.getCurrentInstance().getExternalContext().
                getRealPath("/reportes/");
        
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(pdf+"R-DC-26.jasper");
        StreamedContent file = new DefaultStreamedContent(stream, "reportes/jpg", "descargado.jrprint");
        return file;
    }

    public String getPathFileJasper() {
        return FacesContext.getCurrentInstance().getExternalContext().
                getRealPath("/reportes/R-DC-26.jasper");
    }

    public String getNameFilePdf() {
        return "reporte_dummy.pdf";
    }

    public void downloadFile() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();

            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.reset();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "attachment; filename=" + getNameFilePdf());

            ServletOutputStream output = response.getOutputStream();
            output.write(outputStream.toByteArray());
            output.close();

            facesContext.responseComplete();
        } catch (Exception e) {
//            log.error(e.getMessage(), e);
        }
    }

    public StreamedContent getMedia() {
        return media;
    }

    public void setMedia(StreamedContent media) {
        this.media = media;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
