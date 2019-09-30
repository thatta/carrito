/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.beans;

import com.mycompany.dto.DTOProducto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author USER
 */
@Named
@SessionScoped
public class BeanApoyo implements Serializable{

    private List<DTOProducto> listaCarrito;
    /**
     * Creates a new instance of BeanApoyo
     */
    public BeanApoyo() {
        listaCarrito = new ArrayList();
    }

    public List<DTOProducto> getListaCarrito() {
        return listaCarrito;
    }

    public void setListaCarrito(List<DTOProducto> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }
    
}
