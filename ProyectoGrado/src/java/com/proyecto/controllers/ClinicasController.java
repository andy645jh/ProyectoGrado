package com.proyecto.controllers;

import com.proyecto.facades.CoordinacionFacade;
import com.proyecto.utilities.Formulario;
import com.proyecto.utilities.Mensajes;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.facades.PermisosFacade;
import com.proyecto.persistences.Coordinacion;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Permisos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
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
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class ClinicasController implements Serializable {
    
    private String imagenURL ="/resources/fotos/clinicas/fotos.png";
    private String imagen="fotos.png";

    /*
     devuelve el path de la aplicacion
     */
    public static String getPath() {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
                    .getExternalContext().getContext();
            return ctx.getRealPath("/");


        } catch (Exception e) {

            System.out.println("error "+e.getMessage());
//            addErrorMessage("getPath() " + e.getLocalizedMessage());
        }
        return null;


    }
    
    /*
     devuelve un hashmap con la ruta de fotos clinicas y el url para las imagenes
     */
    public static HashMap<String, String> getMapPathFotosClinica() {
        try {
            HashMap<String, String> map = new HashMap<String, String>();
           
            String path = getPath() + "resources/fotos/clinicas/";
            map.put("path", path);
            map.put("url", "/resources/fotos/clinicas/");
            return map;
        } catch (Exception e) {
            System.out.println("error "+e.getMessage());

//            addECrrorMessage(" getMapPathFotosClinica() " + e.getLocalizedMessage());
        }
        return null;


    }
    
     /*
     devuelve un hashmap con la ruta de fotos clinicas y el url para las imagenes
     */
    public static String getPathFotosClinica() {
        try {             
        
            String path = getPath() + "resources/fotos/clinicas/";
        return path;
        } catch (Exception e) {
            System.out.println("error "+e.getMessage());

//            addErrorMessage("getPathFotosClinica() " + e.getLocalizedMessage());
        }
        return null;


    }
    
      /*
     copia un archivo generalmente cuando se usa el fileupload
     fileName: nombre del archivo a copiar
     in: Es el InputStream
     destination: ruta donde se guardara el archivo
  
     */
    public static Boolean copyFile(String fileName, InputStream in, String destination) {
        try {


            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination + fileName));


            int read = 0;
            byte[] bytes = new byte[1024];


            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }


            in.close();
            out.flush();
            out.close();


            return true;
        } catch (IOException e) {
            System.out.println("error "+e.getMessage());
//            JSFUtil.addErrorMessage("copyFile() " + e.getLocalizedMessage());
        }
        return false;
    }


     public void handleFileUpload(FileUploadEvent event) {
        try {


            UploadedFile file = event.getFile();
//application code
            String destination;


            HashMap<String, String> map = getMapPathFotosClinica();


            destination = map.get("path");
            if (destination == null) {
                System.out.println("error destination ");
//                JSFUtil.addErrorMessage(rf.getMensajeArb("warning.noseobtuvopath"));
            } else {
                imagenURL = map.get("url") + file.getFileName();
                imagen = file.getFileName();
                if (copyFile(file.getFileName(), file.getInputstream(), destination)) {                  
                } 
            }


        } catch (Exception e) {
            System.out.println("error "+e.getMessage());
//            JSFUtil.addErrorMessage("handleFileUpload()" + e.getLocalizedMessage());
        }


    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
     
     

} 

