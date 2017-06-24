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
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class EditorUsuarioFotoBean implements Serializable {

   private Docentes usuario;
private CroppedImage croppeFoto;
private String imageTemp;
@EJB
    private DocentesFacade _ejbFacade;
 
public void actionFoto(){
    this.croppeFoto = null;
    this.imageTemp = null;
}
 
public void actionGuardarFoto(){
    String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/docentes");
    String archivo = path + File.separator + usuario.getCedula() + ".jpg";
 
    try {
 
        if(croppeFoto!=null){
            System.out.println("ENTRO AL croppeFoto "+croppeFoto);
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(archivo));
            imageOutput.write(croppeFoto.getBytes(), 0, croppeFoto.getBytes().length);
            System.out.println("ENTRO AL croppeFoto 2 "+imageOutput.toString());
            imageOutput.close();
        }
        else {
            System.out.println("ELSE");
            OutputStream outStream = new FileOutputStream(new File(archivo));
            InputStream inputStream = new FileInputStream(path+ "/temp/" + usuario.getFoto());
            byte[] buffer = new byte[6124];
            int bulk;
            while (true) {
                System.out.println("WHILE");
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    System.out.println("BULK < 0");
                    break;
                }
                outStream.write(buffer, 0, bulk);
                outStream.flush();
            }
            outStream.close();
            inputStream.close();
        }
 
        actionFoto();
 
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
 
public void uploadFile(FileUploadEvent event) {
    
    usuario=_ejbFacade.buscar(73167775);
    try {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/docentes/temp");
        String archivo = path + File.separator + event.getFile().getFileName();
 
        FileOutputStream fileOutputStream = new FileOutputStream(archivo);
        byte[] buffer = new byte[6124];
        int bulk;
        InputStream inputStream = event.getFile().getInputstream();
        while (true) {
        bulk = inputStream.read(buffer);
        if (bulk < 0) {
          break;
        }
        fileOutputStream.write(buffer, 0, bulk);
        fileOutputStream.flush();
    }
    fileOutputStream.close();
    inputStream.close();
        System.out.println("EVENTO "+event.getFile().getFileName());
        System.out.println("DOCENTE "+usuario.getNombres());
    usuario.setFoto(event.getFile().getFileName());
    this.setImageTemp(event.getFile().getFileName());
 
    } catch (IOException e) {
        e.printStackTrace();
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al subir el archivo"));
    }
}
 
//setter and getter

    public Docentes getUsuario() {
        return usuario;
    }

    public void setUsuario(Docentes usuario) {
        this.usuario = usuario;
    }

    public CroppedImage getCroppeFoto() {
        return croppeFoto;
    }

    public void setCroppeFoto(CroppedImage croppeFoto) {
        this.croppeFoto = croppeFoto;
    }

    public String getImageTemp() {
        return imageTemp;
    }

    public void setImageTemp(String imageTemp) {
        this.imageTemp = imageTemp;
    }
} 

