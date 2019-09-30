/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.*;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "producto")
public class Producto implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column
    private int cantidad;
    @Column
    private double valor;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Venta> listaVentas;

    public Producto() {
    }

    public Producto(String nombre, int cantidad, double valor, List<Venta> listaVentas) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.valor = valor;
        this.listaVentas = listaVentas;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return valor;
    }

    public void setPrecio(double precio) {
        this.valor = valor;
    }

    public List<Venta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }

    
}

