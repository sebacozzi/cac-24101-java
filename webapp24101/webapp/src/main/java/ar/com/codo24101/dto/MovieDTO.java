package ar.com.codo24101.dto;

public class MovieDTO {

    private Long id_movie;
    private String nombre;
    private String descripcion;
    private String genero;
    private Float calificacion;
    private Long anio;
    private Long estrellas;
    private Long director; //Director

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

    public Long getDirector() {
        return director;
    }

    public void setDirector(Long director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "MovieDTO{" + "id_movie=" + id_movie + ", nombre=" + nombre + ", descripcion=" + descripcion + ", genero=" + genero + ", calificacion=" + calificacion + ", anio=" + anio + ", estrellas=" + estrellas + ", director=" + director + '}';
    }

}
