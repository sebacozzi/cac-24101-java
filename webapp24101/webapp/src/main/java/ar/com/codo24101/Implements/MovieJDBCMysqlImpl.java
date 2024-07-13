package ar.com.codo24101.Implements;

import ar.com.codo24101.dao.AdministradorDeConexiones;
import ar.com.codo24101.dao.DAO;
import ar.com.codo24101.dto.MovieDTO;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MovieJDBCMysqlImpl implements DAO<MovieDTO> {

    private final String nombreTabla = "movies";
    private final String[] listaColumnas = {"id_movie", "nombre", "descripcion", "genero", "calificacion", "anio", "estrellas", "url_imagen", "director"};
    private final String[] listaInsert = {"nombre", "descripcion", "genero", "calificacion", "anio", "estrellas","url_imagen", "director"};

    public MovieJDBCMysqlImpl() {
    }

    @Override
    public MovieDTO getByName(String nombre) {
        String sql = "SELECT * FROM %s WHERE nombre = \"%s\"";
        MovieDTO m = null;
        try {
            sql = sql.formatted(nombreTabla, nombre);
            ResultSet rs = AdministradorDeConexiones.genericoConsulta(sql);

            if (rs.next()) {
                m = resultadoAMovieDTO(rs);
            }
            
            
        } catch (Exception e) {
            System.out.println("Error al Buscar la pelicula. " + e);
        } finally {
            AdministradorDeConexiones.desconectar();
        }
        return m;
    }

    @Override
    public boolean create(MovieDTO movieDto) {

        String sql = "INSERT INTO %s(%s) VALUES(%s) ";

        sql = sql.formatted(nombreTabla,
                String.join(", ", listaInsert),
                movieDTOAInsertStrings(movieDto));
        boolean ret = false;
        System.out.println("Sentencia sql insert:  \n"+sql);
        try {

            ret = AdministradorDeConexiones.genericoCrearActualizarBorrar(sql);
        } catch (Exception e) {
            System.out.println("Error al insertar nueva pelicula. " + e);
        }
        return ret;
    }

    @Override
    public boolean update(MovieDTO movieDto) {
        String sql = "UPDATE %s SET %s WHERE id_movie = \"%d\"";

        sql = sql.formatted(nombreTabla,
                movieDTOAStringUpdate(movieDto),
                movieDto.getId_movie());
        System.out.println("SQL UPDATE: \n"+ sql);
        boolean ret = false;
        try {

            ret = AdministradorDeConexiones.genericoCrearActualizarBorrar(sql);
        } catch (Exception e) {
            System.out.println("Error al actualizar la pelicula. " + e);
        }
        return ret;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM %s WHERE id_movie = %d".formatted(nombreTabla, id);
        boolean ret = false;
        try {
            ret = AdministradorDeConexiones.genericoCrearActualizarBorrar(sql);
        } catch (Exception e) {
            System.out.println("Error al eliminar la pelicula. " + e);
        }
        return ret;
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
            m.setUrl_imagen(r.getString("url_imagen"));
            m.setDirector(r.getLong("director"));

            return m;
        } catch (Exception e) {
            System.out.println("Se produjo al intentar obtener los datos de la consulta. " + e);
            return null;
        }
    }

    private String movieDTOAStringUpdate(MovieDTO m) {
        String resultado = "";
        if (m.getNombre() != null) {
            resultado = resultado + "nombre= \""+ m.getNombre()+"\",";
        }
        if (m.getDescripcion() != null) {
            resultado=resultado +"descripcion= \""+  m.getDescripcion()+"\",";
        }
        if (m.getGenero() != null) {
            resultado =resultado +"genero= \""+  m.getGenero()+"\",";
        }
        if (m.getCalificacion() != 0) {
            resultado =resultado +"calificacion= \""+  m.getCalificacion().toString().replaceAll(",", ".")+"\",";
        }
        if (m.getAnio() != 0) {
            resultado =resultado +"anio= \""+  m.getAnio().toString()+"\",";
        }
        
        if (isNull(m.getEstrellas())) {
            resultado =resultado +"estrellas= \""+   m.getEstrellas().toString()+"\",";
        }
        if (m.getUrl_imagen() != null) {
            resultado =resultado +"url_imagen= \""+  m.getUrl_imagen()+"\",";
        }
        if (m.getDirector() != 0) {
            resultado =resultado +"director= \""+  m.getDirector().toString()+"\",";
        }
        System.out.println("Resultado: " + resultado);
        return resultado.substring(0,resultado.length()-1);
    }

    private String movieDTOAInsertStrings(MovieDTO m){
        String resultado="";
        
        if (m.getNombre() != null) {
            resultado = resultado + "\""+m.getNombre()+"\",";
        }
        if (m.getDescripcion() != null) {
            resultado= resultado + "\""+ m.getDescripcion() + "\",";
        }
        if (m.getGenero() != null) {
            resultado= resultado + "\""+m.getGenero()+ "\",";
        }
        if (m.getCalificacion() != 0) {
            resultado= resultado + "\""+ m.getCalificacion().toString().replaceAll(",", ".") + "\",";
        }
        if (m.getAnio() != 0) {
            resultado= resultado + "\""+ m.getAnio().toString()+"\",";
        }
        System.out.println("GetEstrellas: " + m.getEstrellas());
        if (m.getEstrellas()!=0) {
            resultado= resultado + "\""+ m.getEstrellas().toString()+"\",";
        }
        if (m.getUrl_imagen() != null) {
            resultado= resultado + "\""+ m.getUrl_imagen()+"\",";
        }
        if (m.getDirector() != 0) {
            resultado= resultado + "\""+ m.getDirector().toString()+"\",";
        }
        resultado = resultado.substring(0, resultado.length()-1);
        System.out.println("Resultado: " + resultado);
        return resultado;
    }
}
