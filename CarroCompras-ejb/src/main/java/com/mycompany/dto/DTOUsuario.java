/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dto;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class DTOUsuario implements Serializable{
    private int id;
    private String nombre;
    private String usuario;
    private String contresena;

    public DTOUsuario() {
    }

    public DTOUsuario(String nombre, String usuario, String contresena) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contresena = contresena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContresena() {
        return contresena;
    }

    public void setContresena(String contresena) {
        this.contresena = contresena;
    }
    
    
    
}

