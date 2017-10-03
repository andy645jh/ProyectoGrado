package com.proyecto.controllers;

import com.proyecto.persistences.Docentes;
import com.proyecto.utilities.SessionUtils;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class AuditorController implements Serializable{
           
    public AuditorController() {
        
    }  
   
    public /*List<String>*/ void ListaDocs(Docentes doc)
    {
        System.out.println("Listado de Docs");
        int cedula = doc.getCedula();
        File f = new File(SessionUtils.getPathReports(cedula));
        File[] files = f.listFiles();
        List<String> docs = new ArrayList();
        for (File fTemp : files) {
            //if(fTemp.getName())if(fTemp.getName())
            System.out.println("Archivo: "+fTemp.getName());
            docs.add(fTemp.getName());
        }        
        //return docs;    
    }
}
