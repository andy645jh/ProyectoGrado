/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.controllers;

import com.proyecto.facades.ActividadesFacade;
import com.proyecto.facades.HorarioFacade;
import com.proyecto.facades.ProductosFacade;
import com.proyecto.facades.SemanaFacade;
import com.proyecto.persistences.Actividades;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Horario;
import com.proyecto.persistences.Productos;
import com.proyecto.persistences.Semana;
import com.proyecto.utilities.Intervalo;
import com.proyecto.utilities.SessionUtils;
import com.test.ctrl.DynamicColumnReportService;
import com.test.ctrl.Persona;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
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

    @EJB
    private ProductosFacade _productosFacade;

    @EJB
    private HorarioFacade _horarioFacade;

    @PostConstruct
    public void init() {
        _currentDocente = (Docentes) SessionUtils.get("docente");
    }

    public void crearReport() {
        List<String> columnHeaders = Arrays.asList(new String[]{"Col1", "Col2", "Col3", "Col4"});
        List<List<String>> rows = new ArrayList<List<String>>();
        List<String> row1 = Arrays.asList(new String[]{"Data1", "Data2", "Data3", "Data4"});
        List<String> row2 = Arrays.asList(new String[]{"Data5", "Data6", "Data7", "Data8"});
        List<String> row3 = Arrays.asList(new String[]{"Data9", "Data10", "Data11", "Data12"});

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        DynamicColumnReportService service = new DynamicColumnReportService();
        try {
            service.runReport(columnHeaders, rows);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void generateReport() {

        String cadenaConexion = "jdbc:postgresql://localhost:5432/bd_proyecto";
        //Class.forName("org.postgresql.Driver");
        //Connection connection = DriverManager.getConnection(cadenaConexion,"user_java", "123456");

        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/test1.jasper");
        String pdf = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/");
        String subreport = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes\\");

        try {

            System.out.println("SUB_REPORT " + subreport);
            JasperReport jasperReport = JasperCompileManager.compileReport(FacesContext.getCurrentInstance().getExternalContext().
                    getRealPath("/reportes/rdc54.jrxml"));
            System.out.println("Done! 0");
            List<Docentes> listaDocentes = new ArrayList<>();
            listaDocentes.add(_currentDocente);

            List<Actividades> listaActividades = _actividadesFacade.buscarCampo("_coddocente", "73167775");
            List<Productos> listaProductos = _productosFacade.listado();// _productosFacade.buscarCampo("_coddocente", "73167775");
            List<Productos> listaFiltradaProductos = new ArrayList<>();
            List<Intervalo> listaIntervalos = organizarIntervalos();

            for (Productos producto : listaProductos) {
                for (Actividades activ : listaActividades) {
                    if (producto.getCodactividad().equals(activ)) {
                        listaFiltradaProductos.add(producto);
                    }
                }
            }
            System.out.println("Done! 1");
            // Parameters for report
            Map<String, Object> parameters = new HashMap<String, Object>();
            List<Object> objArray = new ArrayList<>();
            objArray.add("hola");
            objArray.add("hola 2");

            parameters.put("SUBREPORT_DIR", subreport);
            parameters.put("array", new JRBeanCollectionDataSource(objArray));
            parameters.put("docentes", new JRBeanCollectionDataSource(listaDocentes));
            parameters.put("intervalos", new JRBeanCollectionDataSource(listaIntervalos));
            parameters.put("productos", new JRBeanCollectionDataSource(listaFiltradaProductos));
            parameters.put("actividades", new JRBeanCollectionDataSource(listaActividades));
            parameters.put("seguimientos", new JRBeanCollectionDataSource(listaActividades));

            System.out.println("URL " + path + " PARAMETROS " + parameters.size());
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            System.out.println("Done! 2");
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\informes_jasper\\test1.pdf");

            System.out.println("Done! 3");
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            e.printStackTrace();
        }
    }

    private List<Intervalo> organizarIntervalos() {
        List<Horario> listHorario = _horarioFacade.listado();
        //_objHorario = listHorario.get(0);   
        Intervalo[] arrayInterval = new Intervalo[HorarioController._intervalos.length];
        System.out.println("TAMAÑO " + listHorario.size());
        for (int i = 0; i < arrayInterval.length; i++) {
            arrayInterval[i] = new Intervalo();
            arrayInterval[i].setInitData(HorarioController._intervalos[i], i);
        }

        for (Horario obj : listHorario) {
            //eventModel.addEvent(new DefaultScheduleEvent(obj.getNombre(), obj.getHorainicio(), obj.getHorafinal(),obj));
            System.out.println("ReportTestControllerElkin.organizarIntervalos -> HOra: " + obj.getHora());
            //cuadrando la lista de horarios        
            arrayInterval[obj.getHora()].setDia(obj);
        }

        //convertir array a lista
        return Arrays.asList(arrayInterval);
        //RequestContext.getCurrentInstance().update(":formHorario:nuevaLista");
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

        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(pdf + "R-DC-26.jasper");
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
