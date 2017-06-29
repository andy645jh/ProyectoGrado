/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.ctrl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author elkin
 */
@ManagedBean
@RequestScoped
public class PhotoCtrl {

    private UploadedFile _file;
    private String _filename = "my_image";

    public PhotoCtrl() {
    }

    public void upload() throws IOException {

        if (_file != null) {
            Path path = Paths.get("my_folder/my_imgs");
            System.out.println("Guardado 0");
            if (!Files.exists(path)) {
                path = Files.createDirectories(path);
            }
            File uploads = new File(path.toString());
            File file = File.createTempFile("somefilename-", ".ext", uploads);

            try (InputStream input = _file.getInputstream()) {
                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            
            /*try {

                String extension = "png";
                Path path = Paths.get("my_folder/my_imgs");
                System.out.println("Guardado 0");
                if (!Files.exists(path)) {
                    path = Files.createDirectories(path);
                }

                System.out.println("Guardado 1");
                Path filePath = Files.createTempFile(path, "img" + "-", "." + extension);
                System.out.println("Guardado 2");

                InputStream input = _file.getInputstream();
                Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Guardado 3");
                FacesMessage message = new FacesMessage("Succesful", _file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } catch (Exception e) {
                FacesMessage message = new FacesMessage("Error: " + e.toString());
                FacesContext.getCurrentInstance().addMessage(null, message);
            }*/

        } else {
            FacesMessage message = new FacesMessage("No hay nada o.O", _file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public UploadedFile getFile() {
        return _file;
    }

    public void setFile(UploadedFile _file) {
        this._file = _file;
    }

}
