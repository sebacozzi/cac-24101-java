/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ar.com.codo24101.Interfaces;

import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author Asterisco Sublimados
 */
public interface GenericResults {
    
    
    default String nullURL() {
        return """
                    <h1>Lista de apis</h1><br>
                    <a href="./peliculas/all">Peliculas</a><br>
                     
                    <a href="./directores/all">Directores</a><br>
                     
                    <a href="./usuarios/all">Usuarios</a>
               
                    <p>resultado login :</p>
                    <p>        {"nombre":"%s",</p>
                             "apellido":"%s",
                             "roles":"%s",
                             "token": "%s"}
                     """;
    };
    
    default String formatedResult(int count, String result) {

        return """
            {"count":%d,"result":%s}""".formatted(count, result);
    };
    
    default boolean esNumerico(String numero) {
        try {
            Long.valueOf(numero);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
     default int validaParametros(Enumeration<String> parametros) {

        List<String> validos = List.of("columnas", "valores", "caso");
        int ret = 0;
        int check = 0;
        while (parametros.hasMoreElements()) {
            String pan = parametros.nextElement();
            if (validos.contains(pan)) {
                ret++;
            }
            check++;
        }
        return ret == check ? ret : -1;
    }
}
