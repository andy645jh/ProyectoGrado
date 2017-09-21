/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.ctrl;

import com.proyecto.persistences.Docentes;
import com.proyecto.utilities.SessionUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author elkin
 */
@ManagedBean(name = "fileUploadController")
//@RequestScoped
public class FileUploadController {

    private UploadedFile _file = null;
    private String _url = "";
    private String _filename;
    /**
     * Creates a new instance of FileUploadController
     */
    public FileUploadController() {
    }

    public boolean exist() {
        return !_url.isEmpty();
    }
    
    public void upload() {
        try {

            copyFile(_file.getFileName(), _file.getInputstream());

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public void copyFile(String fileName, InputStream in) {

        try {
            Docentes doc = (Docentes) SessionUtils.get("docente");
            _url = SessionUtils.getPathImages(doc.getCedula()) +fileName;            
            OutputStream out = new FileOutputStream(new File(_url));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();
            System.out.println("Archivo creado en: " + (_url));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public UploadedFile getFile() {
        return _file;
    }

    public void setFile(UploadedFile _file) {
        this._file = _file;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String _url) {
        this._url = _url;
    }

    public String getFilename() {
        return _filename;
    }

    public void setFilename(String _filename) {
        this._filename = _filename;
    }
}
