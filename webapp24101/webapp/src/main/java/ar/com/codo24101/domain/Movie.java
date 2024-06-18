package ar.com.codo24101.domain;

import ar.com.codo24101.dto.MovieDTO;

public class Movie {

    private Long id_movie;
    private String nombre;
    private String descripcion;
    private String genero;
    private Float calificacion;
    private Long anio;
    private Long estrellas;
    private Director director; //Director

    public Movie() {
    }

    public Movie(Long id_movie, String nombre, String descripcion, String genero, Float calificacion, Long anio, Long estrellas, Director director) {
        this.id_movie = id_movie;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.genero = genero;
        this.calificacion = calificacion;
        this.anio = anio;
        this.estrellas = estrellas;
        this.director = director;
    }

    public Movie(String nombre, String descripcion, String genero, Float calificacion, Long anio, Long estrellas, Director director) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.genero = genero;
        this.calificacion = calificacion;
        this.anio = anio;
        this.estrellas = estrellas;
        this.director = director;
    }

    public Long getId_movie() {
        return id_movie;
    }

    public void setId_movie(Long id_movie) {
        this.id_movie = id_movie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Float calificacion) {
        this.calificacion = calificacion;
    }

    public Long getAnio() {
        return anio;
    }

    public void setAnio(Long anio) {
        this.anio = anio;
    }

    public Long getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Long estrellas) {
        this.estrellas = estrellas;
    }

    public Director getDirector() {
        return director;
    }
    public Long getIdDirector(){
        return director.getId_director();
    }
    
    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" + "id_movie=" + id_movie + ", nombre=" + nombre + ", descripcion=" + descripcion + ", genero=" + genero + ", calificacion=" + calificacion + ", anio=" + anio + ", estrellas=" + estrellas + ", director=" + director + '}';
    }
    
    
    public static MovieDTO movieToMovieDTO(Movie m){
        if (m == null) return null;
        MovieDTO mdto= new MovieDTO();
        mdto.setId_movie(m.getId_movie());
        mdto.setNombre(m.getNombre());
        mdto.setDescripcion(m.getDescripcion());
        mdto.setGenero(m.getGenero());
        mdto.setCalificacion(m.getCalificacion());
        mdto.setAnio(m.getAnio());
        mdto.setEstrellas(m.getEstrellas());
        mdto.setDirector(m.getIdDirector());
        
        return mdto;
    }
}
