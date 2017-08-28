package com.test.ctrl;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 * A sample service to run a Jasper Report and export it to a PDF file.
 */
public class DynamicColumnReportService {

    public void runReport(List<String> columnHeaders, List<List<String>> rows) throws JRException {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/DynamicColumns.jrxml");
        System.out.println("Loading the .jrxml");
        InputStream is = getClass().getResourceAsStream("../../../DynamicColumns.jrxml");
        JasperDesign jasperReportDesign = JRXmlLoader.load(path);

        System.out.println("Adding the dynamic columns");
        DynamicReportBuilder reportBuilder = new DynamicReportBuilder(jasperReportDesign, columnHeaders.size());
        //reportBuilder.addDynamicColumns();
        reportBuilder.initConfig();

        System.out.println("Compiling the report");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperReportDesign);
        List<Persona> listaPersonas = new ArrayList<>();
        listaPersonas.add(new Persona("Elkin", 10));
        listaPersonas.add(new Persona("Giovanny", 20));
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("REPORT_TITLE", "Sample Dynamic Columns Report");
        params.put("header", "Mi columna");
        params.put("col", "Mi columna 3");
        params.put("personas", listaPersonas);
        //DynamicColumnDataSource pdfDataSource = new DynamicColumnDataSource(columnHeaders, rows);
        System.out.println("Filling the report");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

        System.out.println("Exporting the report to pdf");
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\informes_jasper\\DynamicColumns.pdf");
    }

}
