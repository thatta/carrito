/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.beans;


import com.mycompany.interfaces.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author USER
 */
@Named
@ViewScoped
public class Login implements Serializable {
    
    private String usuario;
    private String contrasena;
    
    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    public Login() {
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
    
    public String validar() throws IOException{
        int persona = usuarioFacade.login(usuario, contrasena);
        
        if(persona==1){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ingreso", usuario);
            //FacesContext.getCurrentInstance().getExternalContext().redirect("Clientes.xhtml");
        }else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "DATOS ERRONEOS","INGRESE LOS DATOS CORRECTOS");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return "Clientes";
    }
    
    
}
