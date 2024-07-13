/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codo24101.dto;

/**
 *
 * @author Asterisco Sublimados
 */
public class DirectorCRUDDTO {
    private String metodo;
    private DirectorDTO objeto;

    public DirectorCRUDDTO() {
    }

    public DirectorCRUDDTO(String metodo, DirectorDTO objeto) {
        this.metodo = metodo;
        this.objeto = objeto;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public DirectorDTO getObjeto() {
        return objeto;
    }

    public void setObjeto(DirectorDTO objeto) {
        this.objeto = objeto;
    }

    @Override
    public String toString() {
        return "DirectorCRUDDTO{" + "metodo=" + metodo + ", objeto=" + objeto + '}';
    }
    
}
