package ar.com.codo24101.dao;

import ar.com.codo24101.domain.Movie;
import ar.com.codo24101.dto.MovieDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MovieJDBCMysqlImpl implements MovieDAO{

    @Override
    public Movie getByID(Long id) {
        String sql = "SELECT * FROM movies WHERE id_movie = %d".formatted(id);
        Movie m = null; 
        Connection con= null;
        try {
            con = AdministradorDeConexiones.conectar();
           
            PreparedStatement stat = con.prepareStatement(sql);
            ResultSet consulta = stat.executeQuery();
            
            while(consulta.next()){
                Long idMovie = consulta.getLong("id_movie");
                String nombre = consulta.getString("nombre");
                String descripcion = consulta.getString("descripcion");
                String genero= consulta.getString("genero");
                Float calificacion = consulta.getFloat("calificacion");
                Long anio = consulta.getLong("anio");
                Long estrellas = consulta.getLong("anio");
                Long director = consulta.getLong("director");
                
                m = new Movie(idMovie, nombre, descripcion, genero, calificacion, anio, estrellas, director);
                
            }
            
        } catch (Exception e) {
            System.out.println("Error al recuperar Pelicula por id. "+ e);
        }finally{
            AdministradorDeConexiones.desconectar(con);
        }
        return m;
    }

    @Override
    public void create(MovieDTO movieDto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(MovieDTO movieDto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        
        System.out.println(mj.getByID(1l));
    }
}
