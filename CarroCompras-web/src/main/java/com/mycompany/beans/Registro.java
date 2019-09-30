/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.beans;



import com.mycompany.dto.Criptado;
import com.mycompany.dto.DTOUsuario;
import com.mycompany.entity.Usuario;
import com.mycompany.interfaces.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author USER
 */
@Named
@ViewScoped
public class Registro implements Serializable {
    
    private String nombre;
    private String usuario;
    private String contrasena;
    
    @EJB
    private UsuarioFacadeLocal usuarioFacade;


    public Registro() {
    }

    public Registro(String nombre, String usuario, String contrasena) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    
    public String registrar() throws IOException{
        DTOUsuario dtousuario= new DTOUsuario();
        Usuario user = new Usuario();
        dtousuario.setNombre(nombre);
        dtousuario.setUsuario(usuario);
        dtousuario.setContresena(Criptado.Encriptar(contrasena));
        
        user.setNombre(dtousuario.getNombre());
        user.setUsuario(dtousuario.getUsuario());
        user.setContrasena(dtousuario.getContresena());
        
        usuarioFacade.create(user);
        //FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
        return "Login";
    }
    
    
}
