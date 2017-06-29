package com.proyecto.controllers;


import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ramki
 */
@javax.faces.bean.ManagedBean(name = "cargaArchivo")//para mover el archivo por el ambito de sesion, una especie de apuntador durante toda la vida de la sesion
@SessionScoped
public class cargaArchivo implements Serializable{

    private Part file1;
    private Part file2;
    //...hasta n
    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public Part getFile2() {
        return file2;
    }

    public void setFile2(Part file2) {
        this.file2 = file2;
    }

    public String upload() throws IOException {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/docentes/temp");
        file1.write(path+getFilename(file1));
        file2.write(path+getFilename(file2));
        return "success"; //el nombre de la pagina .xhtml donde retorna
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
