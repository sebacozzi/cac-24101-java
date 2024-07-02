
package ar.com.codo24101.main;

import ar.com.codo24101.dao.DirectorJDBCMysqlImpl;
import ar.com.codo24101.dao.MovieJDBCMysqlImpl;
import ar.com.codo24101.dto.MovieDTO;
import ar.com.codo24101.service.MovieService;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//            MovieJDBCMysqlImpl mj = new MovieJDBCMysqlImpl();
//            
//            
//        //System.out.println(mj.getByID(28l));
//
//        // mj.delete(28l);
//        //mj.create(new MovieDTO("Pelicula de Prueba 29", "Descripcion de Prueba 29", "Genero 29", 44504.1f, 2024l, 2l, 4l));
//        //mj.update(new MovieDTO(29l, "Pelicula de Prueba 29", "Descripcion de Prueba 29", "Genero 29", 4.1f, 2024l, 2l, 4l));
////        System.out.println("");
//        System.out.println(mj.getByID(30l));
////        ArrayList<Movie> al = mj.getLista();
////        
////        for (Movie movie : al) {
////            System.out.println(movie);
////        }
//        System.out.println("Sin valor en columnas: (Linea 32)");
//        mj.getByVal(".", "hh", "AND");
//        System.out.println("");
//        System.out.println("Con una sola columna:(Linea 35)");
//
//        System.out.println("");
//        ArrayList<MovieDTO> al = mj.getByVal("nombre", "hh", "OR");
//
//        if (al.isEmpty()) {
//            System.out.println("No hubo resultados para la busqueda");
//        } else {
//            for (MovieDTO movie : al) {
//                System.out.println(movie);
//            }
//        }
//        System.out.println("");
//
//        System.out.println("Con una columna mal:(Linea 49) ");
//        mj.getByVal("directores", "hh", "OR");
//        System.out.println("");
//        System.out.println("Con 2 columnas:(Linea 52)");
//        System.out.println("");
//        al = mj.getByVal("nombre,director", "el,2", "OR");
//
//        if (al.isEmpty()) {
//            System.out.println("No hubo resultados para la busqueda...");
//        } else {
//            for (MovieDTO movie : al) {
//                System.out.println(movie);
//            }
//        }
//        System.out.println("");
//
//        System.out.println("");
//        System.out.println("Con 2 columnas mal: (Linea 66)");
//        mj.getByVal("nombres,directores", "hh,ff", "OR");
//        System.out.println("");
//        System.out.println("Con 1 columna bien y 1 mal:(Linea 69)");
//        mj.getByVal("nombres,director", "hh,ghj", "OR");
//        System.out.println("");
//        System.out.println("Con 1 columna bien y 1 mal:(Linea 72)");
//        mj.getByVal("nombres,director,", "hh,gf", "OR");
//        System.out.println("");
//        System.out.println("Con 1 columna bien y busqeda varias palabras:(Linea 75)");
//        al = mj.getByVal("nombre", "fi el");
//
//        if (al.isEmpty()) {
//            System.out.println("No hubo resultados para la busqueda...");
//        } else {
//            for (MovieDTO movie : al) {
//                System.out.println(movie);
//            }
//        }
//
//        System.out.println("");
//        MovieDTO m = new MovieDTO();
//        m.setId_movie(27l);
//        m.setNombre("El se�or de los anillos");
//        System.out.println("-".repeat(20));
//        
//        System.out.println("ID: "+m.getId_movie());
//        System.out.println("Nombre: "+m.getNombre());
//        System.out.println("Descripcion: "+m.getDescripcion());
//        System.out.println("Genero: "+m.getGenero());
//        System.out.println("A�o: "+m.getAnio().toString());
//        System.out.println("Calificaci�n: "+m.getCalificacion());
//        System.out.println("Estrellas: "+m.getEstrellas());
//        System.out.println("ID Director: "+m.getDirector());
//        
//      
//        //mj.update(new MovieDTO(29l, "Pelicula de Prueba 29", "Descripcion de Prueba 29", "Genero 29", 4.1f, 2024l, 2l, 4l));
    
     MovieService ms = new MovieService();
        try {
            
        ArrayList<MovieDTO> m = ms.obtenerPeliculas();
    
            System.out.println(m);
        } catch (Exception e) {
            System.out.println("Falla: "+e);
        }
    

    }
}
