package ar.com.codo24101.dao;

import ar.com.codo24101.domain.Movie;
import ar.com.codo24101.dto.MovieDTO;
import java.sql.ResultSet;
import java.util.ArrayList;


public class MovieJDBCMysqlImpl implements DAO<Movie,MovieDTO> {

    private final String nombreTabla = "movies";
    private final String[] listaColumnas = {"id_movie", "nombre", "descripcion", "genero", "calificacion", "anio", "estrellas", "director"};

    @Override
    public Movie getByID(Long id) {
        ArrayList<Movie> l = getByVal("id_movie", id.toString());
        if (!l.isEmpty()) {
            return l.get(0);
        }
        return null;
    }

    @Override
    public void create(MovieDTO movieDto) {

        String sql = "INSERT INTO %s(nombre,descripcion,genero,calificacion,anio,estrellas,director) VALUES(\"%s\",\"%s\",\"%s\",\"%s\",\"%d\",\"%d\",\"%d\") ";

        sql = sql.formatted(nombreTabla, movieDto.getNombre(),
                movieDto.getDescripcion(),
                movieDto.getGenero(),
                movieDto.getCalificacion().toString(),/// Para datos con punto flotante utilizar toString para que envie con punto y no con coma
                movieDto.getAnio(),
                movieDto.getEstrellas(),
                movieDto.getDirector());
        try {
            System.out.println(sql);
            AdministradorDeConexiones.genericoCrearActualizarBorrar(sql);
        } catch (Exception e) {
            System.out.println("Error al insertar nueva pelicula. " + e);

        }

    }

    @Override
    public void update(MovieDTO movieDto) {
        String sql = "UPDATE %s SET nombre = \"%s\", descripcion = \"%s\", genero = \"%s\" , calificacion = \"%s\", anio = \"%d\", estrellas = \"%d\",director = \"%s\" WHERE id_movie = \"%d\"";

        sql = sql.formatted(nombreTabla, movieDto.getNombre(),
                movieDto.getDescripcion(),
                movieDto.getGenero(),
                movieDto.getCalificacion().toString(),
                movieDto.getAnio(),
                movieDto.getEstrellas(),
                movieDto.getDirector(),
                movieDto.getId_movie());

        try {

            AdministradorDeConexiones.genericoCrearActualizarBorrar(sql);
        } catch (Exception e) {
            System.out.println("Error al actualizar la pelicula. " + e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM %s WHERE id_movie = %d".formatted(nombreTabla, id);

        try {
            AdministradorDeConexiones.genericoCrearActualizarBorrar(sql);
        } catch (Exception e) {
            System.out.println("Error al eliminar la pelicula. " + e);
        }

    }

    @Override
    public ArrayList<Movie> getLista() {
        ArrayList<Movie> lista = new ArrayList<>();
        String sql = "SELECT * FROM %s".formatted(nombreTabla);

        try {
            ResultSet rs = AdministradorDeConexiones.genericoConsulta(sql);

            while (rs.next()) {
                lista.add(resultadoAMovie(rs));
            }
            AdministradorDeConexiones.desconectar();
        } catch (Exception e) {
            System.out.println("Fallo la consulta de todas las peliculas. " + e);
        } finally {
        }

        return lista;
    }

    @Override
    public ArrayList<Movie> getByVal(String columnas, String valores, String metodo) {
        ArrayList<Movie> m = new ArrayList<>();

        if (!validaColumnas(columnas,listaColumnas)) {
            return null;
        }
        String[] listaCols = columnas.split(",");

        if (!validaValores(valores, listaCols.length)) {
            return null;
        }
        String[] listaValores = valores.split(",");

        String sql = generarConsulta(nombreTabla, listaValores, listaCols, metodo);

        System.out.println(sql);

        try {
            ResultSet rs = AdministradorDeConexiones.genericoConsulta(sql);

            while (rs.next()) {
                m.add(resultadoAMovie(rs));
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un problema al recuperar lista de Peliculas. " + e);
        } finally {
            AdministradorDeConexiones.desconectar();
        }

        return m;
    }

//    @Override
//    public ArrayList<Movie> getByVal(String columna, String filtro) {
//        return getByVal(columna, filtro.replaceAll(" ", "%"), "");
//    }
    
    private Movie resultadoAMovie(ResultSet r) {
        try {
            Long idMovie = r.getLong("id_movie");
            String nombre = r.getString("nombre");
            String descripcion = r.getString("descripcion");
            String genero = r.getString("genero");
            Float calificacion = r.getFloat("calificacion");
            Long anio = r.getLong("anio");
            Long estrellas = r.getLong("anio");
            Long director = r.getLong("director");
            return new Movie(idMovie, nombre, descripcion, genero, calificacion, anio, estrellas, director);
        } catch (Exception e) {
            System.out.println("Se produjo al intentar obtener los datos de la consulta. "+ e);
            return null;
        }

    }
    
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
        ArrayList<Movie> al = mj.getByVal("nombre", "hh", "OR");

        if (al.isEmpty()) {
            System.out.println("No hubo resultados para la busqueda");
        } else {
            for (Movie movie : al) {
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
            for (Movie movie : al) {
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
            for (Movie movie : al) {
                System.out.println(movie);
            }
        }

        System.out.println("");
    }

}
