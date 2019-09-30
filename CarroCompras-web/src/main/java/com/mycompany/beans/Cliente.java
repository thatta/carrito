/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.beans;

import com.mycompany.dto.DTOProducto;
import com.mycompany.dto.DTOUsuario;
import com.mycompany.interfaces.ProductoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author USER
 */
@Named
@ViewScoped
public class Cliente implements Serializable {
    /**
     * Metodo para verificar y validar la sesion del rol cliente 
     */
    @EJB
    ProductoFacadeLocal p;
    
    @Inject
    BeanApoyo sesion;
    
    private List<DTOProducto> listaProductos;
    private DTOProducto productoSeleccionado;

    public Cliente() {
        listaProductos= new ArrayList();
    }
    
    @PostConstruct
    public void _init(){
        listaProductos = p.obtenerProductos();
    }
    
    public void verificarSesion(){
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            DTOUsuario persona = (DTOUsuario) fc.getExternalContext().getSessionMap().get("ingreso");
            
            if(persona == null){
                 fc.getExternalContext().redirect("Inicio.xhtml");
            } 
        } catch (Exception e) {
        }
    
    }
    /**
     * Metodo para cerrar la sesion del rol cliente
     */
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public void obtenerProductos(){
        listaProductos = p.obtenerProductos();
    }
    
    public void agregarCarrito(){
        //DTOProducto producto = new DTOProducto(productoSeleccionado.getId(), productoSeleccionado.getCantSeleccionada());
        DTOProducto producto = productoSeleccionado;
        producto.setTotal(productoSeleccionado.getPrecio()*productoSeleccionado.getCantidadSeleccionada());
        System.out.println("Total "+producto.getTotal());
        boolean respuesta = p.obtenerStockProducto(producto);
        if(!respuesta){
            FacesContext faces = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                        "No puede superar la cantidad en stock");
                faces.addMessage(null, msg);
        }else{
            sesion.getListaCarrito().add(producto);
            obtenerProductos();
            FacesContext faces = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado",
                        "Producto agregado al Carrito.");
                faces.addMessage(null, msg);
        }
    }

    public List<DTOProducto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<DTOProducto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public DTOProducto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(DTOProducto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }
    
}
