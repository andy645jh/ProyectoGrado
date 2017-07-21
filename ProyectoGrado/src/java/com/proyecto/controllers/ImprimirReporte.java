/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author User
 */
@WebServlet(name = "ImprimirReporte", urlPatterns = {"/ImprimirReporte"})
public class ImprimirReporte extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ImprimirReporte</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ImprimirReporte at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        HashMap args = new HashMap();
        String ruta = FacesContext.getCurrentInstance().getExternalContext().
                    getRealPath("/reportes/");
        
        String logoEtiqueta = FacesContext.getCurrentInstance().getExternalContext().
                    getRealPath("/resources/img/logo_p4.png");
        
        args.put("RUTA_LOGO", logoEtiqueta);
            args.put("SUBREPORT_DIR", ruta);

        try {
            this.exportPdf(response,  args);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ImprimirReporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ImprimirReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void exportPdf(HttpServletResponse response,  HashMap param) throws IOException, ClassNotFoundException, SQLException {
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

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
