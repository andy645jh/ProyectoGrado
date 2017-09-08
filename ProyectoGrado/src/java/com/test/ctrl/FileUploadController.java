/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.ctrl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author elkin
 */
@ManagedBean(name = "fileUploadController")
//@RequestScoped
public class FileUploadController {

    private String destination = "C:\\webapp\\img\\";
    private UploadedFile _file = null;
    private String _url = "";
    private String _filename;
    private String _pathServer;
    /**
     * Creates a new instance of FileUploadController
     */
    public FileUploadController() {
    }

    public boolean exist() {
        return !_url.isEmpty();
    }
    
    public void upload() {

        /*FacesMessage msg = new FacesMessage("Success! ", _file.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        String project = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        int port = FacesContext.getCurrentInstance().getExternalContext().getRequestServerPort();
        _pathServer = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
        System.out.println("Server: "+ (_pathServer+":"+port+"/"+project));*/
        // Do what you want with the file       
        try {

            copyFile(_file.getFileName(), _file.getInputstream());

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public void copyFile(String fileName, InputStream in) {

        try {
            Path path = Paths.get(destination);
            if (!Files.exists(path)) {
                path = Files.createDirectories(path);
            }
            _filename = fileName;
            _url = destination +fileName;
            System.out.println("URL: "+_url);
            OutputStream out = new FileOutputStream(new File(_url));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();
            System.out.println("New file created! : " + (path + fileName));
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
