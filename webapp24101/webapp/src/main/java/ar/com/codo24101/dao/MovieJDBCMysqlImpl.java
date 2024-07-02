package ar.com.codo24101.dao;

import ar.com.codo24101.dto.MovieDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MovieJDBCMysqlImpl implements DAO<MovieDTO> {

    private final String nombreTabla = "movies";
    private final String[] listaColumnas = {"id_movie", "nombre", "descripcion", "genero", "calificacion", "anio", "estrellas", "director"};
    private final String[] listaInsert = {"nombre", "descripcion", "genero", "calificacion", "anio", "estrellas", "director"};

    public MovieJDBCMysqlImpl() {
    }
    
    @Override
    public void create(MovieDTO movieDto) {

        String sql = "INSERT INTO %s(%s) VALUES(%s) ";

        sql = sql.formatted(nombreTabla,
                String.join(", ", listaInsert),
                formateo("\"%s\"", ", ", movieDTOAMap(movieDto)));
        try {

            AdministradorDeConexiones.genericoCrearActualizarBorrar(sql);
        } catch (Exception e) {
            System.out.println("Error al insertar nueva pelicula. " + e);
        }

    }

    @Override
    public void update(MovieDTO movieDto) {
        String sql = "UPDATE %s SET %s WHERE id_movie = \"%d\"";

        sql = sql.formatted(nombreTabla, movieDto.getNombre(),
                formateo("%s = \"%s\"", ", ", movieDTOAMap(movieDto)),
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
    public ArrayList<MovieDTO> getLista() {

        ArrayList<MovieDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM %s".formatted(nombreTabla);

        try {
            ResultSet rs = AdministradorDeConexiones.genericoConsulta(sql);

            while (rs.next()) {
                lista.add(resultadoAMovieDTO(rs));
            }

            AdministradorDeConexiones.desconectar();
        } catch (Exception e) {
            System.out.println("Fallo la consulta de todas las peliculas. " + e);
        }

        return lista;
    }

    @Override
    public ArrayList<MovieDTO> getByVal(String columnas, String valores, String metodo) {
        ArrayList<MovieDTO> m = new ArrayList<>();

        try {
            String sql = crearSqlConsulta(columnas, valores, nombreTabla, listaColumnas, metodo);

            ResultSet rs = AdministradorDeConexiones.genericoConsulta(sql);

            while (rs.next()) {
                m.add(resultadoAMovieDTO(rs));

            }
            for (Map.Entry<String, String> en : movieDTOAMap(m.get(0)).entrySet()) {
                System.out.println("Key: "+ en.getKey() + "  - Valor: "+ en.getValue());
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un problema al recuperar lista de Peliculas. " + e);
        } finally {
            AdministradorDeConexiones.desconectar();
        }
        
        return m;
    }

    private MovieDTO resultadoAMovieDTO(ResultSet r) {
        try {
            MovieDTO m = new MovieDTO();
            m.setId_movie(r.getLong("id_movie"));
            m.setNombre(r.getString("nombre"));
            m.setDescripcion(r.getString("descripcion"));
            m.setGenero(r.getString("genero"));
            m.setCalificacion(r.getFloat("calificacion"));
            m.setAnio(r.getLong("anio"));
            m.setEstrellas(r.getLong("estrellas"));
            m.setDirector(r.getLong("director"));
            
            return m;
        } catch (Exception e) {
            System.out.println("Se produjo al intentar obtener los datos de la consulta. " + e);
            return null;
        }
    }

    private HashMap<String, String> movieDTOAMap(MovieDTO m) {
        HashMap<String, String> resultado = new HashMap<>();
        if (m.getNombre()!=null) {
            resultado.put("nombre", m.getNombre());
        }
        if (m.getDescripcion()!=null) {
            resultado.put("descripcion", m.getDescripcion());
        }
        if (m.getGenero()!=null) {
            resultado.put("genero", m.getGenero());
        }
        if (m.getCalificacion() != 0) {
            resultado.put("calificacion", m.getCalificacion().toString().replaceAll(",", "."));
        }
        if (m.getAnio() != 0) {
            resultado.put("anio", m.getAnio().toString());
        }
        if (isNull(m.getEstrellas())) {
            resultado.put("estrellas", m.getEstrellas().toString());
        }
        if (m.getDirector() != 0) {
            resultado.put("director", m.getDirector().toString());
        }
        System.out.println("Resultado: "+ resultado);
        return resultado;
    }
    
    
}
