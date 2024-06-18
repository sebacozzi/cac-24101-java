/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codo24101.service;

import ar.com.codo24101.dao.DAO;
import ar.com.codo24101.dao.DirectorJDBCMysqlImpl;
import ar.com.codo24101.dao.MovieJDBCMysqlImpl;
import ar.com.codo24101.domain.Director;
import ar.com.codo24101.domain.Movie;
import ar.com.codo24101.dto.DirectorDTO;
import ar.com.codo24101.dto.MovieDTO;

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
    
    public Movie obtener(Long id){
        return movieDTOAMovie(movieDao.getByID(id));
    }
    public MovieDTO obtenerPelicula(Long id){
        return movieDao.getByID(id);
    }
    
    public void elimanarPelicula(Long id){
        movieDao.delete(id);
    }
    
   public void actualizarPelicula(Movie m){
       movieDao.update(Movie.movieToMovieDTO(m));
   }
   
   
    
    private Movie movieDTOAMovie(MovieDTO mdto){
        Movie m = new Movie();
        m.setId_movie(mdto.getId_movie());
        m.setNombre(mdto.getNombre());
        m.setDescripcion(mdto.getDescripcion());
        m.setGenero(mdto.getGenero());
        m.setCalificacion(mdto.getCalificacion());
        m.setEstrellas(mdto.getEstrellas());
        m.setDirector(Director.DirectorDTOtoDirector(dirDao.getByID(mdto.getDirector())));
        
        return m;
    }
}
