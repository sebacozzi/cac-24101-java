package ar.com.codo24101.dao;

import ar.com.codo24101.domain.Movie;
import ar.com.codo24101.dto.MovieDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

public class MovieJDBCMysqlImpl implements MovieDAO {

    private final String nombreTabla = "movies";
    private final String[] listaColumnas = {"id_movie", "nombre", "descripcion", "genero", "calificacion", "anio", "estrellas", "director"};

    @Override
    public Movie getByID(Long id) {
        String sql = "SELECT * FROM %s WHERE id_movie = %d".formatted(nombreTabla, id);
        Movie m = null;

        try {

            ResultSet rs = AdministradorDeConexiones.genericoConsulta(sql);

            while (rs.next()) {
                Long idMovie = rs.getLong("id_movie");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String genero = rs.getString("genero");
                Float calificacion = rs.getFloat("calificacion");
                Long anio = rs.getLong("anio");
                Long estrellas = rs.getLong("anio");
                Long director = rs.getLong("director");

                m = new Movie(idMovie, nombre, descripcion, genero, calificacion, anio, estrellas, director);

            }

            AdministradorDeConexiones.desconectar();
        } catch (Exception e) {
            System.out.println("Error al recuperar Pelicula por id. " + e);
        }
        return m;
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
                lista.add(new Movie(
                        rs.getLong("id_movie"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("genero"),
                        rs.getFloat("calificacion"),
                        rs.getLong("anio"),
                        rs.getLong("estrellas"),
                        rs.getLong("director")));
            }
            AdministradorDeConexiones.desconectar();
        } catch (Exception e) {
            System.out.println("Fallo la consulta de todas las peliculas. " + e);
        } finally {
        }

        return lista;
    }

    @Override
    public Movie getByVal(String col, String val) {
        Movie m = null;
        String[] listaCol = col.split(",");
        System.out.println("col: " + Arrays.toString(col.split(",")));
        System.out.println("Length listaCol: " + listaCol.length);
        if (col.length() == 0 || listaCol.length == 0) {
            System.out.println("""
                               No se paso ninguna columna para filtrado.
                               Las columnas deben estar separadas por coma. Formato de lista de columnas: "col1,col2,col3,coln"
                               Columnas admitidas: """ + Arrays.toString(listaColumnas));
            return null;
        } else {
            String errorCheckCols = checkColumnas(listaColumnas, listaCol);
            if (!errorCheckCols.isEmpty()) {
                System.out.println("No existe la columna: \"" + errorCheckCols + "\"");
                return null;
            }

        }

        System.out.println("!!Retorna Movie!!");
        return m;
    }

    private String checkColumnas(String[] listaCols, String[] cols) {
        String resultado = "";
        for (String col : cols) {
            if (!Arrays.toString(listaCols).contains(col)) {
                resultado += col + ",";
            }
        }
        
        resultado= String.join(",", resultado.split(","));
        return resultado;
    }

    public static void main(String[] args) {
        MovieJDBCMysqlImpl mj = new MovieJDBCMysqlImpl();
        //System.out.println(mj.getByID(28l));

        // mj.delete(28l);
        //mj.create(new MovieDTO("Pelicula de Prueba 29", "Descripcion de Prueba 29", "Genero 29", 44504.1f, 2024l, 2l, 4l));
        //mj.update(new MovieDTO(29l, "Pelicula de Prueba 29", "Descripcion de Prueba 29", "Genero 29", 4.1f, 2024l, 2l, 4l));
//        System.out.println("");
//        System.out.println(mj.getByID(25l));
//        ArrayList<Movie> al = mj.getLista();
//        
//        for (Movie movie : al) {
//            System.out.println(movie);
//        }
        System.out.println("Sin valor en columnas:");
        mj.getByVal(".", "hh");
        System.out.println("");
        System.out.println("Con una sola columna:");
        mj.getByVal("nombre", "hh");
        System.out.println("");
        System.out.println("Con una columna mal: ");
        mj.getByVal("directores", "hh");
        System.out.println("");
        System.out.println("Con 2 columnas:");
        mj.getByVal("director,nombre,", "hh");
        System.out.println("");
        System.out.println("Con 2 columnas mal:");
        mj.getByVal("nombres,directores", "hh");
        System.out.println("");
        System.out.println("Con 1 columna bien y 1 mal:");
        mj.getByVal("nombres,director", "hh");
        System.out.println("");
        System.out.println("Con 1 columna bien y 1 mal:");
        mj.getByVal("nombres,director,", "hh");
        System.out.println("");
    }
}
