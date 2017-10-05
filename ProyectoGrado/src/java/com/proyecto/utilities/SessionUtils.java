/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Elkin
 */
public class SessionUtils {

    public static void clear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
    }

    public static void add(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }

    public static Object get(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
    }

    public static void remove(String key) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
    }
    
    public static String getPathImages(int id) {
        String destination = "C:/webapp/"+id+"/";
        String server = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
        if (!server.contains("localhost")) {
            destination = "/home/webapp/"+id+"/";
        }
        try {
            Path path = Paths.get(destination);
            if (!Files.exists(path)) {
                path = Files.createDirectories(path);
                //destination = path.toString();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return destination;
    }

    public static String getPathReports(int id) {
        
        String server = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
        return generatePath(id, server);       
    }
    
    public static String getPathReports(int id, HttpServletRequest request) {
        
        String server = request.getServerName();
        return generatePath(id, server);
    }
    
    private static String generatePath(int id, String server)
    {
        String destination = "C:/webapp/"+id+"/";
        if (!server.contains("localhost")) {
            destination = "/home/webapp/"+id+"/";
        }

        try {
            Path path = Paths.get(destination);
            if (!Files.exists(path)) {
                path = Files.createDirectories(path);
                //destination = path.toString();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return destination;
    }
    
    public static String getYear()
    {
        Calendar cal = Calendar.getInstance();
        return Integer.toString(cal.get(Calendar.YEAR));
    }
    
    public static String getSemestre()
    {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH)<=5 ? "1" : "2";
    }
}
