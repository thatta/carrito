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
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author USER
 */
@Named
@RequestScoped
public class CarritoController implements Serializable {

    @EJB
    ProductoFacadeLocal productoCon;

    @Inject
    BeanApoyo sesion;

    private DTOProducto productoSeleccionado;
    private List<DTOProducto> listaProductos;
    private double total;

    /**
     * Creates a new instance of CarritoController
     */
    public CarritoController() {
        listaProductos = new ArrayList();
    }

    public void eliminarDelCarrito() {
        DTOProducto producto = productoSeleccionado;
        sesion.getListaCarrito().remove(producto);
        productoCon.eliminarDelCarrito(producto);
        FacesContext faces = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completado",
                "Producto eliminado del Carrito.");
        faces.addMessage(null, msg);
    }
    
    public double obtenerTotal(){
        listaProductos = sesion.getListaCarrito();
        total=0;
        for (DTOProducto producto : listaProductos) {
            total = (total+producto.getTotal());
        }
        return total;
    }
    
    public void finalizarCompra(){
        FacesContext fc = FacesContext.getCurrentInstance();
        DTOUsuario persona = (DTOUsuario) fc.getExternalContext().getSessionMap().get("ingreso");
        productoCon.agregarVenta(sesion.getListaCarrito(), persona.getId());
        sesion.getListaCarrito().clear();
        FacesContext faces = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completado",
                        "Productos comprados.");
                faces.addMessage(null, msg);
    }

    public BeanApoyo getSesion() {
        return sesion;
    }

    public void setSesion(BeanApoyo sesion) {
        this.sesion = sesion;
    }

    public DTOProducto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(DTOProducto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
