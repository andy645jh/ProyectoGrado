/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.utilities;

import javax.faces.context.FacesContext;

/**
 *
 * @author Elkin
 */
public class SessionUtils
{

    public static void clear()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
    }
    
    public static void add(String key, Object value) 
    {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);        
    }

    public static Object get(String key) 
    {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
    }
}
