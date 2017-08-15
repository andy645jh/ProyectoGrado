/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.ctrl;

/**
 *
 * @author elkin
 */
public class Persona {

    private String _nombre;
    private int _edad;

    public Persona() {
    }

    public Persona(String nombre, int edad) {
        _nombre = nombre;
        _edad = edad;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public int getEdad() {
        return _edad;
    }

    public void setEdad(int _edad) {
        this._edad = _edad;
    }    
}
