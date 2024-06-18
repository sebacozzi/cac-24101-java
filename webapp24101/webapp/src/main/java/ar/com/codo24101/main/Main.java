/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codo24101.main;

import ar.com.codo24101.dao.MovieJDBCMysqlImpl;
import ar.com.codo24101.dto.MovieDTO;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Main {
    public static void main(String[] args) {
            MovieJDBCMysqlImpl mj = new MovieJDBCMysqlImpl();
            
            
        //System.out.println(mj.getByID(28l));

        // mj.delete(28l);
        //mj.create(new MovieDTO("Pelicula de Prueba 29", "Descripcion de Prueba 29", "Genero 29", 44504.1f, 2024l, 2l, 4l));
        //mj.update(new MovieDTO(29l, "Pelicula de Prueba 29", "Descripcion de Prueba 29", "Genero 29", 4.1f, 2024l, 2l, 4l));
//        System.out.println("");
        System.out.println(mj.getByID(30l));
//        ArrayList<Movie> al = mj.getLista();
//        
//        for (Movie movie : al) {
//            System.out.println(movie);
//        }
        System.out.println("Sin valor en columnas:");
        mj.getByVal(".", "hh", "AND");
        System.out.println("");
        System.out.println("Con una sola columna:");

        System.out.println("");
        ArrayList<MovieDTO> al = mj.getByVal("nombre", "hh", "OR");

        if (al.isEmpty()) {
            System.out.println("No hubo resultados para la busqueda");
        } else {
            for (MovieDTO movie : al) {
                System.out.println(movie);
            }
        }
        System.out.println("");

        System.out.println("Con una columna mal: ");
        mj.getByVal("directores", "hh", "OR");
        System.out.println("");
        System.out.println("Con 2 columnas:");
        System.out.println("");
        al = mj.getByVal("nombre,director", "el,2", "OR");

        if (al.isEmpty()) {
            System.out.println("No hubo resultados para la busqueda...");
        } else {
            for (MovieDTO movie : al) {
                System.out.println(movie);
            }
        }
        System.out.println("");

        System.out.println("");
        System.out.println("Con 2 columnas mal:");
        mj.getByVal("nombres,directores", "hh,ff", "OR");
        System.out.println("");
        System.out.println("Con 1 columna bien y 1 mal:");
        mj.getByVal("nombres,director", "hh,ghj", "OR");
        System.out.println("");
        System.out.println("Con 1 columna bien y 1 mal:");
        mj.getByVal("nombres,director,", "hh,gf", "OR");
        System.out.println("");
        System.out.println("Con 1 columna bien y busqeda varias palabras:");
        al = mj.getByVal("nombre", "fi el");

        if (al.isEmpty()) {
            System.out.println("No hubo resultados para la busqueda...");
        } else {
            for (MovieDTO movie : al) {
                System.out.println(movie);
            }
        }

        System.out.println("");
    }
}
