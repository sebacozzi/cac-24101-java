/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codo24101.dto;

/**
 *
 * @author Asterisco Sublimados
 */
public final class MovieCRUDDTO  {
    private String metodo;
    private MovieDTO objeto;

    public MovieCRUDDTO() {
    }

    public MovieCRUDDTO(String metodo, MovieDTO objeto) {
        this.metodo = metodo;
        this.objeto = objeto;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public MovieDTO getObjeto() {
        return objeto;
    }

    public void setObjeto(MovieDTO objeto) {
        this.objeto = objeto;
    }

    @Override
    public String toString() {
        return "PostDTO{" + "metodo=" + metodo + ", objeto=" + objeto + '}';
    }
    
    
}
