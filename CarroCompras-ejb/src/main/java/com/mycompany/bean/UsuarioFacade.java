/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.dto.Criptado;
import com.mycompany.interfaces.UsuarioFacadeLocal;
import com.mycompany.entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "persistencia")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public int login(String username, String password) {
        int valor =0;
        try{
            Query validar=em.createQuery("FROM Usuario u WHERE u.usuario = ?1 and u.contrasena = ?2");
            validar.setParameter(1, username);
            validar.setParameter(2, Criptado.Encriptar(password));
            List<Usuario> lista= validar.getResultList();
            if(!lista.isEmpty()){
                valor=1;
            }else{
                valor=2;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return valor;
    }
    
}
