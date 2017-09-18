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
import javax.faces.context.FacesContext;

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

    public static String getPathImages() {
        String destination = "C:/webapp/img/";
        String server = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
        if (!server.contains("localhost")) {
            destination = "/home/webapp/img/";
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

    public static String getPathReports() {
        String destination = "C:/webapp/pdf/";

        String server = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
        if (!server.contains("localhost")) {
            destination = "/home/webapp/pdf/";
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
}
