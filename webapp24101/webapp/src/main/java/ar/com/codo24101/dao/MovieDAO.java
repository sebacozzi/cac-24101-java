package ar.com.codo24101.dao;

import ar.com.codo24101.domain.Movie;
import ar.com.codo24101.dto.MovieDTO;
import java.util.ArrayList;

public interface MovieDAO {

    public Movie getByID(Long id);

    public void create(MovieDTO movieDto);
    
    public void update(MovieDTO movieDto);
    
    public void delete(Long id);
   
    public ArrayList<Movie> getLista();
    
    public Movie getByVal(String col,String val);
}
