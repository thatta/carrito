/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.dto.DTOProducto;
import com.mycompany.interfaces.ProductoFacadeLocal;
import com.mycompany.entity.Producto;
import com.mycompany.entity.Venta;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;

/**
 *
 * @author USER
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> implements ProductoFacadeLocal {
    @PersistenceContext(unitName = "persistencia")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    @Override
    public List<DTOProducto> obtenerProductos() {

        ModelMapper modelMapper = new ModelMapper();
        List<DTOProducto> listaProductos = new ArrayList();
        List<Producto> listaEntProductos = findAll();
        if (!listaEntProductos.isEmpty()) {
            for (Producto pro : listaEntProductos) {
                listaProductos.add(modelMapper.map(pro, DTOProducto.class));
            }
        }
        return listaProductos;
    }
    
    @Override
    public boolean obtenerStockProducto(DTOProducto producto) {

        Producto eProducto = new Producto();
        eProducto = find(producto.getId());
        if (eProducto.getCantidad() < producto.getCantidadSeleccionada()) {
            return false;
        } else {
            eProducto.setCantidad(eProducto.getCantidad() - producto.getCantidadSeleccionada());
            edit(eProducto);
            return true;
        }
    }
    
    @Override
    public void eliminarDelCarrito(DTOProducto producto) {
        Producto eProducto = find(producto.getId());
        eProducto.setCantidad(eProducto.getCantidad() + producto.getCantidadSeleccionada());
        edit(eProducto);
    }
    
    @Override
    public void agregarVenta(List<DTOProducto> listaProductos, int idUsuario){
        for (DTOProducto dtoProducto : listaProductos) {
            Producto eProducto = find(dtoProducto.getId());
            Venta venta = new Venta(idUsuario, dtoProducto.getCantidadSeleccionada(), new Date(), eProducto);
            eProducto.getListaVentas().add(venta);
            edit(eProducto);
        }
    }
}
