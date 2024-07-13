/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codo24101.dto;

/**
 *
 * @author Asterisco Sublimados
 */
public class UsuarioCRUDDTO {
    private String metodo;
    private UsuarioDTO objeto;

    public UsuarioCRUDDTO() {
    }

    public UsuarioCRUDDTO(String metodo, UsuarioDTO objeto) {
        this.metodo = metodo;
        this.objeto = objeto;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public UsuarioDTO getObjeto() {
        return objeto;
    }

    public void setObjeto(UsuarioDTO objeto) {
        this.objeto = objeto;
    }

    @Override
    public String toString() {
        return "UsuarioCRUDDTO{" + "metodo=" + metodo + ", objeto=" + objeto + '}';
    }
    
}
