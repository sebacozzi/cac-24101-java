/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codo24101.service;

import ar.com.codo24101.dao.DAO;
import ar.com.codo24101.Implements.DirectorJDBCMysqlImpl;
import ar.com.codo24101.Implements.MovieJDBCMysqlImpl;
import ar.com.codo24101.domain.Director;
import ar.com.codo24101.domain.Movie;
import ar.com.codo24101.dto.DirectorDTO;
import ar.com.codo24101.dto.MovieDTO;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class MovieService {
    private DAO<MovieDTO> movieDao;
    private DAO<DirectorDTO> dirDao;

    public MovieService() {
        this.movieDao = new MovieJDBCMysqlImpl();
        this.dirDao = new DirectorJDBCMysqlImpl();
    }
    
    public boolean crear(MovieDTO movieDto ){
       movieDao.create(movieDto);
        return movieDao.getByName(movieDto.getNombre())!=null;
    }
    
    public MovieDTO obtener(Long id){
        return movieDao.getByID("id_movie",id);
    }
    public MovieDTO obtenerPelicula(String nombre){
        return movieDao.getByName(nombre);
    }
    
    public boolean eliminarPelicula(Long id){
        movieDao.delete(id);
        return movieDao.getByID("id_movie",id) == null;
    }
    
   public boolean actualizarPelicula(MovieDTO m){
      
      return movieDao.update(m);
   }
   
   public ArrayList<MovieDTO> obtenerPeliculas(){
       return movieDao.getLista();
   }
   
   public ArrayList<MovieDTO> obtenerByVal(String columnas,String valores){
       return movieDao.getByVal(columnas, valores);
   }
   
   public ArrayList<MovieDTO> obtenerByVal(String columnas,String valores,String caso){
       return movieDao.getByVal(columnas, valores,caso);
   }
  
    private Movie movieDTOAMovie(MovieDTO mdto){
        Movie m = new Movie();
        m.setId_movie(mdto.getId_movie());
        m.setNombre(mdto.getNombre());
        m.setDescripcion(mdto.getDescripcion());
        m.setGenero(mdto.getGenero());
        m.setCalificacion(mdto.getCalificacion());
        m.setEstrellas(mdto.getEstrellas());
        m.setDirector(Director.DirectorDTOtoDirector(dirDao.getByID("id_director",mdto.getDirector())));
        
        return m;
    }
}
