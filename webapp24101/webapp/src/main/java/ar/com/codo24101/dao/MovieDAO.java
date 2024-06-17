package ar.com.codo24101.dao;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 * @param <Movie>
 * @param <MovieDTO>
 */
public interface MovieDAO <T,E> {

    public T getByID(Long id);

    public void create(E movieDto);
    
    public void update(E movieDto);
    
    public void delete(Long id);
   
    public ArrayList<T> getLista();
    
    public ArrayList<T> getByVal(String columnas,String valores, String metodo);
    
    public ArrayList<T> getByVal(String columna,String filtro);
}
