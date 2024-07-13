/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ar.com.codo24101.Interfaces;

/**
 *
 * @author Usuario
 */
public interface Validaciones {
    String URI_LOGIN ="/webapp/private/login/";
    
    default boolean puedeGet(String metodo){
        if(metodo == null){
            return false;
        }
        return metodo.contains("GET");
    };
    
    default boolean puedePost(String metodo){
        if(metodo == null){
            return false;
        }
      return metodo.contains("POST");  
    };
    default boolean puedeDelete(String metodo){
        if(metodo == null){
            return false;
        }
        return metodo.contains("DELETE");
    }
    default boolean puedePut(String metodo){
        if(metodo == null){
            return false;
        }
        return metodo.contains("PUT");
    }
    default boolean esUriLogin(String uri){
        
        if(uri == null){
            return false;
        }
         return uri.equals(URI_LOGIN);
     }
}
