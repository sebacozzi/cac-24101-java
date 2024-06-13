package ar.com.codo24101.dao;

import ar.com.codo24101.domain.Movie;
import ar.com.codo24101.dto.MovieDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MovieJDBCMysqlImpl implements MovieDAO {

    private final String nombreTabla = "movies";
    
    @Override
    public Movie getByID(Long id) {
        String sql = "SELECT * FROM %s WHERE id_movie = %d".formatted(nombreTabla, id);
        Movie m = null;
        Connection con = null;
        try {
            con = AdministradorDeConexiones.conectar();

            PreparedStatement stat = con.prepareStatement(sql);
            ResultSet rs = stat.executeQuery();

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

        } catch (Exception e) {
            System.out.println("Error al recuperar Pelicula por id. " + e);
        } finally {
            AdministradorDeConexiones.desconectar(con);
        }
        return m;
    }

    @Override
    public void create(MovieDTO movieDto) {

        String sql = "INSERT INTO %s(nombre,descripcion,genero,calificacion,anio,estrellas,director) VALUES(?,?,?,?,?,?,?) ";

        sql = sql.formatted(nombreTabla);

        Connection con = null;
        try {
            con = AdministradorDeConexiones.conectar();
            PreparedStatement ps = con.prepareStatement(sql);

          ps.setString(1,movieDto.getNombre());
          ps.setString(2,movieDto.getDescripcion());
          ps.setString(3,movieDto.getGenero());
          ps.setFloat(4,movieDto.getCalificacion());
          ps.setLong(5,movieDto.getAnio());
          ps.setLong(6,movieDto.getEstrellas());
          ps.setLong(7,movieDto.getDirector());
        
            
            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                System.out.println("Pelicula agregada correctamente.");
            } else {
                System.out.println("No se pudo agregar la pelicula. Código resultado:: " + resultado);
            }

        } catch (Exception e) {
            System.out.println("Error al insertar nueva pelicula. " + e);
        } finally {
            AdministradorDeConexiones.desconectar(con);
        }

    }

    @Override
    public void update(MovieDTO movieDto) {
        String sql = "UPDATE %s SET nombre = ?, descripcion = ?, genero = ? , calificacion = ?, anio = ?, estrellas = ?,director = ? WHERE id_movie = ?".formatted(nombreTabla);
        Connection con = null;
        try {
            con = AdministradorDeConexiones.conectar();
            PreparedStatement ps = con.prepareStatement(sql);

          ps.setString(1,movieDto.getNombre());
          ps.setString(2,movieDto.getDescripcion());
          ps.setString(3,movieDto.getGenero());
          ps.setFloat(4,movieDto.getCalificacion());
          ps.setLong(5,movieDto.getAnio());
          ps.setLong(6,movieDto.getEstrellas());
          ps.setLong(7,movieDto.getDirector());
          ps.setLong(8,movieDto.getId_movie());
            
            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                System.out.println("Pelicula Modificada con exito.");
            } else {
                System.out.println("No se pudo modificar los datos de la pelicula. Código resultado:: " + resultado);
            }

        } catch (Exception e) {
            System.out.println("Error al actualizar la pelicula. " + e);
        } finally {
            AdministradorDeConexiones.desconectar(con);
        }

    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM %s WHERE id_movie = %d".formatted(nombreTabla,id);
        Connection con = null;
        try {
            con = AdministradorDeConexiones.conectar();
            
            PreparedStatement ps = con.prepareStatement(sql);            
            
            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                System.out.println("Pelicula eliminada con exito.");
            } else {
                System.out.println("No se pudo eliminar la pelicula. Código resultado:: " + resultado);
            }

        } catch (Exception e) {
            System.out.println("Error al eliminar la pelicula. " + e);
        } finally {
            AdministradorDeConexiones.desconectar(con);
        }

    }

    @Override
    public ArrayList<Movie> getLista() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Movie getByVal(String col, String val) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        MovieJDBCMysqlImpl mj = new MovieJDBCMysqlImpl();

        //mj.create(new MovieDTO("Pelicula de Prueba", "Descripcion de Prueba", "Genero1", 4.1f, 2024l, 2l, 2l));
        mj.delete(26l);
        
        System.out.println(mj.getByID(26l));
        
    }
}
