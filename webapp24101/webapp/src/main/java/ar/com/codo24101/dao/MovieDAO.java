package ar.com.codo24101.dao;

import ar.com.codo24101.domain.Movie;

public interface MovieDAO {

    public Movie getByID(long id);

    public void create(MovieDTO movieDto);
}
